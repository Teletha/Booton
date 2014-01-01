/*
 * Copyright (C) 2013 Nameless Production Committee
 *
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          http://opensource.org/licenses/mit-license.php
 */
package booton.translator;

import static booton.translator.OperandCondition.*;
import static org.objectweb.asm.Opcodes.*;
import static org.objectweb.asm.Type.*;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringJoiner;

import js.lang.NativeObject;
import jsx.bwt.Input;
import kiss.I;
import kiss.model.ClassUtil;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Attribute;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.Handle;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Type;

import booton.Obfuscator;
import booton.Stylist;
import booton.css.CSS;
import booton.translator.Node.Switch;
import booton.translator.Node.TryCatchFinallyBlocks;

/**
 * <p>
 * In general, the compiler converts the short-circuit route, and optimizes the logical expression.
 * Compiler generates goto-label to omit the evaluation of the second operand that becomes
 * unnecessary when first operand is evaluated. If we can't restore the original logical expression
 * completely, garbage goto code will remain.
 * </p>
 * 
 * @version 2013/11/25 14:07:28
 */
class JavaMethodCompiler extends MethodVisitor {

    /**
     * Represents an expanded frame. See {@link ClassReader#EXPAND_FRAMES}.
     */
    private static final int FRAME_NEW = 400;

    /**
     * Represents a compressed frame with complete frame data.
     */
    private static final int FRAME_FULL = 401;

    /**
     * Represents a compressed frame where locals are the same as the locals in the previous frame,
     * except that additional 1-3 locals are defined, and with an empty stack.
     */
    private static final int FRAME_APPEND = 402;

    /**
     * Represents a compressed frame where locals are the same as the locals in the previous frame,
     * except that the last 1-3 locals are absent and with an empty stack.
     */
    private static final int FRAME_CHOP = 403;

    /**
     * Represents a compressed frame with exactly the same locals as the previous frame and with an
     * empty stack.
     */
    private static final int FRAME_SAME = 404;

    /**
     * Represents a compressed frame with exactly the same locals as the previous frame and with a
     * single value on the stack.
     */
    private static final int FRAME_SAME1 = 405;

    /**
     * Represents a jump instruction. A jump instruction is an instruction that may jump to another
     * instruction.
     */
    private static final int JUMP = 406;

    /**
     * Represents a primitive addtion instruction. IADD, LADD, FADD and DADD.
     */
    private static final int ADD = 407;

    /**
     * Represents a primitive substruction instruction. ISUB, LSUB, FSUB and DSUB.
     */
    private static final int SUB = 408;

    /**
     * Represents a constant 0 instruction. ICONST_0, LCONST_0, FCONST_0 and DCONST_0.
     */
    private static final int CONSTANT_0 = 409;

    /**
     * Represents a constant 1 instruction. ICONST_1, LCONST_1, FCONST_1 and DCONST_1.
     */
    private static final int CONSTANT_1 = 410;

    /**
     * Represents a duplicate instruction. DUP and DUP2.
     */
    private static final int DUPLICATE = 411;

    /**
     * Represents a duplicate instruction. DUP_X1 and DUP2_X2.
     */
    private static final int DUPLICATE_X1 = 412;

    /**
     * Represents a return instruction.
     */
    private static final int RETURNS = 413;

    /**
     * Represents a increment instruction.
     */
    private static final int INCREMENT = 414;

    /** The extra opcode for byte code parsing. */
    private static final int LABEL = 300;

    /** The frequently used operand for cache. */
    private static final OperandNumber ZERO = new OperandNumber(0);

    /** The frequently used operand for cache. */
    private static final OperandNumber ONE = new OperandNumber(1);

    /** The frequently used operand for cache. */
    private static final OperandExpression Return = new OperandExpression("return ");

    /** The debug flag. */
    private final Debugger debugger;

    /** The java source(byte) code. */
    private final Javascript script;

    /** The javascript object code. */
    private final ScriptWriter code;

    /** The current processing method name. */
    private final String methodName;

    /** The method return type. */
    private final Type returnType;

    /** The method return type. */
    private final Type[] parameterTypes;

    /** The local variable manager. */
    private final LocalVariables variables;

    /** The pool of try-catch-finally blocks. */
    private final TryCatchFinallyBlocks tries = new TryCatchFinallyBlocks();

    private final Map<Node, Label> labels = new HashMap<>();

    /** The current processing node. */
    private Node current = null;

    /** The all node list for this method. */
    private List<Node> nodes = new ArrayList();

    /** The counter for the current processing node identifier. */
    private int counter = -1;

    /** The counter for construction of the object initialization. */
    private int countInitialization = 0;

    /** The record of recent instructions. */
    private int[] records = new int[8];

    /** The current start position of instruction records. */
    private int recordIndex = 0;

    /** The flag whether the next jump instruction is used for assert statement or not. */
    private boolean assertJump = false;

    /**
     * {@link Enum#values} produces special bytecode, so we must handle it by special way.
     */
    private Operand[] enumValues = new Operand[2];

    /**
     * <p>
     * Switch statement with enum produces special bytecode, so we must handle it by special way.
     * The following asmfier code is typical code for enum switch.
     * </p>
     * 
     * <pre>
     * // invoke compiler generated static method to retrieve the user class specific number array
     * // we should ignore this oeprand
     * mv.visitMethodInsn(INVOKESTATIC, "EnumSwitchUserClass", "$SWITCH_TABLE$EnumClass", "()[I");
     *
     * // load target enum variable
     * mv.visitVarInsn(ALOAD, 1);
     * 
     * // invoke Enum#ordinal method to retieve identical number
     * mv.visitMethodInsn(INVOKEVIRTUAL, "EnumClass", "ordinal", "()I");
     * 
     * // access mapping number array
     * //we should ignore this operand
     * mv.visitInsn(IALOAD);
     * </pre>
     */
    private boolean enumSwitchInvoked;

    /** The synchronized block related nodes. */
    private Set<Node> synchronizer = new HashSet();

