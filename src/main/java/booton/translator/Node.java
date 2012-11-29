/*
 * Copyright (C) 2009 Nameless Production Committee.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package booton.translator;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import booton.translator.JavaMethodCompiler.TryBlock;

/**
 * @version 2009/08/05 14:51:59
 */
class Node {

    static final Operand END = new OperandExpression(";");

    /** The identified label for this node. */
    final int id;

    /** The actual operand stack. */
    final LinkedList<Operand> stack = new LinkedList();

    /** The node list. */
    final List<Node> incoming = new ArrayList();

    /** The node list. */
    final List<Node> outgoing = new ArrayList();

    /** The node list. */
    final List<Node> backedges = new ArrayList();

    Node previous = null;

    /** The dominator node. */
    private Node dominator;

    /** The finally blocks. */
    private List<TryBlock> catcheTries = new ArrayList();

    /** The finally blocks. */
    private Deque<TryBlock> catches = new ArrayDeque();

    /** The finally blocks. */
    private List<TryBlock> finallyTries = new ArrayList();

    /** The finally blocks. */
    private Deque<TryBlock> finallies = new ArrayDeque();

    private int stoppable = 0;

    /** The following node. */
    Node follower;

    /** The flag whether this node has already written or not. */
    private boolean written = false;

    /**
     * @param label
     */
    Node(int id) {
        this.id = id;
    }

    /**
     * <p>
     * Helper method to add new operand to the top of operands stack.
     * </p>
     * 
     * @param operand A new operand to add.
     */
    final void addOperand(Object operand) {
        if (operand instanceof Operand) {
            stack.add((Operand) operand);
        } else if (operand instanceof Integer) {
            stack.add(new OperandNumber((Integer) operand));
        } else {
            stack.add(new OperandExpression(operand));
        }
    }

    /**
     * @param operands
     */
    final void addExpression(Object... operands) {
        for (Object operand : operands) {
            addOperand(operand);
        }
        stack.add(END);
    }

    /**
     * <p>
     * Helper method to remove the operand which is stored in the specified index from the operands
     * stack.
     * </p>
     * 
     * @param index An index that you want to remove from the operands stack.
     * @return A removed operand.
     */
    final Operand remove(int index) {
        // Calculate index
        index = stack.size() - 1 - index;

        if (index < 0) {
            // Remove operand from the previous node if we can.
            //
            // calculated = stack.size() - 1 - index
            // index - stack.size() = -calculated - 1;
            return previous == null ? null : previous.remove(-index - 1);
        }

        // Retrieve and remove it
        Operand operand = stack.remove(index);
        // if (operand instanceof OperandCondition) {
        // throw new Error(operand.toString());
        // }
        if (operand.duplicated) {
            operand.duplicated = false;

            // Duplicate pointer
            stack.add(index, operand);
        }

        // API definition
        return operand;
    }

    /**
     * Helper method to join latest two operands.
     * 
     * @param separator
     */
    final void join(String separator) {
        // calculate index
        int index = stack.size() - 2;

        // retrieve it
        Operand operand = stack.get(index);

        // copy operand if needed
        if (operand.duplicated) {
            operand.duplicated = false;

            // duplicate pointer
            stack.add(index - 1 == -1 ? 0 : index - 1, operand);
        }

        // join latest operands
        stack.add(new OperandExpression(remove(1) + separator + remove(0)));
    }

    /**
     * <p>
     * Set catch block for this node.
     * <p>
     * 
     * @param block
     */
    final void addCatch(TryBlock block) {
        catches.add(block);

        // block.start.stoppable += block.start.incoming.size();

        //
        for (TryBlock item : catcheTries) {
            if (item.base == block.base && item.end == block.end) {
                return;
            }
        }

        catcheTries.add(block);
        block.end.stoppable++;
    }

    /**
     * <p>
     * Set finally block for this node.
     * <p>
     * 
     * @param block
     */
    final void addFinally(TryBlock block) {
        for (TryBlock item : finallyTries) {
            if (item.start == block.start) {
                return;
            }
        }

        finallies.add(block);
        finallyTries.add(block);

        block.start.stoppable += block.start.incoming.size();
        block.end.stoppable += block.end.incoming.size();

    }

