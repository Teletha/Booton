boot.define("o",{$0:function(){},_CI:function(a,b){return boot.o.CL(a,b);},_CM:function(a,b){return boot.o.CN(a,b);}});boot.define("n",{$0:function(){},BB:function(){return this.BA()==0;},CA:function(a,b,c,d){b=0;d=a.D();l2:while (d.F()!=0) {c=d.E();if(this.CB(c)==0){}else{b=1;continue l2;}continue l2;}return b;},CC:function(a,b,c,d){b=0;d=this.D();l2:while (d.F()!=0) {c=d.E();if(a.BD(c)!=0){}else{b=this.CD(c);continue l2;}continue l2;}return b;},CE:function(a,b,c,d){b=0;d=a.D();l2:while (d.F()!=0) {c=d.E();if(this.CD(c)==0){}else{b=1;continue l2;}continue l2;}return b;},CF:function(a,b,c){c=a.D();l1:while (c.F()!=0) {b=c.E();if(this.BD(b)!=0){}else{return 0;}continue l1;}return 1;},CG:function(){return this.CH(new Array(0));},CH:function(a,b,c,d,e){b=this.BA();if(a.length>=b){}else{a=boot.o.CI(a.getClass(),b);}c=this.D();d=0;l6:while (c.F()!=0) {e=c.E();a[d]=e;++d;continue l6;}return a;}});boot.define("m",boot.n,{$0:function(){boot.n.prototype.$0.call(this);}});boot.define("p",{$1:function(a,b){this.a=a;;this.b=0;this.c=b;},F:function(){return this.b<this.c.length;},E:function(){this.d=boot.i.CK(this.a)[this.c[this.b++]];return this.d;},CO:function(){if(this.b<=0){}else{this.a.CD(this.d);}},$0:function(a,b,c){boot.p.prototype.$1.call(this,a,b);}});boot.define("i",boot.m,{$0:function(){boot.m.prototype.$0.call(this);this.a=0;this.b={};},BA:function(){return this.a;},BD:function(a){return this.CJ(a) in this.b;},CB:function(a,b){b=this.CJ(a);if(b in this.b==0){this.a=this.a+1;this.b[b]=a;return 1;}else{return 0;}},CD:function(a,b){b=this.CJ(a);if(b in this.b!=0){this.a=this.a-1;delete this.b[b];return 1;}else{return 0;}},BN:function(){this.a=0;this.b=[];},D:function(){return new boot.p(this,this.b.keys(),null,0);},CJ:function(a){return (a==null?-1:a.hashCode());},BF:function(a){return this.b[this.CJ(a)];},BH:function(a,b,c){b=null;c=this.CJ(a);if(c in this.b==0){this.a=this.a+1;}else{b=this.b[c];}this.b[c]=a;return b;},BJ:function(a,b,c){b=null;c=this.CJ(a);if(c in this.b==0){}else{b=this.b[c];this.a=this.a-1;delete this.b[c];}return b;},_CK:function(a){return a.b;}});boot.define("ba",{$1:function(a,b,c){this.a=a;;this.b=b;this.c=c;},F:function(){return this.b.F();},E:function(a){a=this.b.E();if(this.c==0){return a.BG();}else{return a.BM();}},CO:function(){this.b.CO();},$0:function(a,b,c,d){boot.ba.prototype.$1.call(this,a,b,c);}});boot.define("l",boot.n,{$1:function(a){this.a=a;boot.n.prototype.$0.call(this);},BA:function(){return boot.h.BP(this.a).BA();},BD:function(a){return this.a.BE(a);},D:function(){return new boot.ba(this.a,boot.h.BP(this.a).D(),0,null,0);},CB:function(a){return 0;},CD:function(a,b,c){b=this.D();l2:while (b.F()!=0) {c=b.E();if(c!=a){}else{b.CO();return 1;}continue l2;}return 0;},BN:function(){boot.h.BP(this.a).BN();},$0:function(a,b){boot.l.prototype.$1.call(this,a);}});boot.define("k",boot.m,{$1:function(a){this.a=a;boot.m.prototype.$0.call(this);},BA:function(){return boot.h.BP(this.a).BA();},BD:function(a){return boot.h.BP(this.a).BD(a);},D:function(){return new boot.ba(this.a,boot.h.BP(this.a).D(),1,null,0);},CB:function(a){return 0;},CD:function(a){return boot.h.BP(this.a).CD(a);},BN:function(){boot.h.BP(this.a).BN();},$0:function(a,b){boot.k.prototype.$1.call(this,a);}});boot.define("j",{$1:function(a,b){this.a=a;this.b=b;},BM:function(){return this.a;},BG:function(){return this.b;},CP:function(a,b){b=this.b;this.b=a;return b;},hashCode:function(){return this.a.hashCode();},$0:function(a,b,c){boot.j.prototype.$1.call(this,a,b);}});boot.define("h",{$0:function(){this.a=new boot.i(0);},BA:function(){return this.a.BA();},BB:function(){return this.a.BB();},BC:function(a){return this.a.BD(a);},BE:function(a,b,c){c=this.P().D();l1:while (c.F()!=0) {b=c.E();if(b!=a){}else{return 1;}continue l1;}return 0;},O:function(a,b){b=this.a.BF(a);return (b==null?null:b.BG());},M:function(a,b,c){c=this.a.BH(new boot.j(a,b,null,0));if(c!=null){return c.BG();}else{return null;}},BI:function(a,b){b=this.a.BJ(a);if(b!=null){return b.BG();}else{return null;}},BK:function(a,b,c){c=a.BL().D();l1:for (;c.F()!=0;this.M(b.BM(),b.BG())) {b=c.E();}},BN:function(){this.a.BN();},BO:function(){return new boot.k(this,null,0);},P:function(){return new boot.l(this,null,0);},BL:function(){return this.a;},_BP:function(a){return a.a;}});boot.define("e",{_:function(){boot.e.b=new boot.h(0);boot.e.c=new boot.e("Ahri",0);boot.e.d=new boot.e("Akali",0);boot.e.e=new boot.e("Alistar",0);boot.e.f=new boot.e("Amumu",0);boot.e.g=new boot.e("Ashe",0);boot.e.h=new boot.e("Blitzcrank",0);boot.e.i=new boot.e("Brand",0);boot.e.j=new boot.e("Caitlyn",0);boot.e.k=new boot.e("Cassiopeia",0);boot.e.l=new boot.e("Chogath",0);boot.e.m=new boot.e("Corki",0);boot.e.n=new boot.e("Darius",0);boot.e.o=new boot.e("Diana",0);boot.e.p=new boot.e("Dr.Mundo",0);boot.e.ba=new boot.e("Elise",0);boot.e.bb=new boot.e("Evelynn",0);boot.e.bc=new boot.e("Ezreal",0);boot.e.bd=new boot.e("Fiddlesticks",0);boot.e.be=new boot.e("Fiora",0);boot.e.bf=new boot.e("Fizz",0);boot.e.bg=new boot.e("Galio",0);boot.e.bh=new boot.e("Gangplank",0);boot.e.bi=new boot.e("Garen",0);boot.e.bj=new boot.e("Gragas",0);boot.e.bk=new boot.e("Graves",0);boot.e.bl=new boot.e("Hecarim",0);boot.e.bm=new boot.e("Heimerdinger",0);boot.e.bn=new boot.e("Irelia",0);boot.e.bo=new boot.e("Janna",0);boot.e.bp=new boot.e("Jarvan IV",0);boot.e.ca=new boot.e("Jax",0);boot.e.cb=new boot.e("Jayce",0);boot.e.cc=new boot.e("Karma",0);boot.e.cd=new boot.e("Karthus",0);boot.e.ce=new boot.e("Kassadin",0);boot.e.cf=new boot.e("Katarina",0);boot.e.cg=new boot.e("Kayle",0);boot.e.ch=new boot.e("Kennen",0);boot.e.ci=new boot.e("Kha'zix",0);boot.e.cj=new boot.e("Kog'maw",0);boot.e.ck=new boot.e("LeBlanc",0);boot.e.cl=new boot.e("Lee Sin",0);boot.e.cm=new boot.e("Leona",0);boot.e.cn=new boot.e("Lulu",0);boot.e.co=new boot.e("Lux",0);boot.e.cp=new boot.e("Malphite",0);boot.e.da=new boot.e("Maokai",0);boot.e.db=new boot.e("Master Yi",0);boot.e.dc=new boot.e("Miss Fortune",0);boot.e.dd=new boot.e("Mordekaiser",0);boot.e.de=new boot.e("Morgana",0);boot.e.df=new boot.e("Nasus",0);boot.e.dg=new boot.e("Nautilus",0);boot.e.dh=new boot.e("Nidalee",0);boot.e.di=new boot.e("Nocturne",0);boot.e.dj=new boot.e("Nunu",0);boot.e.dk=new boot.e("Olaf",0);boot.e.dl=new boot.e("Orianna",0);boot.e.dm=new boot.e("Pantheon",0);boot.e.dn=new boot.e("Poppy",0);boot.e.dp=new boot.e("Rammus",0);boot.e.ea=new boot.e("Renekton",0);boot.e.eb=new boot.e("Rengar",0);boot.e.ec=new boot.e("Riven",0);boot.e.ed=new boot.e("Rumble",0);boot.e.ee=new boot.e("Ryze",0);boot.e.ef=new boot.e("Sejuani",0);boot.e.eg=new boot.e("Shaco",0);boot.e.eh=new boot.e("Shen",0);boot.e.ei=new boot.e("Shyvana",0);boot.e.ej=new boot.e("Singed",0);boot.e.ek=new boot.e("Sion",0);boot.e.el=new boot.e("Sivir",0);boot.e.em=new boot.e("Skarner",0);boot.e.en=new boot.e("Sona",0);boot.e.eo=new boot.e("Soraka",0);boot.e.ep=new boot.e("Swain",0);boot.e.fa=new boot.e("Syndra",0);boot.e.fb=new boot.e("Talon",0);boot.e.fc=new boot.e("Taric",0);boot.e.fd=new boot.e("Teemo",0);boot.e.fe=new boot.e("Tristana",0);boot.e.ff=new boot.e("Trundle",0);boot.e.fg=new boot.e("Tryndamere",0);boot.e.fh=new boot.e("Twisted Fate",0);boot.e.fi=new boot.e("Twitch",0);boot.e.fj=new boot.e("Udyr",0);boot.e.fk=new boot.e("Urgot",0);boot.e.fl=new boot.e("Varus",0);boot.e.fm=new boot.e("Vayne",0);boot.e.fn=new boot.e("Veigar",0);boot.e.fo=new boot.e("Viktor",0);boot.e.fp=new boot.e("Vladimir",0);boot.e.ga=new boot.e("Volibear",0);boot.e.gb=new boot.e("Warwick",0);boot.e.gc=new boot.e("Wukong",0);boot.e.gd=new boot.e("Xerath",0);boot.e.ge=new boot.e("Xin Zhao",0);boot.e.gf=new boot.e("Yorick",0);boot.e.gg=new boot.e("Zed",0);boot.e.gh=new boot.e("Ziggs",0);boot.e.gi=new boot.e("Zilean",0);boot.e.gj=new boot.e("Zyra",0);},$0:function(a){this.a=a;boot.e.b.M(a,this);},J:function(){return this.a.replace(/\./g,"").replace(/\s/g,"").replace(/\'/g,"");},_N:function(a){return boot.e.b.O(a);},_C:function(){return boot.e.b.P();}});boot.define("g",{$0:function(){}});boot.define("d",boot.g,{$0:function(a){this.a=a;boot.g.prototype.$0.call(this);},G:function(){return boot.e.C();},H:function(a){return a.a;},I:function(a){return "src/main/resources/teemowork/icon/"+a.J()+".png";},K:function(a){return this.H(a);},L:function(a){return this.I(a);}});boot.define("b",{$0:function(){},B:function(a){}});boot.define("bc",{$0:function(a){this.a=a;this.b=0;},F:function(){return this.b<this.a.size();},DA:function(){return $(this.a.get(this.b++));},CO:function(){},E:function(){return this.DA();}});boot.defineNative("jQuery",{$0:function(){},D:function(){return new boot.bc(this,0);}});boot.define("a",boot.b,{$0:function(){boot.b.prototype.$0.call(this);this.a=new boot.d(this,0);},A:function(a,b,c){b=boot.e.C().D();l1:for (;b.F()!=0;c.appendTo("body")) {a=b.E();c=$("<li><a class='tt-twitter' href='#'><span>"+a.a+"</span></a></li>");}$("a").addClass("f");}});