    /**
     * @param script A target script to compile.
     * @param code A code writer.
     * @param name A method name.
     * @param description A method description.
     * @param isStatic A static flag.
     */
    JavaMethodCompiler(Javascript script, ScriptWriter code, String original, String name, String description, boolean isStatic) {
        super(ASM5);

        CompilerRecorder.recordMethodName(original);

        this.script = script;
        this.code = code;
        this.methodName = name;
        this.returnType = Type.getReturnType(description);
        this.parameterTypes = Type.getArgumentTypes(description);
        this.variables = new LocalVariables(isStatic);
        this.debugger = new Debugger(script.source, original, description);

        Type[] parameters = Type.getArgumentTypes(description);

        if (!isStatic) {
            variables.type(0).type(script.source);
        }

        for (int i = 0; i < parameters.length; i++) {
            variables.type(isStatic ? i : i + 1).type(convert(parameters[i]));
        }
        debugger.whileProcess = true;

        if (script.source.getName().endsWith("LeftObserver") && original.equals("onNext")) {
            debugger.enable = true;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void visitEnd() {
        debugger.whileProcess = false;

        // Dispose all nodes which contains synchronized block.
        for (Node node : synchronizer) {
            disposeNode(node, true);
        }

        for (int i = nodes.size() - 1; 0 <= i; i--) {
            Node node = nodes.get(i);

            // Resolve shorthand syntax sugar of "if" statement.
            if (node.stack.peekFirst() instanceof OperandCondition && node.outgoing.size() == 1) {
                // create condition node
                Node condition = createNode(node);
                condition.connect(node);
                condition.connect(node.outgoing.get(0));
                condition.stack.add(node.stack.pollFirst().invert());
            }

            // Separate conditional operands.
            int number = searchConditionalOperandSeparator(node);

            if (number != -1) {
                Node created = createNode(nodes.get(i + 1));

                // transfer operands
                for (int j = 0; j < number; j++) {
                    created.stack.addFirst(node.stack.pollLast());
                }
            }
        }

        // Search all backedge nodes.
        searchBackEdge(nodes.get(0), new ArrayDeque());

        // Resolve all try-catch-finally blocks.
        tries.process();

        // Build dominator tree
        for (Node node : nodes) {
            Node dominator = node.getDominator();

            if (dominator != null) {
                dominator.dominators.addIfAbsent(node);
            }
        }

        debugger.print(script, nodes);

        // ===============================================
        // Script Code
        // ===============================================
        try {
            // write method declaration
            code.mark();
            code.append(methodName, ":", "function(", I.join(",", variables.names()), "){");
            nodes.get(0).write(code);
            code.optimize();
            code.append('}'); // method end
            code.separator();
        } catch (Exception e) {
            TranslationError error = new TranslationError(e);
            error.write("Can't compile method because");
            error.write(e.getMessage());
            error.writeMethod(debugger.methodName, returnType, parameterTypes);

            throw error;
        }

        debugger.print(code.toFragment());
    }

    /**
     * <p>
     * Count a number of transferable operands.
     * </p>
     * 
     * @param node A target node.
     * @return A result.
     */
    private int searchConditionalOperandSeparator(Node node) {
        boolean initialized = false;
        boolean conditionOnly = false;

        for (int i = node.stack.size() - 1; 0 <= i; i--) {
            Operand operand = node.stack.get(i);

            if (operand == Return || operand == Node.RETURN) {
                return -1;
            }

            if (operand == Node.END) {
                continue;
            }

            if (operand instanceof OperandCondition) {
                if (!initialized) {
                    initialized = true;
                    conditionOnly = true;
                } else {
                    if (!conditionOnly) {
                        return node.stack.size() - i - 1;
                    }
                }
            } else {
                if (!initialized) {
                    initialized = true;
                    conditionOnly = false;
                } else {
                    if (conditionOnly) {
                        return node.stack.size() - i - 1;
                    }
                }
            }
        }
        return -1;
    }

    /**
     * <p>
     * Helper method to search all backedge nodes using depth-first search.
     * </p>
     * 
     * @param node A target node to check.
     * @param recorder All passed nodes.
     */
    private void searchBackEdge(Node node, Deque<Node> recorder) {
        // Store the current processing node.
        recorder.add(node);

        // Step into outgoing nodes.
        for (Node out : node.outgoing) {
            if (recorder.contains(out)) {
                out.backedges.addIfAbsent(node);
            } else {
                searchBackEdge(out, recorder);
            }
        }

        // Remove the current processing node.
        recorder.pollLast();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
        if (desc.equals(Type.getType(Debuggable.class).getDescriptor())) {
            debugger.enable = true;

            return debugger;
        }
        return null; // do nothing
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AnnotationVisitor visitAnnotationDefault() {
        return null; // do nothing
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void visitAttribute(Attribute attr) {
        // do nothing
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void visitCode() {
        visitLabel(new Label());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void visitLineNumber(int line, Label start) {
        getNode(start).number = line;

        CompilerRecorder.recordMethodLineNumber(line);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void visitFieldInsn(int opcode, String ownerClassName, String name, String desc) {
        // If this field access instruction is used for assertion, we should skip it to erase
        // compiler generated extra code.
        if (name.equals("$assertionsDisabled")) {
            assertJump = true;
            return;
        }

        // recode current instruction
        record(opcode);

        // compute owner class
        Class owner = convert(ownerClassName);

        // current processing script depends on the owner class
        Javascript.require(owner);

        // Field type
        Class type = convert(Type.getType(desc));

        Translator translator = TranslatorManager.getTranslator(owner);

        switch (opcode) {
        case PUTFIELD:
            // Increment (decrement) of field doesn't use increment instruction, so we must
            // distinguish increment (decrement) from addition by pattern matching.
            if (match(ALOAD, DUP, GETFIELD, DUPLICATE_X1, CONSTANT_1, ADD, PUTFIELD)) {
                // The pattenr of post-increment field is like above.
                current.remove(0);

                current.addOperand(new OperandExpression(current.remove(0) + "." + Javascript.computeFieldName(owner, name) + "++"));
            } else if (match(ALOAD, DUP, GETFIELD, DUPLICATE_X1, CONSTANT_1, SUB, PUTFIELD)) {
                // The pattenr of post-decrement field is like above.
                current.remove(0);

                current.addOperand(new OperandExpression(current.remove(0) + "." + Javascript.computeFieldName(owner, name) + "--"));
            } else if (match(ALOAD, DUP, GETFIELD, CONSTANT_1, ADD, DUPLICATE_X1, PUTFIELD)) {
                // The pattenr of pre-increment field is like above.
                current.remove(0);

                current.addOperand(new OperandExpression("++" + current.remove(0) + "." + Javascript.computeFieldName(owner, name)));
            } else if (match(ALOAD, DUP, GETFIELD, CONSTANT_1, SUB, DUPLICATE_X1, PUTFIELD)) {
                // The pattenr of pre-decrement field is like above.
                current.remove(0);

                current.addOperand(new OperandExpression("--" + current.remove(0) + "." + Javascript.computeFieldName(owner, name)));
            } else {
                OperandExpression assignment = new OperandExpression(translator.translateField(owner, name, current.remove(1)) + "=" + current.remove(0)
                        .cast(type), type);

                if (match(DUPLICATE_X1, PUTFIELD)) {
                    // multiple assignment (i.e. this.a = this.b = 0;)
                    current.addOperand(assignment);
                } else {
                    // normal assignment
                    current.addExpression(assignment);
                }
            }
            break;

        case GETFIELD:
            current.addOperand(translator.translateField(owner, name, current.remove(0)), type);
            break;

        case PUTSTATIC:
            if (match(GETSTATIC, DUPLICATE, CONSTANT_1, ADD, PUTSTATIC)) {
                // The pattenr of post-increment field is like above.
                current.remove(0);

                current.addOperand(new OperandExpression(current.remove(0) + "++"));
            } else if (match(GETSTATIC, DUPLICATE, CONSTANT_1, SUB, PUTSTATIC)) {
                // The pattenr of post-decrement field is like above.
                current.remove(0);

                current.addOperand(new OperandExpression(current.remove(0) + "--"));
            } else if (match(GETSTATIC, CONSTANT_1, ADD, DUPLICATE, PUTSTATIC)) {
                // The pattenr of pre-increment field is like above.
                current.remove(0);
                current.remove(0);

                current.addOperand(new OperandExpression("++" + Javascript.computeClassName(owner) + "." + Javascript.computeFieldName(owner, name)));

            } else if (match(GETSTATIC, CONSTANT_1, SUB, DUPLICATE, PUTSTATIC)) {
                // The pattenr of pre-decrement field is like above.
                current.remove(0);
                current.remove(0);

                current.addOperand(new OperandExpression("--" + Javascript.computeClassName(owner) + "." + Javascript.computeFieldName(owner, name)));
            } else {
                current.addExpression(new OperandExpression(Javascript.computeClassName(owner) + "." + Javascript.computeFieldName(owner, name) + "=" + current.remove(0)
                        .cast(type), type));
            }
            break;

        case GETSTATIC:
            current.addOperand(translator.translateStaticField(owner, name), type);
            break;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void visitFrame(int type, int nLocal, Object[] local, int nStack, Object[] stack) {
        switch (type) {
        case F_NEW:
            record(FRAME_NEW);
            break;

        case F_FULL:
            record(FRAME_FULL);

            processTernaryOperator();
            break;

        case F_APPEND:
            record(FRAME_APPEND);
            break;

        case F_CHOP:
            record(FRAME_CHOP);
            break;

        case F_SAME:
            record(FRAME_SAME);

            if (nLocal == 0 && nStack == 0) {
                processTernaryOperator();
            }
            break;

        case F_SAME1:
            record(FRAME_SAME1);

            if (nLocal == 0 && nStack == 1) {
                processTernaryOperator();
            }
            break;
        }
    }

    /**
     * <p>
     * Helper method to resolve ternary operator.
     * </p>
     */
    private void processTernaryOperator() {
        Operand third = current.peek(2);

        if (third instanceof OperandCondition) {
            Operand first = current.peek(0);

            if (first == Node.END) {
                return;
            }

            Operand second = current.peek(1);

            Node firstNode = findNodeBy(first);
            Node secondNode = findNodeBy(second);
            Node thirdNode = findNodeBy(third);

            if (firstNode == secondNode) {
                return;
            }

            if ((firstNode == thirdNode && secondNode != thirdNode) || (firstNode != thirdNode && secondNode == thirdNode) || (thirdNode.outgoing.contains(firstNode) && thirdNode.outgoing.contains(secondNode))) {
                if (first == ONE && second == ZERO) {
                    current.remove(0);
                    current.remove(0);
                    thirdNode.addOperand(new OperandAmbiguousZeroOneTernary(current.remove(0)));
                } else if (first == ZERO && second == ONE) {
                    current.remove(0);
                    current.remove(0);
                    thirdNode.addOperand(new OperandAmbiguousZeroOneTernary(current.remove(0).invert()));
                } else {
                    current.remove(0);
                    current.remove(0);
                    current.remove(0);
                    thirdNode.addOperand(new OperandEnclose(new OperandExpression(third.invert().disclose() + "?" + second.disclose() + ":" + first.disclose(), new InferredType(first, second))));
                }

                // dispose empty nodes
                if (firstNode.stack.isEmpty()) {
                    debugger.print("dispose first node " + firstNode.id);
                    disposeNode(firstNode);
                }

                if (secondNode.stack.isEmpty()) {
                    debugger.print("dispose second node " + secondNode.id);
                    disposeNode(secondNode);
                }

                // process recursively
                processTernaryOperator();
            }
        }
    }

    /**
     * <p>
     * Search the node which has the specified operand.
     * </p>
     * 
     * @param operand
     * @return
     */
    private Node findNodeBy(Operand operand) {
        for (int i = nodes.size() - 1; 0 <= i; i--) {
            Node node = nodes.get(i);

            if (node.has(operand)) {
                return node;
            }
        }
        throw new IllegalArgumentException("The operand [" + operand + "] is not found in the current context.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void visitIincInsn(int position, int increment) {
        // recode current instruction
        record(INCREMENT);

        // retrieve the local variable name
        String variable = variables.name(position);
        InferredType type = variables.type(position);

        if (increment == 1) {
            // increment
            if (match(ILOAD, INCREMENT)) {
                // post increment
                current.addOperand(new OperandExpression(current.remove(0) + "++", type));
            } else {
                // pre increment
                current.addExpression(new OperandExpression("++" + variable, type));
            }
        } else if (increment == -1) {
            // increment
            if (match(ILOAD, INCREMENT)) {
                // post increment
                current.addOperand(new OperandExpression(current.remove(0) + "--", type));
            } else {
                // pre increment
                current.addExpression(new OperandExpression("--" + variable, type));
            }
        } else {
            current.addExpression(variable, "=", variable, "+", increment);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void visitInsn(int opcode) {
        // recode current instruction
        record(opcode);

        switch (opcode) {
        case DUP:
            if (!match(NEW, DUP) && !match(NEW, DUP2)) {
                // mark as duplicated operand
                current.peek(0).duplicated = true;
            }
            break;

        case DUP2:
            if (!match(NEW, DUP) && !match(NEW, DUP2)) {
                // mark as duplicated operand
                Operand first = current.peek(0);
                first.duplicated = true;

                if (!first.isLarge()) {
                    current.peek(1).duplicated = true;
                }
            }
            break;

        case DUP_X1:
        case DUP_X2:
            // These instructions are used for field increment mainly, see visitFieldInsn(PUTFIELD).
            // Skip this instruction to simplify compiler.
            break;

        case DUP2_X1:
        case DUP2_X2:
            // These instructions are used for field increment mainly, see visitFieldInsn(PUTFIELD).
            // Skip this instruction to simplify compiler.
            break;

        case POP:
            // When the JDK compiler compiles the code including "instance method reference", it
            // generates the byte code expressed in following ASM codes.
            //
            // visitInsn(DUP);
            // visitMethodInsn(INVOKEVIRTUAL, "java/lang/Object","getClass","()Ljava/lang/Class;");
            // visitInsn(POP);
            //
            // Although i guess that it is the initialization code for the class to
            // which the lambda method belongs, ECJ doesn't generated such code.
            // In Javascript runtime, it is a completely unnecessary code,
            // so we should delete them unconditionally.
            if (match(DUP, INVOKEVIRTUAL, POP)) {
                current.remove(0);
                break;
            }

        case POP2:
            // One sequence of expressions was finished, so we must write out one remaining
            // operand. (e.g. Method invocation which returns some operands but it is not used ever)
            current.addExpression(current.remove(0));
            break;

        // 0
        case ICONST_0:
        case LCONST_0:
        case FCONST_0:
        case DCONST_0:
            current.addOperand(ZERO);
            break;

        // 1
        case ICONST_1:
        case LCONST_1:
        case FCONST_1:
        case DCONST_1:
            current.addOperand(ONE);
            break;

        // 2
        case ICONST_2:
        case FCONST_2:
            current.addOperand(2);
            break;

        // 3
        case ICONST_3:
            current.addOperand(3);
            break;

        // 4
        case ICONST_4:
            current.addOperand(4);
            break;

        // 5
        case ICONST_5:
            current.addOperand(5);
            break;

        // -1
        case ICONST_M1:
            current.addOperand(-1);
            break;

        // null
        case ACONST_NULL:
            current.addOperand(null); // not "null"
            break;

        // + operand
        case IADD:
        case LADD:
        case FADD:
        case DADD:
            current.join("+").enclose();
            break;

        // - operand
        case ISUB:
        case LSUB:
        case FSUB:
        case DSUB:
            current.join("-").enclose();
            break;

        // * operand
        case IMUL:
        case LMUL:
        case FMUL:
        case DMUL:
            current.join("*");
            break;

        // / operand
        case IDIV:
        case LDIV:
        case FDIV:
        case DDIV:
            current.join("/");
            break;

        // % operand
        case IREM:
        case LREM:
        case FREM:
        case DREM:
            current.join("%");
            break;

        // & operand
        case IAND:
        case LAND:
            current.join("&").enclose();
            break;

        // | operand
        case IOR:
        case LOR:
            current.join("|").enclose();
            break;

        // ^ operand
        case IXOR:
        case LXOR:
            current.join("^");
            break;

        // << operand
        case ISHL:
        case LSHL:
            current.join("<<");
            break;

        // >> operand
        case ISHR:
        case LSHR:
            current.join(">>");
            break;

        // >>> operand
        case IUSHR:
        case LUSHR:
            current.join(">>>");
            break;

        // negative operand
        case INEG:
        case LNEG:
        case FNEG:
        case DNEG:
            current.addOperand(new OperandExpression("-" + current.remove(0)));
            break;

        case RETURN:
            current.addExpression(Node.RETURN);

            // disconnect the next appearing node from the current node
            current = null;
            break;

        case IRETURN:
            if (match(JUMP, ICONST_0, IRETURN, LABEL, FRAME_SAME, ICONST_1, IRETURN) || match(JUMP, ICONST_0, IRETURN, LABEL, FRAME_APPEND, ICONST_1, IRETURN)) {
                current.remove(0);
                current.remove(0);
                current.remove(0);
                current.remove(0);

                mergeConditions(current);
                disposeNode(current);
            } else if (match(JUMP, ICONST_1, IRETURN, LABEL, FRAME_SAME, ICONST_0, IRETURN) || match(JUMP, ICONST_1, IRETURN, LABEL, FRAME_APPEND, ICONST_0, IRETURN)) {
                // merge the node sequence of logical expression
                current.remove(0);
                current.remove(0);
                current.remove(0);
                current.remove(0);

                mergeConditions(current);
                disposeNode(current);

                // invert the latest condition
                if (!current.stack.isEmpty()) {
                    current.peek(0).invert();
                }
            }

            Operand operand = current.remove(0);

            if (returnType == BOOLEAN_TYPE) {
                if (operand.toString().equals("0")) {
                    operand = new OperandExpression("false");
                } else if (operand.toString().equals("1")) {
                    operand = new OperandExpression("true");
                } else if (operand instanceof OperandAmbiguousZeroOneTernary) {
                    operand = operand.cast(boolean.class);
                }
            }

            current.addExpression(Return, operand);

            // disconnect the next appearing node from the current node
            current = null;
            break;

        case ARETURN:
        case LRETURN:
        case FRETURN:
        case DRETURN:
            current.addExpression(Return, current.remove(match(DUP, JUMP, ARETURN) ? 1 : 0));

            // disconnect the next appearing node from the current node
            current = null;
            break;

        // write array value by index
        case IALOAD:
            if (enumSwitchInvoked) {
                enumSwitchInvoked = false; // reset

                // enum switch table starts from 1, but Enum#ordinal starts from 0
                current.addOperand(current.remove(0) + "+1");
                break;
            }
        case AALOAD:
        case BALOAD:
        case LALOAD:
        case FALOAD:
        case DALOAD:
        case CALOAD:
        case SALOAD:
            current.addOperand(current.remove(1) + "[" + current.remove(0) + "]");
            break;

        // read array value by index
        case AASTORE:
        case BASTORE:
        case IASTORE:
        case LASTORE:
        case FASTORE:
        case DASTORE:
        case CASTORE:
        case SASTORE:
            Operand contextMaybeArray = current.remove(2);
            Operand value = current.remove(0);

            if (opcode == CASTORE) {
                // convert assign value (int -> char)
                value = value.cast(char.class);
            }

            if (contextMaybeArray instanceof OperandArray) {
                // initialization of syntax sugar
                ((OperandArray) contextMaybeArray).set(current.remove(0), value);
            } else {
                // read by index
                current.addExpression(contextMaybeArray, "[", current.remove(0), "]=", value.toString());
            }
            break;

        // read array length
        case ARRAYLENGTH:
            current.addOperand(current.remove(0) + ".length");
            break;

        // throw
        case ATHROW:
            current.addExpression("throw ", current.remove(0));

            // disconnect the next appearing node from the current node
            current = null;
            break;

        // numerical comparison operator for long, float and double primitives
        case LCMP:
        case DCMPL:
        case DCMPG:
        case FCMPL:
        case FCMPG:
            break; // ignore, because we should handle it in visitJumpInsn method

        case MONITORENTER:
            current.remove(0);
            synchronizer.add(current);
            break;

        case MONITOREXIT:
            synchronizer.add(current);
            break;

        case I2C:
            // cast int to char
            current.addOperand("String.fromCharCode(" + current.remove(0) + ")", char.class);
            break;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void visitIntInsn(int opcode, int operand) {
        // recode current instruction
        record(opcode);

        // The opcode of the instruction to be visited. This opcode is either BIPUSH, SIPUSH or
        // NEWARRAY.
        switch (opcode) {
        // When opcode is BIPUSH, operand value should be between Byte.MIN_VALUE and
        // Byte.MAX_VALUE.
        case BIPUSH:
            current.addOperand(operand);
            break;

        // When opcode is SIPUSH, operand value should be between Short.MIN_VALUE and
        // Short.MAX_VALUE.
        case SIPUSH:
            current.addOperand(operand);
            break;

        // When opcode is NEWARRAY, operand value should be one of Opcodes.T_BOOLEAN,
        // Opcodes.T_CHAR, Opcodes.T_FLOAT, Opcodes.T_DOUBLE, Opcodes.T_BYTE, Opcodes.T_SHORT,
        // Opcodes.T_INT or Opcodes.T_LONG.
        case NEWARRAY:
            Class type = null;

            switch (operand) {
            case T_INT:
                type = int[].class;
                break;

            case T_LONG:
                type = long[].class;
                break;

            case T_FLOAT:
                type = float[].class;
                break;

            case T_DOUBLE:
                type = double[].class;
                break;

            case T_BOOLEAN:
                type = boolean[].class;
                break;

            case T_BYTE:
                type = byte[].class;
                break;

            case T_CHAR:
                type = char[].class;
                break;

            case T_SHORT:
                type = short[].class;
                break;

            default:
                // If this exception will be thrown, it is bug of this program. So we must rethrow
                // the wrapped error in here.
                throw new Error();
            }

            Javascript.require(type);
            current.addOperand(new OperandArray(current.remove(0), type));
            break;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void visitInvokeDynamicInsn(String name, String description, Handle bsm, Object... bsmArgs) {
        Handle handle = (Handle) bsmArgs[1];
        Type functionalInterfaceType = (Type) bsmArgs[2];
        Type lambdaType = Type.getMethodType(handle.getDesc());
        Type callerType = Type.getMethodType(description);
        int parameterDiff = lambdaType.getArgumentTypes().length - functionalInterfaceType.getArgumentTypes().length;
        boolean useContext = callerType.getArgumentTypes().length - Math.max(parameterDiff, 0) == 1;

        // detect functional interface
        String interfaceClass = Javascript.computeClass(convert(callerType.getReturnType()));

        // detect lambda method
        Class lambdaClass = convert(handle.getOwner());
        String lambdaMethodName = '"' + Javascript.computeMethodName(lambdaClass, handle.getName(), handle.getDesc()) + '"';

        // build parameter from local environment
        StringJoiner parameters = new StringJoiner(",", "[", "]");

        for (int i = parameterDiff - 1; 0 <= i; i--) {
            parameters.add(current.remove(i).toString());
        }

        // detect context
        Object context = useContext ? current.remove(0) : "null";

        // decide lambda context
        Object holder = null;

        switch (handle.getTag()) {
        case H_INVOKESTATIC:
            holder = Javascript.computeClassName(lambdaClass);
            break;

        case H_INVOKESPECIAL:
        case H_INVOKEVIRTUAL:
            holder = context;
            break;

        case H_INVOKEINTERFACE:
            holder = context;
            break;

        case H_NEWINVOKESPECIAL:
            holder = Javascript.computeClassName(lambdaClass) + ".prototype";
            break;

        default:
            // If this exception will be thrown, it is bug of this program. So we must rethrow the
            // wrapped error in here.
            throw new Error();
        }

        // create lambda proxy class
        current.addOperand(Javascript.writeMethodCode(Proxy.class, "newLambdaInstance", Class.class, interfaceClass, NativeObject.class, holder, String.class, lambdaMethodName, Object.class, context, Object[].class, parameters.toString()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void visitJumpInsn(int opcode, Label label) {
        // If this jump instruction is used for assertion, we should skip it to erase compiler
        // generated extra code.
        if (assertJump) {
            assertJump = false; // reset flag
            return;
        }

        // recode current instruction
        record(JUMP);

        // search node
        Node node = getNode(label);

        switch (opcode) {
        case GOTO:
            connect(label);
            // disconnect the next appearing node from the current node
            current = null;
            return;

        case IFEQ: // == 0
            if (match(DCMPL, JUMP) || match(FCMPL, JUMP) || match(LCMP, JUMP)) {
                // for long, float and double
                current.condition(current.remove(1), EQ, current.remove(0), node);
            } else {
                // others
                current.condition(current.remove(0), EQ, ZERO, node);
            }
            break;

        case IFNE: // != 0
            if (match(DCMPL, JUMP) || match(FCMPL, JUMP) || match(LCMP, JUMP)) {
                // for long, float and double
                current.condition(current.remove(1), NE, current.remove(0), node);
            } else {
                // others
                current.condition(current.remove(0), NE, ZERO, node);
            }
            break;

        case IFGE: // => 0
            if (match(DCMPG, JUMP) || match(FCMPG, JUMP) || match(LCMP, JUMP)) {
                // for long, float and double
                current.condition(current.remove(1), GE, current.remove(0), node);
            } else {
                // others
                current.condition(current.remove(0), GE, ZERO, node);
            }
            break;

        case IFGT: // > 0
            if (match(DCMPG, JUMP) || match(FCMPG, JUMP) || match(LCMP, JUMP)) {
                // for long, float and double
                current.condition(current.remove(1), GT, current.remove(0), node);
            } else {
                // others
                current.condition(current.remove(0), GT, ZERO, node);
            }
            break;

        case IFLE: // <= 0
            if (match(DCMPL, JUMP) || match(FCMPL, JUMP) || match(LCMP, JUMP)) {
                // for long, float and double
                current.condition(current.remove(1), LE, current.remove(0), node);
            } else {
                // others
                current.condition(current.remove(0), LE, ZERO, node);
            }
            break;

        case IFLT: // < 0
            if (match(DCMPL, JUMP) || match(FCMPL, JUMP) || match(LCMP, JUMP)) {
                // for long, float and double
                current.condition(current.remove(1), LT, current.remove(0), node);
            } else {
                // others
                current.condition(current.remove(0), LT, ZERO, node);
            }
            break;

        case IFNULL: // object == null
            current.condition(current.remove(0), EQ, new OperandExpression("null"), node);
            break;

        case IFNONNULL: // object != null
            current.condition(current.remove(0), NE, new OperandExpression("null"), node);
            break;

        // ==
        case IF_ACMPEQ:
        case IF_ICMPEQ:
            current.condition(current.remove(1), EQ, current.remove(0), node);
            break;

        // !=
        case IF_ACMPNE:
        case IF_ICMPNE:
            current.condition(current.remove(1), NE, current.remove(0), node);
            break;

        case IF_ICMPGE: // int => int
            current.condition(current.remove(1), GE, current.remove(0), node);
            break;

        case IF_ICMPGT: // int > int
            current.condition(current.remove(1), GT, current.remove(0), node);
            break;

        case IF_ICMPLE: // int <= int
            current.condition(current.remove(1), LE, current.remove(0), node);
            break;

        case IF_ICMPLT: // int < int
            current.condition(current.remove(1), LT, current.remove(0), node);
            break;
        }
        connect(label);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void visitLabel(Label label) {
        // recode current instruction
        record(LABEL);

        // Basically, visitLabel method is called each expression. But the following patterns are
        // exception so we must deal with them.
        //
        // Logical Expression Result with Return
        // A logical expression result (e.g. return i == 1;) is represented as [ICONST_0, IRETURN,
        // LABEL, ICONST_1, IRETURN] in bytecode.
        //
        // Logical Expression Result
        // A logical expression result (e.g. boolean b = (i == 1);) is represented as [ICONST_0,
        // GOTO, LABEL, ICONST_1, LABEL] in bytecode.
        //
        // Ternary Operator
        // Ternary operator (e.g. int i = (j == 0) ? 0 : 1;) is represented as [LABEL,
        // THEN_EXPRESSION, GOTO, LABEL, ELSE_EXPRESSION, LABEL] in bytecode.

        // build new node
        current = connect(label);

        if (debugger.enable) {
            System.out.println("visit label " + current.id);
        }

        if (debugger.beforeLabel) {
            debugger.print("Before node" + current.id);
            debugger.print(nodes);
        }

        // store the node in appearing order
        nodes.add(current);

        if (1 < nodes.size()) {
            current.previous = nodes.get(nodes.size() - 2);

            if (current.previous.stack.size() != 0) {
                if (current.peek(0) != Node.END) {
                    mergeConditions(current);
                }
            }
        }

        if (debugger.afterLabel) {
            debugger.print("After node" + current.id);
            debugger.print(nodes);
        }
    }

    /**
     * Connect the current node and the specified labed node.
     * 
     * @param label
     * @return
     */
    private Node connect(Label label) {
        // search cached node
        Node node = getNode(label);

        if (current != null) {
            current.connect(node);
        }

        // API definition
        return node;
    }

    /**
     * <p>
     * Helper method to merge all conditional operands.
     * </p>
     */
    private void mergeConditions(Node node) {
        Set<Node> group = new HashSet();
        group.add(node);

        boolean found = false;

        // Decide target node
        Node target = node.previous;

        // Merge the sequencial conditional operands in this node from right to left.
        for (int i = 0; i < target.stack.size(); i++) {
            Operand operand = target.peek(i);

            if (operand instanceof OperandCondition) {
                OperandCondition condition = (OperandCondition) operand;

                if (!found) {
                    found = true;

                    // This is first operand condition.
                    group.add(condition.transition);

                    // Set next appearing node for grouping.
                    condition.next = node;
                } else if (group.contains(condition.transition) && target.peek(i - 1) instanceof OperandCondition) {
                    // Merge two adjucent conditional operands.
                    i--;

                    target.set(i, new OperandCondition(condition, (OperandCondition) target.remove(i)));
                } else {
                    return; // Stop here.
                }
            }
        }

        // Merge this node and the specified node.
        // Rearch the start of node
        if (target.previous != null) {
            Operand operand = target.previous.peek(0);

            if (operand instanceof OperandCondition) {
                OperandCondition condition = (OperandCondition) operand;

                if (group.contains(condition.transition)) {
                    debugger.print("dispose merged node " + target.id);
                    disposeNode(target);

                    // Merge recursively
                    mergeConditions(node);
                }
            }
        }
    }

    /**
     * <p>
     * This parameter must be a non null Integer, a Float, a Long, a Double a String (or a Type for
     * .class constants, for classes whose version is 49.0 or more).
     * </p>
     * 
     * @see org.objectweb.asm.MethodVisitor#visitLdcInsn(java.lang.Object)
     */
    @Override
    public void visitLdcInsn(Object constant) {
        if (constant instanceof String) {
            current.stack.add(new OperandString((String) constant));
        } else if (constant instanceof Type) {
            String className = ((Type) constant).getInternalName();

            // add class operand
            Class clazz = convert(className);

            if (CSS.class.isAssignableFrom(clazz)) {
                // support stylesheet class
                I.make(Stylist.class).register(clazz);

                current.addOperand('"' + Obfuscator.computeCSSName(clazz) + '"');
            } else {
                // support class literal in javascript runtime.
                current.addOperand(Javascript.computeClass(clazz));

                Javascript.require(clazz);
            }
        } else {
            current.addOperand(constant);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void visitLocalVariable(String name, String desc, String signature, Label start, Label end, int index) {
        // Compiler generated code (i.e. synthetic method) doesn't have local variable operand.
        // So we shouldn't use this method to salvage infomation.
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void visitMaxs(int maxStack, int maxLocals) {
        variables.max = maxLocals;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void visitMethodInsn(int opcode, String className, String methodName, String desc) {
        // recode current instruction
        record(opcode);

        // compute owner class
        Class owner = convert(className);

        // current processing script depends on the owner class
        Javascript.require(owner);

        // compute parameter types
        Type[] types = Type.getArgumentTypes(desc);
        Class[] parameters = new Class[types.length];

        for (int i = 0; i < types.length; i++) {
            parameters[i] = convert(types[i]);
        }

        // copy latest operands for this method invocation
        ArrayList<Operand> contexts = new ArrayList(parameters.length + 1);

        for (int i = 0; i < parameters.length; i++) {
            contexts.add(0, current.remove(0));
        }

        // write mode
        Class returnType = convert(Type.getReturnType(desc));
        boolean immediately = returnType == void.class;

        // retrieve translator for this method owner
        Translator translator = TranslatorManager.getTranslator(owner);

        switch (opcode) {
        // Invoke instance method; special handling for superclass constructor, private method,
        // and instance initialization method invocations
        case INVOKESPECIAL:
            // push "this" operand
            contexts.add(0, current.remove(0));

            // Analyze method argument
            if (!methodName.equals("<init>")) {
                if (owner == script.source) {
                    // private method invocation
                    current.addOperand(translator.translateMethod(owner, methodName, desc, parameters, contexts), returnType);
                } else {
                    // super method invocation
                    current.addOperand(translator.translateSuperMethod(owner, methodName, desc, parameters, contexts), returnType);
                }
            } else {
                // constructor
                if (countInitialization != 0) {
                    if (className.equals(Type.getType(Input.class).getInternalName())) {
                        String model = "";
                        String path = contexts.remove(1).toString();
                        int index = path.indexOf('.', path.startsWith("this.") ? 5 : 0);

                        if (index != -1) {
                            model = path.substring(0, index);
                            path = path.substring(index + 1);
                        }
                        contexts.add(new OperandExpression(Javascript.computeClass(ClassUtil.wrap(parameters[0]))));
                        contexts.add(new OperandExpression(model));
                        contexts.add(new OperandString(path));

                        current.addOperand(translator.translateConstructor(owner, "(Ljava/lang/Class;Ljava/lang/Object;Ljava/lang/String;)V", new Class[] {
                                Class.class, Object.class, String.class}, contexts), owner);
                    } else {
                        // instance initialization method invocation
                        current.addOperand(translator.translateConstructor(owner, desc, parameters, contexts), owner);
                    }

                    countInitialization--;

                    // don't write
                    immediately = false;
                } else {
                    if (className.equals("java/lang/Object")) {
                        // ignore
                    } else {
                        current.addOperand(translator.translateSuperMethod(owner, methodName, desc, parameters, contexts), returnType);
                    }
                }
            }
            break;

        case INVOKEVIRTUAL: // method call
        case INVOKEINTERFACE: // interface method call
            // push "this" operand
            contexts.add(0, current.remove(0));

            // translate
            current.addOperand(translator.translateMethod(owner, methodName, desc, parameters, contexts), returnType);
            break;

        case INVOKESTATIC: // static method call
            if (Switch.isEnumSwitchTable(methodName, desc)) {
                enumSwitchInvoked = true;
            } else {
                // Non-private static method which is called from child class have parent
                // class signature.
                while (!hasStaticMethod(owner, methodName, parameters)) {
                    owner = owner.getSuperclass();
                }
                // push class operand
                contexts.add(0, new OperandExpression(Javascript.computeClassName(owner)));

                // translate
                current.addOperand(translator.translateStaticMethod(owner, methodName, desc, parameters, contexts), returnType);
            }
            break;
        }

        // if this method (not constructor and not static initializer) return void, we must
        // write out the expression of method invocation immediatly.
        if (immediately && current.stack.size() != 0) {
            current.addExpression(current.remove(0));
        }

    }

    /**
     * <p>
     * Check static method.
     * </p>
     * 
     * @param owner
     * @param name
     * @param types
     * @return
     */
    private boolean hasStaticMethod(Class owner, String name, Class[] types) {
        if (owner == null) {
            return false;
        }

        try {
            Method method = owner.getDeclaredMethod(name, types);

            return Modifier.isStatic(method.getModifiers());
        } catch (NoSuchMethodException e) {
            return false;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void visitMultiANewArrayInsn(String desc, int dimension) {
        // remove needless operands
        for (int i = 0; i < dimension - 1; i++) {
            current.remove(0);
        }
        current.addOperand(new OperandArray(current.remove(0), convert(desc)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AnnotationVisitor visitParameterAnnotation(int arg0, String arg1, boolean arg2) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void visitTableSwitchInsn(int min, int max, Label defaults, Label... labels) {
        List<Node> nodes = new ArrayList();

        for (Label label : labels) {
            nodes.add(getNode(label));
        }

        int[] keys = new int[max - min + 1];

        for (int i = 0; i < keys.length; i++) {
            keys[i] = min + i;
        }
        current.createSwitch(getNode(defaults), keys, nodes);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void visitLookupSwitchInsn(Label defaults, int[] keys, Label[] labels) {
        List<Node> nodes = new ArrayList();

        for (Label label : labels) {
            nodes.add(getNode(label));
        }
        current.createSwitch(getNode(defaults), keys, nodes);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void visitTryCatchBlock(Label start, Label end, Label handler, String type) {
        tries.addTryCatchFinallyBlock(getNode(start), getNode(end), getNode(handler), convert(type));
    }

    /**
     * {@inheritDoc}
     */
    public void visitTypeInsn(int opcode, String type) {
        // recode current instruction
        record(opcode);

        switch (opcode) {
        case NEW:
            countInitialization++;

            current.addOperand("new " + Javascript.computeClassName(convert(type)));
            break;

        case ANEWARRAY:
            if (type.charAt(0) == '[') {
                type = "[" + type;
            } else {
                type = "[L" + type + ";";
            }
            current.addOperand(new OperandArray(current.remove(0), convert(type)));
            break;

        case CHECKCAST:

            break;

        case INSTANCEOF:
            Class clazz = convert(type);

            // load source
            Javascript.require(clazz);

            String code;

            if (clazz == Object.class || clazz == NativeObject.class) {
                code = current.remove(0) + " instanceof Object";
            } else if (clazz == String.class) {
                code = "boot.isString(" + current.remove(0) + ")";
            } else if (clazz.isInterface() || clazz.isArray()) {
                code = Javascript.writeMethodCode(Class.class, "isInstance", Javascript.computeClass(clazz), Object.class, current.remove(0));
            } else {
                code = current.remove(0) + " instanceof " + Javascript.computeClassName(clazz);
            }
            current.addOperand(code, boolean.class);
            break;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void visitVarInsn(int opcode, int position) {
        // recode current instruction
        record(opcode);

        // retrieve local variable name
        String variable = variables.name(position, opcode);

        switch (opcode) {
        case ILOAD:
        case FLOAD:
        case LLOAD:
        case DLOAD:
            if (match(INCREMENT, ILOAD) && current.peek(0) == Node.END) {
                String expression = current.peek(1).toString();

                if (expression.startsWith("++") || expression.startsWith("--")) {
                    if (expression.substring(2).equals(variable)) {
                        current.remove(0);
                        break;
                    }
                }
            }

        case ALOAD:
            current.addOperand(new OperandExpression(variable, variables.type(position)));
            break;

        case ASTORE:
            if (match(FRAME_SAME1, ASTORE) || match(FRAME_FULL, ASTORE)) {
                tries.assignVariableName(current, variable);
            }

        case ISTORE:
        case LSTORE:
        case FSTORE:
        case DSTORE:
            // Increment not-int type doesn't use Iinc instruction, so we must distinguish
            // increment from addition by pattern matching. Post increment code of non-int type
            // leaves characteristic pattern like the following.
            if (match(FLOAD, DUP, FCONST_1, FADD, FSTORE) || match(DLOAD, DUP2, DCONST_1, DADD, DSTORE) || match(LLOAD, DUP2, LCONST_1, LADD, LSTORE)) {
                current.remove(0);
                current.remove(0);

                current.addOperand(variable + "++");
            } else {
                if (current.peek(0) != null) {
                    // retrieve and remove it
                    Operand operand = current.remove(0, false);

                    // Enum#values produces special bytecode, so we must handle it by special way.
                    if (match(ASTORE, ICONST_0, ALOAD, ARRAYLENGTH, DUP, ISTORE)) {
                        enumValues[0] = current.remove(0);
                        enumValues[1] = current.remove(0);
                    }

                    // infer local variable type
                    variables.type(position).type(operand.infer().type());

                    OperandExpression assignment = new OperandExpression(variable + "=" + operand);

                    if (!operand.duplicated) {
                        current.addExpression(assignment);
                    } else {
                        operand.duplicated = false;

                        // Enum#values produces special bytecode,
                        // so we must handle it by special way.
                        if (match(ARRAYLENGTH, DUP, ISTORE, ANEWARRAY, DUP, ASTORE)) {
                            current.addOperand(enumValues[1]);
                            current.addOperand(enumValues[0]);
                        }

                        // duplicate pointer
                        current.addOperand(new OperandEnclose(assignment));
                    }
                }
            }
            break;
        }
    }

    /**
     * <p>
     * Create new node before the specified node.
     * </p>
     * 
     * @param next A index node.
     * @return A created node.
     */
    private final Node createNode(Node next) {
        Node created = new Node(counter++, debugger);

        // switch line number
        created.number = next.number;
        next.number = -1;

        // switch previous node
        Node previous = next.previous;
        created.previous = previous;
        next.previous = created;

        // switch incoming node
        ArrayList<Node> list = new ArrayList(next.incoming);

        for (Node incoming : list) {
            incoming.disconnect(next);
            incoming.connect(created);
        }

        // link
        created.connect(next);

        // insert to node list
        nodes.add(nodes.indexOf(next), created);

        // API definition
        return created;
    }

    /**
     * <p>
     * Helper method to dispose the specified node.
     * </p>
     */
    private final void disposeNode(Node target) {
        disposeNode(target, false);
    }

    /**
     * <p>
     * Helper method to dispose the specified node.
     * </p>
     */
    private final void disposeNode(Node target, boolean clearStack) {
        debugger.print("dispose" + target.id);
        debugger.print(nodes);

        int index = nodes.indexOf(target);

        if (0 < index && index + 1 < nodes.size()) {
            nodes.get(index + 1).previous = index < 1 ? null : nodes.get(index - 1);
        }

        Label label = labels.get(target);

        if (label != null) {
            labels.put(target.previous, label);
            label.info = target.previous;
        }

        // Merge the current processing node
        nodes.remove(target);

        // Remove it from outgoings of all previous-sibling nodes.
        for (Node node : labels.keySet()) {
            if (node != null) {
                node.incoming.remove(target);
                node.outgoing.remove(target);
            }
        }

        for (Node out : target.outgoing) {
            for (Node in : target.incoming) {
                if (!in.outgoing.contains(out)) {
                    in.outgoing.add(out);
                }
            }
        }

        for (Node in : target.incoming) {
            for (Node out : target.outgoing) {
                if (!out.incoming.contains(in)) {
                    out.incoming.add(in);
                }
            }
        }

        // Copy all operands to the previous node if needed
        if (!clearStack) {
            if (target.previous != null) {
                target.previous.stack.addAll(target.stack);
            }
        }

        // Delete all operands from the current processing node
        target.stack.clear();

        if (target == current) {
            current = target.previous;
        }

        // dispose empty node recursively
        if (target.previous != null && target.previous.stack.isEmpty()) {
            disposeNode(target.previous, clearStack);
        }
    }

    /**
     * <p>
     * Retrieve the asossiated node of the specified label.
     * </p>
     * 
     * @param label A label for node.
     * @return An asossiated and cached node.
     */
    private final Node getNode(Label label) {
        Node node = (Node) label.info;

        // search cached node
        if (node == null) {
            label.info = node = new Node(counter++, debugger);
            labels.put(node, label);
        }

        // API definition
        return node;
    }

    /**
     * Record the current instruction.
     */
    private final void record(int opcode) {
        records[recordIndex++] = opcode;

        if (recordIndex == 8) {
            recordIndex = 0; // loop index
        }
    }

    /**
     * <p>
     * Pattern matching for the recent instructions.
     * </p>
     * 
     * @param opcodes A sequence of opecodes to match.
     * @return A result.
     */
    private final boolean match(int... opcodes) {
        root: for (int i = 0; i < opcodes.length; i++) {
            int record = records[(recordIndex + i + 8 - opcodes.length) % 8];

            switch (opcodes[i]) {
            case ADD:
                switch (record) {
                case IADD:
                case LADD:
                case FADD:
                case DADD:
                    continue root;

                default:
                    return false;
                }

            case SUB:
                switch (record) {
                case ISUB:
                case LSUB:
                case FSUB:
                case DSUB:
                    continue root;

                default:
                    return false;
                }

            case CONSTANT_0:
                switch (record) {
                case ICONST_0:
                case LCONST_0:
                case FCONST_0:
                case DCONST_0:
                    continue root;

                default:
                    return false;
                }

            case CONSTANT_1:
                switch (record) {
                case ICONST_1:
                case LCONST_1:
                case FCONST_1:
                case DCONST_1:
                    continue root;

                default:
                    return false;
                }

            case DUPLICATE:
                switch (record) {
                case DUP:
                case DUP2:
                    continue root;

                default:
                    return false;
                }

            case DUPLICATE_X1:
                switch (record) {
                case DUP_X1:
                case DUP2_X1:
                    continue root;

                default:
                    return false;
                }

            case RETURNS:
                switch (record) {
                case RETURN:
                case IRETURN:
                case ARETURN:
                case LRETURN:
                case FRETURN:
                case DRETURN:
                    continue root;

                default:
                    return false;
                }

            default:
                if (record != opcodes[i]) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Convert parameter type to class.
     * 
     * @param type A parameter {@link Type}.
     * @return A parameter {@link Class}.
     */
    static final Class convert(Type type) {
        switch (type.getSort()) {
        case INT:
            return int.class;

        case Type.LONG:
            return long.class;

        case Type.FLOAT:
            return float.class;

        case Type.DOUBLE:
            return double.class;

        case CHAR:
            return char.class;

        case BYTE:
            return byte.class;

        case SHORT:
            return short.class;

        case BOOLEAN:
            return boolean.class;

        case VOID:
            return void.class;

        case ARRAY:
            return Array.newInstance(convert(type.getElementType()), new int[type.getDimensions()]).getClass();

        default:
            try {
                return Class.forName(type.getClassName());
            } catch (ClassNotFoundException e) {
                // If this exception will be thrown, it is bug of this program. So we must
                // rethrow the wrapped error in here.
                throw new Error(e);
            }
        }
    }

    /**
     * <p>
     * Helper method to convert the specified class name to {@link Class}.
     * </p>
     * 
     * @param className A fully qualified internal class name.
     * @return Java class.
     */
    static final Class convert(String className) {
        if (className == null) {
            return null;
        }

        try {
            return Class.forName(className.replace('/', '.'));
        } catch (ClassNotFoundException e) {
            // If this exception will be thrown, it is bug of this program. So we must rethrow the
            // wrapped error in here.
            throw new Error(e);
        }
    }

    /**
     * <p>
     * Manage local variables.
     * </p>
     * 
     * @version 2013/01/21 11:09:48
     */
    private static class LocalVariables {

        /** The current processing method is static or not. */
        private final boolean isStatic;

        /** The max size of variables. */
        private int max = 0;

        /** The ignorable variable index. */
        private final List<Integer> ignores = new ArrayList();

        /** The local type mapping. */
        private final Map<Integer, InferredType> types = new HashMap();

        /**
         * @param isStatic
         */
        private LocalVariables(boolean isStatic) {
            this.isStatic = isStatic;
        }

        /**
         * <p>
         * Compute the identified qualified local variable name for ECMAScript.
         * </p>
         * 
         * @param order An order by which this variable was declared.
         * @return An identified local variable name for ECMAScript.
         */
        private String name(int order) {
            return name(order, 0);
        }

        /**
         * <p>
         * Compute the identified qualified local variable name for ECMAScript.
         * </p>
         * 
         * @param order An order by which this variable was declared.
         * @return An identified local variable name for ECMAScript.
         */
        private String name(int order, int opcode) {
            // ignore long or double second index
            switch (opcode) {
            case LLOAD:
            case LSTORE:
            case DLOAD:
            case DSTORE:
                ignores.add(order + 1);
                break;
            }

            // order 0 means "this", but static method doesn't have "this" variable
            if (!isStatic) {
                order--;
            }

            if (order == -1) {
                return "this";
            }

            // Compute local variable name
            return Obfuscator.mung32(order);
        }

        /**
         * <p>
         * List up all valid variable names.
         * </p>
         * 
         * @return
         */
        private List<String> names() {
            List<String> names = new ArrayList();

            for (int i = isStatic ? 0 : 1; i < max; i++) {
                if (!ignores.contains(i)) {
                    names.add(name(i));
                }
            }
            return names;
        }

        /**
         * <p>
         * Find {@link InferredType} for the specified position.
         * </p>
         * 
         * @param position
         * @return
         */
        private InferredType type(int position) {
            InferredType type = types.get(position);

            if (type == null) {
                type = new InferredType();
                types.put(position, type);
            }
            return type;
        }
    }
}