    /**
     * Helper method to check whether the specified node dominate this node or not.
     * 
     * @param dominator A dominator node.
     * @return A result.
     */
    private boolean hasDominator(Node dominator) {
        Node current = this;

        while (current != null) {
            if (current == dominator) {
                return true;
            }
            current = current.getDominator();
        }

        // Not Found
        return false;
    }

    /**
     * Compute the immediate dominator of this node.
     * 
     * @return A dominator node. If this node is root, <code>null</code>.
     */
    private Node getDominator() {
        // check cache
        if (dominator == null) {
            // We must search a immediate dominator.
            //
            // At first, we can ignore the older incoming nodes.
            List<Node> candidates = new CopyOnWriteArrayList(incoming);

            // compute backedges
            for (Node node : candidates) {
                if (backedges.contains(node)) {
                    candidates.remove(node);
                }
            }

            int size = candidates.size();

            switch (size) {
            case 0: // this is root node
                dominator = null;
                break;

            case 1: // only one incoming node
                dominator = candidates.get(0);
                break;

            default: // multiple incoming nodes
                Node candidate = candidates.get(0);

                while (true) {
                    boolean result = true;

                    for (int i = 1; i < size; i++) {
                        if (!candidates.get(i).hasDominator(candidate)) {
                            result = false;
                            break;
                        }
                    }

                    if (result) {
                        dominator = candidate;
                        break;
                    } else {
                        if (candidate == null) {
                            System.out.println("current " + id + "       ");
                        }
                        candidate = candidate.getDominator();
                    }
                }
                break;
            }
        }

        // API definition
        return dominator;
    }

    /**
     * Helper method to search the nearest common dominator for this node and the specified node.
     * 
     * @param node A target node.
     * @return A dominator. If it is not found, <code>null</code>.
     */
    private Node getNearestCommonDominator(Node node) {
        Node current = this;

        while (current != null) {
            if (node.hasDominator(current)) {
                return current;
            }
            current = current.getDominator();
        }

        return null;
    }

