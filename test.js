boot.define("I",{$0:function(){boot.I.prototype.$1.call(this,"div");},$1:function(A){this.a=$("<"+A+">");}});boot.define("T",{$1:function(A,B,C){this.a=A;;this.b=B;this.c=C;},N:function(){return this.b.N();},H:function(A){A=this.b.H();if(this.c==0){return A.X();}else{return A.x();}},BK:function(){this.b.BK();},$0:function(A,B,C,D){boot.T.prototype.$1.call(this,A,B,C);}});boot.define("U",{$1:function(A,B){this.a=A;;this.b=0;this.c=B;},N:function(){return this.b<this.c.length;},H:function(){this.d=boot.M.BM(this.a)[this.c[this.b++]];return this.d;},BK:function(){if(this.b<=0){}else{this.a.BE(this.d);}},$0:function(A,B,C){boot.U.prototype.$1.call(this,A,B);}});boot.define("S",{$0:function(){},_BJ:function(A,B){return boot.S.BN(A,B);},_BO:function(A,B){return boot.S.BP(A,B);}});boot.define("R",{$0:function(){},Q:function(){return this.P()==0;},BB:function(A,B,C,D){B=0;D=A.G();l2:while (D.N()!=0) {C=D.H();if(this.BC(C)==0){}else{B=1;continue l2;}continue l2;}return B;},BD:function(A,B,C,D){B=0;D=this.G();l2:while (D.N()!=0) {C=D.H();if(A.S(C)!=0){}else{B=this.BE(C);continue l2;}continue l2;}return B;},BF:function(A,B,C,D){B=0;D=A.G();l2:while (D.N()!=0) {C=D.H();if(this.BE(C)==0){}else{B=1;continue l2;}continue l2;}return B;},BG:function(A,B,C){C=A.G();l1:while (C.N()!=0) {B=C.H();if(this.S(B)!=0){}else{return 0;}continue l1;}return 1;},BH:function(){return this.BI(new Array(0));},BI:function(A,B,C,D,E){B=this.P();if(A.length>=B){}else{A=boot.S.BJ(A.getClass(),B);}C=this.G();D=0;l6:while (C.N()!=0) {E=C.H();A[D]=E;++D;continue l6;}return A;}});boot.define("Q",boot.R,{$0:function(){boot.R.prototype.$0.call(this);}});boot.define("M",boot.Q,{$0:function(){boot.Q.prototype.$0.call(this);this.a=0;this.b={};},P:function(){return this.a;},S:function(A){return this.BL(A) in this.b;},BC:function(A,B){B=this.BL(A);if(B in this.b==0){this.a=this.a+1;this.b[B]=A;return 1;}else{return 0;}},BE:function(A,B){B=this.BL(A);if(B in this.b!=0){this.a=this.a-1;delete this.b[B];return 1;}else{return 0;}},y:function(){this.a=0;this.b=[];},G:function(){return new boot.U(this,this.b.keys(),null,0);},BL:function(A){return (A==null?-1:A.hashCode());},W:function(A){return this.b[this.BL(A)];},Y:function(A,B,C){B=null;C=this.BL(A);if(C in this.b==0){this.a=this.a+1;}else{B=this.b[C];}this.b[C]=A;return B;},u:function(A,B,C){B=null;C=this.BL(A);if(C in this.b==0){}else{B=this.b[C];this.a=this.a-1;delete this.b[C];}return B;},_BM:function(A){return A.b;}});boot.define("O",boot.Q,{$1:function(A){this.a=A;boot.Q.prototype.$0.call(this);},P:function(){return boot.J.BA(this.a).P();},S:function(A){return boot.J.BA(this.a).S(A);},G:function(){return new boot.T(this.a,boot.J.BA(this.a).G(),1,null,0);},BC:function(A){return 0;},BE:function(A){return boot.J.BA(this.a).BE(A);},y:function(){boot.J.BA(this.a).y();},$0:function(A,B){boot.O.prototype.$1.call(this,A);}});boot.define("N",{$1:function(A,B){this.a=A;this.b=B;},x:function(){return this.a;},X:function(){return this.b;},BQ:function(A,B){B=this.b;this.b=A;return B;},hashCode:function(){return this.a.hashCode();},$0:function(A,B,C){boot.N.prototype.$1.call(this,A,B);}});boot.define("P",boot.R,{$1:function(A){this.a=A;boot.R.prototype.$0.call(this);},P:function(){return boot.J.BA(this.a).P();},S:function(A){return this.a.T(A);},G:function(){return new boot.T(this.a,boot.J.BA(this.a).G(),0,null,0);},BC:function(A){return 0;},BE:function(A,B,C){B=this.G();l2:while (B.N()!=0) {C=B.H();if(C!=A){}else{B.BK();return 1;}continue l2;}return 0;},y:function(){boot.J.BA(this.a).y();},$0:function(A,B){boot.P.prototype.$1.call(this,A);}});boot.define("J",{$0:function(){this.a=new boot.M(0);},P:function(){return this.a.P();},Q:function(){return this.a.Q();},R:function(A){return this.a.S(A);},T:function(A,B,C){C=this.U().G();l1:while (C.N()!=0) {B=C.H();if(B!=A){}else{return 1;}continue l1;}return 0;},V:function(A,B){B=this.a.W(A);return (B==null?null:B.X());},M:function(A,B,C){C=this.a.Y(new boot.N(A,B,null,0));if(C!=null){return C.X();}else{return null;}},Z:function(A,B){B=this.a.u(A);if(B!=null){return B.X();}else{return null;}},v:function(A,B,C){C=A.w().G();l1:for (;C.N()!=0;this.M(B.x(),B.X())) {B=C.H();}},y:function(){this.a.y();},z:function(){return new boot.O(this,null,0);},U:function(){return new boot.P(this,null,0);},w:function(){return this.a;},_BA:function(A){return A.a;}});boot.define("V",{$0:function(A){this.a=A;this.b=0;},N:function(){return this.b<this.a.size();},BR:function(){return $(this.a.get(this.b++));},BK:function(){},H:function(){return this.BR();}});boot.defineNative("Document",{$0:function(){},createElement:function(A){return this.createElement(A);}});boot.defineNative("jQuery",{$0:function(){},K:function(A){return $(document.createElement(A)).appendTo(this);},I:function(A){return this.K("span").addClass(A);},G:function(){return new boot.V(this,0);}});boot.define("K",{$0:function(A,B){this.a=A;this.b=B;},handler:function(A,B,C,D){B=this.b.val().toLowerCase().replace(/\s/g,"");D=boot.H.O(this.a).w().G();l2:while (D.N()!=0) {C=D.H();if(this.a.L(C.x()).toLowerCase().indexOf(B) != -1==0){C.X().addClass("d");continue l2;}else{C.X().removeClass("d");continue l2;}}}});boot.define("H",boot.I,{$0:function(){boot.I.prototype.$0.call(this);this.b=new boot.J(0);this.c=this.F();},E:function(A,B,C,D,E){this.a.css("line-height","0").css("width","700px").css("margin","0 auto");B=$("<input type='text'>");B.appendTo(this.a);B.addClass("a").css("display","block");B.keyup(new boot.K(this,B,0));D=this.c.G();l6:for (;D.N()!=0;this.b.M(C,E)) {C=D.H();E=this.a.I("b").css("background-image","url("+this.J(C)+")");E.K("span").addClass("c").text(this.L(C));}A.append(this.a);},_O:function(A){return A.b;}});boot.define("G",{$1:function(A,B){this.a=A;;this.b=B.K("ul").addClass("h");},D:function(A,B,C){C=this.b.K("li").addClass("i");C.K("a").addClass("g").attr("href",B).text(A);return new boot.G(this.a,C,1);},$0:function(A,B,C){boot.G.prototype.$1.call(this,A,B);}});boot.define("F",{$0:function(){this.a=$("#Header").addClass("e");},D:function(A,B,C){C=this.a.K("li").addClass("f");C.K("a").addClass("g").attr("href",B).text(A);return new boot.G(this,C,null,0);}});boot.define("X",{_:function(){boot.X.b=new boot.J(0);boot.X.c=new boot.X("Ahri",0);boot.X.d=new boot.X("Akali",0);boot.X.e=new boot.X("Alistar",0);boot.X.f=new boot.X("Amumu",0);boot.X.g=new boot.X("Ashe",0);boot.X.h=new boot.X("Blitzcrank",0);boot.X.i=new boot.X("Brand",0);boot.X.j=new boot.X("Caitlyn",0);boot.X.k=new boot.X("Cassiopeia",0);boot.X.l=new boot.X("Chogath",0);boot.X.m=new boot.X("Corki",0);boot.X.n=new boot.X("Darius",0);boot.X.o=new boot.X("Diana",0);boot.X.p=new boot.X("Dr.Mundo",0);boot.X.ba=new boot.X("Elise",0);boot.X.bb=new boot.X("Evelynn",0);boot.X.bc=new boot.X("Ezreal",0);boot.X.bd=new boot.X("Fiddlesticks",0);boot.X.be=new boot.X("Fiora",0);boot.X.bf=new boot.X("Fizz",0);boot.X.bg=new boot.X("Galio",0);boot.X.bh=new boot.X("Gangplank",0);boot.X.bi=new boot.X("Garen",0);boot.X.bj=new boot.X("Gragas",0);boot.X.bk=new boot.X("Graves",0);boot.X.bl=new boot.X("Hecarim",0);boot.X.bm=new boot.X("Heimerdinger",0);boot.X.bn=new boot.X("Irelia",0);boot.X.bo=new boot.X("Janna",0);boot.X.bp=new boot.X("Jarvan IV",0);boot.X.ca=new boot.X("Jax",0);boot.X.cb=new boot.X("Jayce",0);boot.X.cc=new boot.X("Karma",0);boot.X.cd=new boot.X("Karthus",0);boot.X.ce=new boot.X("Kassadin",0);boot.X.cf=new boot.X("Katarina",0);boot.X.cg=new boot.X("Kayle",0);boot.X.ch=new boot.X("Kennen",0);boot.X.ci=new boot.X("Kha'zix",0);boot.X.cj=new boot.X("Kog'maw",0);boot.X.ck=new boot.X("LeBlanc",0);boot.X.cl=new boot.X("Lee Sin",0);boot.X.cm=new boot.X("Leona",0);boot.X.cn=new boot.X("Lulu",0);boot.X.co=new boot.X("Lux",0);boot.X.cp=new boot.X("Malphite",0);boot.X.da=new boot.X("Maokai",0);boot.X.db=new boot.X("Master Yi",0);boot.X.dc=new boot.X("Miss Fortune",0);boot.X.dd=new boot.X("Mordekaiser",0);boot.X.de=new boot.X("Morgana",0);boot.X.df=new boot.X("Nami",0);boot.X.dg=new boot.X("Nasus",0);boot.X.dh=new boot.X("Nautilus",0);boot.X.di=new boot.X("Nidalee",0);boot.X.dj=new boot.X("Nocturne",0);boot.X.dk=new boot.X("Nunu",0);boot.X.dl=new boot.X("Olaf",0);boot.X.dm=new boot.X("Orianna",0);boot.X.dn=new boot.X("Pantheon",0);boot.X.dp=new boot.X("Poppy",0);boot.X.ea=new boot.X("Rammus",0);boot.X.eb=new boot.X("Renekton",0);boot.X.ec=new boot.X("Rengar",0);boot.X.ed=new boot.X("Riven",0);boot.X.ee=new boot.X("Rumble",0);boot.X.ef=new boot.X("Ryze",0);boot.X.eg=new boot.X("Sejuani",0);boot.X.eh=new boot.X("Shaco",0);boot.X.ei=new boot.X("Shen",0);boot.X.ej=new boot.X("Shyvana",0);boot.X.ek=new boot.X("Singed",0);boot.X.el=new boot.X("Sion",0);boot.X.em=new boot.X("Sivir",0);boot.X.en=new boot.X("Skarner",0);boot.X.eo=new boot.X("Sona",0);boot.X.ep=new boot.X("Soraka",0);boot.X.fa=new boot.X("Swain",0);boot.X.fb=new boot.X("Syndra",0);boot.X.fc=new boot.X("Talon",0);boot.X.fd=new boot.X("Taric",0);boot.X.fe=new boot.X("Teemo",0);boot.X.ff=new boot.X("Tristana",0);boot.X.fg=new boot.X("Trundle",0);boot.X.fh=new boot.X("Tryndamere",0);boot.X.fi=new boot.X("Twisted Fate",0);boot.X.fj=new boot.X("Twitch",0);boot.X.fk=new boot.X("Udyr",0);boot.X.fl=new boot.X("Urgot",0);boot.X.fm=new boot.X("Varus",0);boot.X.fn=new boot.X("Vayne",0);boot.X.fo=new boot.X("Veigar",0);boot.X.fp=new boot.X("Vi",0);boot.X.ga=new boot.X("Viktor",0);boot.X.gb=new boot.X("Vladimir",0);boot.X.gc=new boot.X("Volibear",0);boot.X.gd=new boot.X("Warwick",0);boot.X.ge=new boot.X("Wukong",0);boot.X.gf=new boot.X("Xerath",0);boot.X.gg=new boot.X("Xin Zhao",0);boot.X.gh=new boot.X("Yorick",0);boot.X.gi=new boot.X("Zed",0);boot.X.gj=new boot.X("Ziggs",0);boot.X.gk=new boot.X("Zilean",0);boot.X.gl=new boot.X("Zyra",0);},$0:function(A){this.a=A;this.gm=this.BV().toLowerCase();boot.X.b.M(A,this);},BV:function(){return this.a.replace(/\./g,"").replace(/\s/g,"").replace(/\'/g,"");},_BW:function(A){return boot.X.b.V(A);},_BS:function(){return boot.X.b.U();}});boot.define("D",boot.H,{$0:function(A){this.d=A;boot.H.prototype.$0.call(this);},F:function(){return boot.X.BS();},BT:function(A){return A.a;},BU:function(A){return "src/main/resources/teemowork/icon/"+A.BV()+".png";},L:function(A){return this.BT(A);},J:function(A){return this.BU(A);}});boot.define("B",{$0:function(){},B:function(A){}});boot.define("A",boot.B,{$0:function(){boot.B.prototype.$0.call(this);this.a=new boot.D(this,0);},C:function(){return "E";},A:function(A,B,C){console.log(history);$("body").css("padding","0px 10%");A=$("#Content");B=new boot.F(0);B.D("< ^ v ^ > Teemowork","test.html");B.D("Patch","#");C=B.D("Data","#");C.D("Champion","#");C.D("Item","#");C.D("Mastery","#");C.D("Rune","#");B.D("Builder","#");B.D("About","#");B.D("Contact","#");this.a.E(A);}});