boot.define("b",{$0:function(){},B:function(a){}});boot.defineNative("Document",{$0:function(){},createElement:function(a){return this.createElement(a);}});boot.define("j",{$0:function(a){this.a=a;this.b=0;},J:function(){return this.b<this.a.size();},K:function(){return $(this.a.get(this.b++));},L:function(){},F:function(){return this.K();}});boot.defineNative("jQuery",{$0:function(){},I:function(a){return $(document.createElement(a)).appendTo(this);},H:function(a){return this.I("span").addClass(a);},E:function(){return new boot.j(this,0);}});boot.define("f",{$0:function(a,b){this.a=a;;this.b=b.I("ul");},C:function(a,b,c){c=this.b.I("li");c.I("a").attr("href",b).text(a);return new boot.f(this.a,c,0);},$1:function(a,b,c){boot.f.prototype.$0.call(this,a,b);}});boot.define("bd",{$1:function(a,b){this.a=a;;this.b=0;this.c=b;},J:function(){return this.b<this.c.length;},F:function(){this.d=boot.m.CK(this.a)[this.c[this.b++]];return this.d;},L:function(){if(this.b<=0){}else{this.a.CD(this.d);}},$0:function(a,b,c){boot.bd.prototype.$1.call(this,a,b);}});boot.define("bc",{$0:function(){},_CI:function(a,b){return boot.bc.CL(a,b);},_CM:function(a,b){return boot.bc.CN(a,b);}});boot.define("bb",{$0:function(){},BB:function(){return this.BA()==0;},CA:function(a,b,c,d){b=0;d=a.E();l2:while (d.J()!=0) {c=d.F();if(this.CB(c)==0){}else{b=1;continue l2;}continue l2;}return b;},CC:function(a,b,c,d){b=0;d=this.E();l2:while (d.J()!=0) {c=d.F();if(a.BD(c)!=0){}else{b=this.CD(c);continue l2;}continue l2;}return b;},CE:function(a,b,c,d){b=0;d=a.E();l2:while (d.J()!=0) {c=d.F();if(this.CD(c)==0){}else{b=1;continue l2;}continue l2;}return b;},CF:function(a,b,c){c=a.E();l1:while (c.J()!=0) {b=c.F();if(this.BD(b)!=0){}else{return 0;}continue l1;}return 1;},CG:function(){return this.CH(new Array(0));},CH:function(a,b,c,d,e){b=this.BA();if(a.length>=b){}else{a=boot.bc.CI(a.getClass(),b);}c=this.E();d=0;l6:while (c.J()!=0) {e=c.F();a[d]=e;++d;continue l6;}return a;}});boot.define("ba",boot.bb,{$0:function(){boot.bb.prototype.$0.call(this);}});boot.define("m",boot.ba,{$0:function(){boot.ba.prototype.$0.call(this);this.a=0;this.b={};},BA:function(){return this.a;},BD:function(a){return this.CJ(a) in this.b;},CB:function(a,b){b=this.CJ(a);if(b in this.b==0){this.a=this.a+1;this.b[b]=a;return 1;}else{return 0;}},CD:function(a,b){b=this.CJ(a);if(b in this.b!=0){this.a=this.a-1;delete this.b[b];return 1;}else{return 0;}},BN:function(){this.a=0;this.b=[];},E:function(){return new boot.bd(this,this.b.keys(),null,0);},CJ:function(a){return (a==null?-1:a.hashCode());},BF:function(a){return this.b[this.CJ(a)];},BH:function(a,b,c){b=null;c=this.CJ(a);if(c in this.b==0){this.a=this.a+1;}else{b=this.b[c];}this.b[c]=a;return b;},BJ:function(a,b,c){b=null;c=this.CJ(a);if(c in this.b==0){}else{b=this.b[c];this.a=this.a-1;delete this.b[c];}return b;},_CK:function(a){return a.b;}});boot.define("be",{$1:function(a,b,c){this.a=a;;this.b=b;this.c=c;},J:function(){return this.b.J();},F:function(a){a=this.b.F();if(this.c==0){return a.BG();}else{return a.BM();}},L:function(){this.b.L();},$0:function(a,b,c,d){boot.be.prototype.$1.call(this,a,b,c);}});boot.define("p",boot.bb,{$1:function(a){this.a=a;boot.bb.prototype.$0.call(this);},BA:function(){return boot.l.BP(this.a).BA();},BD:function(a){return this.a.BE(a);},E:function(){return new boot.be(this.a,boot.l.BP(this.a).E(),0,null,0);},CB:function(a){return 0;},CD:function(a,b,c){b=this.E();l2:while (b.J()!=0) {c=b.F();if(c!=a){}else{b.L();return 1;}continue l2;}return 0;},BN:function(){boot.l.BP(this.a).BN();},$0:function(a,b){boot.p.prototype.$1.call(this,a);}});boot.define("n",{$1:function(a,b){this.a=a;this.b=b;},BM:function(){return this.a;},BG:function(){return this.b;},CO:function(a,b){b=this.b;this.b=a;return b;},hashCode:function(){return this.a.hashCode();},$0:function(a,b,c){boot.n.prototype.$1.call(this,a,b);}});boot.define("o",boot.ba,{$1:function(a){this.a=a;boot.ba.prototype.$0.call(this);},BA:function(){return boot.l.BP(this.a).BA();},BD:function(a){return boot.l.BP(this.a).BD(a);},E:function(){return new boot.be(this.a,boot.l.BP(this.a).E(),1,null,0);},CB:function(a){return 0;},CD:function(a){return boot.l.BP(this.a).CD(a);},BN:function(){boot.l.BP(this.a).BN();},$0:function(a,b){boot.o.prototype.$1.call(this,a);}});boot.define("l",{$0:function(){this.a=new boot.m(0);},BA:function(){return this.a.BA();},BB:function(){return this.a.BB();},BC:function(a){return this.a.BD(a);},BE:function(a,b,c){c=this.P().E();l1:while (c.J()!=0) {b=c.F();if(b!=a){}else{return 1;}continue l1;}return 0;},O:function(a,b){b=this.a.BF(a);return (b==null?null:b.BG());},M:function(a,b,c){c=this.a.BH(new boot.n(a,b,null,0));if(c!=null){return c.BG();}else{return null;}},BI:function(a,b){b=this.a.BJ(a);if(b!=null){return b.BG();}else{return null;}},BK:function(a,b,c){c=a.BL().E();l1:for (;c.J()!=0;this.M(b.BM(),b.BG())) {b=c.F();}},BN:function(){this.a.BN();},BO:function(){return new boot.o(this,null,0);},P:function(){return new boot.p(this,null,0);},BL:function(){return this.a;},_BP:function(a){return a.a;}});boot.define("g",{_:function(){boot.g.b=new boot.l(0);boot.g.c=new boot.g("Ahri",0);boot.g.d=new boot.g("Akali",0);boot.g.e=new boot.g("Alistar",0);boot.g.f=new boot.g("Amumu",0);boot.g.g=new boot.g("Ashe",0);boot.g.h=new boot.g("Blitzcrank",0);boot.g.i=new boot.g("Brand",0);boot.g.j=new boot.g("Caitlyn",0);boot.g.k=new boot.g("Cassiopeia",0);boot.g.l=new boot.g("Chogath",0);boot.g.m=new boot.g("Corki",0);boot.g.n=new boot.g("Darius",0);boot.g.o=new boot.g("Diana",0);boot.g.p=new boot.g("Dr.Mundo",0);boot.g.ba=new boot.g("Elise",0);boot.g.bb=new boot.g("Evelynn",0);boot.g.bc=new boot.g("Ezreal",0);boot.g.bd=new boot.g("Fiddlesticks",0);boot.g.be=new boot.g("Fiora",0);boot.g.bf=new boot.g("Fizz",0);boot.g.bg=new boot.g("Galio",0);boot.g.bh=new boot.g("Gangplank",0);boot.g.bi=new boot.g("Garen",0);boot.g.bj=new boot.g("Gragas",0);boot.g.bk=new boot.g("Graves",0);boot.g.bl=new boot.g("Hecarim",0);boot.g.bm=new boot.g("Heimerdinger",0);boot.g.bn=new boot.g("Irelia",0);boot.g.bo=new boot.g("Janna",0);boot.g.bp=new boot.g("Jarvan IV",0);boot.g.ca=new boot.g("Jax",0);boot.g.cb=new boot.g("Jayce",0);boot.g.cc=new boot.g("Karma",0);boot.g.cd=new boot.g("Karthus",0);boot.g.ce=new boot.g("Kassadin",0);boot.g.cf=new boot.g("Katarina",0);boot.g.cg=new boot.g("Kayle",0);boot.g.ch=new boot.g("Kennen",0);boot.g.ci=new boot.g("Kha'zix",0);boot.g.cj=new boot.g("Kog'maw",0);boot.g.ck=new boot.g("LeBlanc",0);boot.g.cl=new boot.g("Lee Sin",0);boot.g.cm=new boot.g("Leona",0);boot.g.cn=new boot.g("Lulu",0);boot.g.co=new boot.g("Lux",0);boot.g.cp=new boot.g("Malphite",0);boot.g.da=new boot.g("Maokai",0);boot.g.db=new boot.g("Master Yi",0);boot.g.dc=new boot.g("Miss Fortune",0);boot.g.dd=new boot.g("Mordekaiser",0);boot.g.de=new boot.g("Morgana",0);boot.g.df=new boot.g("Nasus",0);boot.g.dg=new boot.g("Nautilus",0);boot.g.dh=new boot.g("Nidalee",0);boot.g.di=new boot.g("Nocturne",0);boot.g.dj=new boot.g("Nunu",0);boot.g.dk=new boot.g("Olaf",0);boot.g.dl=new boot.g("Orianna",0);boot.g.dm=new boot.g("Pantheon",0);boot.g.dn=new boot.g("Poppy",0);boot.g.dp=new boot.g("Rammus",0);boot.g.ea=new boot.g("Renekton",0);boot.g.eb=new boot.g("Rengar",0);boot.g.ec=new boot.g("Riven",0);boot.g.ed=new boot.g("Rumble",0);boot.g.ee=new boot.g("Ryze",0);boot.g.ef=new boot.g("Sejuani",0);boot.g.eg=new boot.g("Shaco",0);boot.g.eh=new boot.g("Shen",0);boot.g.ei=new boot.g("Shyvana",0);boot.g.ej=new boot.g("Singed",0);boot.g.ek=new boot.g("Sion",0);boot.g.el=new boot.g("Sivir",0);boot.g.em=new boot.g("Skarner",0);boot.g.en=new boot.g("Sona",0);boot.g.eo=new boot.g("Soraka",0);boot.g.ep=new boot.g("Swain",0);boot.g.fa=new boot.g("Syndra",0);boot.g.fb=new boot.g("Talon",0);boot.g.fc=new boot.g("Taric",0);boot.g.fd=new boot.g("Teemo",0);boot.g.fe=new boot.g("Tristana",0);boot.g.ff=new boot.g("Trundle",0);boot.g.fg=new boot.g("Tryndamere",0);boot.g.fh=new boot.g("Twisted Fate",0);boot.g.fi=new boot.g("Twitch",0);boot.g.fj=new boot.g("Udyr",0);boot.g.fk=new boot.g("Urgot",0);boot.g.fl=new boot.g("Varus",0);boot.g.fm=new boot.g("Vayne",0);boot.g.fn=new boot.g("Veigar",0);boot.g.fo=new boot.g("Viktor",0);boot.g.fp=new boot.g("Vladimir",0);boot.g.ga=new boot.g("Volibear",0);boot.g.gb=new boot.g("Warwick",0);boot.g.gc=new boot.g("Wukong",0);boot.g.gd=new boot.g("Xerath",0);boot.g.ge=new boot.g("Xin Zhao",0);boot.g.gf=new boot.g("Yorick",0);boot.g.gg=new boot.g("Zed",0);boot.g.gh=new boot.g("Ziggs",0);boot.g.gi=new boot.g("Zilean",0);boot.g.gj=new boot.g("Zyra",0);},$0:function(a){this.a=a;boot.g.b.M(a,this);},G:function(){return this.a.replace(/\./g,"").replace(/\s/g,"").replace(/\'/g,"");},_N:function(a){return boot.g.b.O(a);},_D:function(){return boot.g.b.P();}});boot.define("e",{$0:function(){this.a=$("#Header").addClass("bf");},C:function(a,b,c){c=this.a.I("li");c.I("a").attr("href",b).text(a);return new boot.f(this,c,null,1);}});boot.define("bg",{$0:function(){}});boot.define("d",boot.bg,{$0:function(a){this.a=a;boot.bg.prototype.$0.call(this);},CP:function(){return boot.g.D();},DA:function(a){return a.a;},DB:function(a){return "src/main/resources/teemowork/icon/"+a.G()+".png";},DC:function(a){return this.DA(a);},DD:function(a){return this.DB(a);}});boot.define("a",boot.b,{$0:function(){boot.b.prototype.$0.call(this);this.a=new boot.d(this,0);},A:function(a,b,c,d,e,f){$("body").css("padding","150px");a=$("#Content");b=new boot.e(0);b.C("< ^ v ^ > Teemowork","test.html");b.C("Patch","#");c=b.C("Data","#");c.C("Champion","#");c.C("Item","#");c.C("Mastery","#");c.C("Rune","#");b.C("Builder","#");b.C("About","#");b.C("Contact","#");e=boot.g.D().E();l14:for (;e.J()!=0;a.H("h").css("background-image","url("+f+")").I("span").text(d.a)) {d=e.F();f="src/main/resources/teemowork/icon/"+d.G()+".png";}}});