    /**
     * <p>
     * Write script fragment.
     * </p>
     * 
     * @param buffer
     */
    void write(ScriptBuffer buffer) {
        if (!written) {
            written = true;

            // check try-catch-finally
            for (int i = 0; i < Math.max(catcheTries.size(), finallyTries.size()); i++) {
                buffer.append("try{");
            }

            int outs = outgoing.size();
            int backs = backedges.size();

            if (outs == 0) {
                // end node
                buffer.append(this);
            } else if (outs == 1) {
                // do while or normal
                if (backs == 0) {
                    // normal node with follower
                    buffer.append(this);
                    process(outgoing.get(0), buffer);
                } else {
                    // do while

                    // setup condition expression node
                    Node condition = backedges.get(0);
                    condition.written = true;

                    // setup actual do-while block and its following node

                    condition.follower = condition.outgoing.get(1);

                    // write script fragment
                    buffer.append("l", condition.id, " : do {");
                    buffer.append(this);
                    process(this.outgoing.get(0), buffer);
                    buffer.append("} while (", condition, ")");
                    condition.process(condition.follower, buffer);
                }
            } else if (outs == 2) {
                // while, for or if
                if (backs == 0) {
                    // if
                    OperandCondition ccc = (OperandCondition) stack.peekLast();

                    if (ccc != null && ccc.next == outgoing.get(0)) {
                        ccc.invert();
                    }

                    if (outgoing.get(0).incoming.size() == 1) {
                        buffer.append("if (", this, ") {");
                        process(outgoing.get(0), buffer);
                        buffer.append("} else {");
                        process(outgoing.get(1), buffer);
                        buffer.append("}");
                        process(follower, buffer);
                    } else {
                        buffer.append("if (", this, ") {");
                        buffer.append("} else {");
                        process(outgoing.get(1), buffer);
                        buffer.append("}");
                        process(outgoing.get(0), buffer);
                    }
                } else if (backs == 1) {
                    // while or for
                    if (backedges.get(0).outgoing.size() == 1) {
                        // for

                        // setup update expression node
                        Node update = backedges.get(0);
                        update.written = true;

                        // literalize
                        if (update.stack.peekLast() == END) {
                            update.remove(0);
                        }

                        // setup following node
                        follower = outgoing.get(1);
                        System.out.println(this.getClass().getName() + "#" + System.identityHashCode(this));
                        System.out.println(stack.size());
                        // write script fragment
                        buffer.append("l", id, " : for (;", this, ";", update, ") {");
                        process(outgoing.get(0), buffer);
                        buffer.append("}");
                        process(follower, buffer);
                    } else {
                        // while with break only

                        // setup following node
                        follower = outgoing.get(1);

                        // write script fragment
                        buffer.append("l", id, " : while (", this, ") {");
                        process(outgoing.get(0), buffer);
                        buffer.append("}");
                        process(follower, buffer);
                    }
                } else {
                    // while with continue and break

                    // setup following node
                    follower = outgoing.get(1);

                    // write script fragment
                    buffer.append("l", id, " : while (", this, ") {");
                    process(outgoing.get(0), buffer);
                    buffer.append("}");
                    process(follower, buffer);
                }
            }

            // check try-catch-finally
            if (catches.size() != 0 || finallies.size() != 0) {
                Node end = null;

                // catch
                if (catches.size() != 0) {
                    buffer.append("} catch ($) {");

                    Iterator<TryBlock> iterator = catches.descendingIterator();

                    while (iterator.hasNext()) {
                        TryBlock block = iterator.next();

                        buffer.append("if ($ instanceof " + block.exception + ") {");
                        block.start.write(buffer);
                        buffer.append("}");

                        end = block.end;
                        // process2(end, buffer);
                    }
                    buffer.append("}");
                }

                // finally
                if (finallies.size() != 0) {
                    Iterator<TryBlock> iterator = finallies.descendingIterator();

                    while (iterator.hasNext()) {
                        TryBlock block = iterator.next();

                        if (catches.size() == 0) {
                            buffer.append("} finally {");
                        } else {
                            buffer.append(" finally {");
                        }

                        block.start.write(buffer);
                        buffer.append("}");

                        end = block.end;
                        process2(end, buffer);
                    }
                }

                process2(end, buffer);
                // root.start.process(root.end, buffer);
            }
        }
    }

    private void process2(Node dest, ScriptBuffer buffer) {
        if (dest != null) {
            if (dest.stoppable != 0) {
                dest.stoppable--;
            } else {
                dest.write(buffer);
            }
        }
    }

    /**
     * Helper method to process script writing.
     * 
     * @param dest
     * @param buffer
     */
    private void process(Node dest, ScriptBuffer buffer) {
        if (dest != null) {
            if (dest.stoppable != 0) {
                System.out.println("stop node " + dest.id);
                dest.stoppable--;
                return;
            }

            Node dominator = dest.getDominator();

            if (dominator == null || dominator == this) {
                // normal process
                dest.write(buffer);
                return;
            }

            if (hasDominator(dest)) {
                buffer.append("continue l", dest.id, ";");
                return;
            }

            if (dominator.backedges.size() == 0) {
                // stop here
                dest.getDominator().follower = dest;
                System.out.println("stop  " + dest.id + "   current " + this.id + "     domi " + dominator.id);
            } else {
                buffer.append("break l", dominator.id, ";");
            };
        }
    }

    // /**
    // * Helper method to process script writing.
    // *
    // * @param dest
    // * @param buffer
    // */
    // private void process(ScriptBuffer buffer) {
    // Node dest = follower;
    //
    // if (dest != null) {
    // Node dominator = dest.getDominator();
    //
    // if (dominator == null || dominator == this) {
    // // normal process
    // dest.write(buffer);
    // return;
    // }
    //
    // if (hasDominator(dest)) {
    // buffer.append("continue l", dest.id, ";");
    // return;
    // }
    //
    // if (dominator.backedges.size() == 0) {
    // // stop here
    // dest.getDominator().follower = dest;
    // } else {
    // buffer.append("break l", dominator.id, ";");
    // };
    // }
    // }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        for (Operand operand : stack) {
            builder.append(operand);
        }
        return builder.toString();
    }
}
