boot.define("be",{$1:function(a,b,c){this.a=a;;this.b=b;this.c=c;},L:function(){return this.b.L();},H:function(a){a=this.b.H();if(this.c==0){return a.BK();}else{return a.CA();}},CN:function(){this.b.CN();},$0:function(a,b,c,d){boot.be.prototype.$1.call(this,a,b,c);}});boot.define("bd",{$0:function(){},_CM:function(a,b){return boot.bd.DA(a,b);},_DB:function(a,b){return boot.bd.DC(a,b);}});boot.define("bc",{$0:function(){},BF:function(){return this.BE()==0;},CE:function(a,b,c,d){b=0;d=a.G();l2:while (d.L()!=0) {c=d.H();if(this.CF(c)==0){}else{b=1;continue l2;}continue l2;}return b;},CG:function(a,b,c,d){b=0;d=this.G();l2:while (d.L()!=0) {c=d.H();if(a.BH(c)!=0){}else{b=this.CH(c);continue l2;}continue l2;}return b;},CI:function(a,b,c,d){b=0;d=a.G();l2:while (d.L()!=0) {c=d.H();if(this.CH(c)==0){}else{b=1;continue l2;}continue l2;}return b;},CJ:function(a,b,c){c=a.G();l1:while (c.L()!=0) {b=c.H();if(this.BH(b)!=0){}else{return 0;}continue l1;}return 1;},CK:function(){return this.CL(new Array(0));},CL:function(a,b,c,d,e){b=this.BE();if(a.length>=b){}else{a=boot.bd.CM(a.getClass(),b);}c=this.G();d=0;l6:while (c.L()!=0) {e=c.H();a[d]=e;++d;continue l6;}return a;}});boot.define("bb",boot.bc,{$0:function(){boot.bc.prototype.$0.call(this);}});boot.define("bf",{$1:function(a,b){this.a=a;;this.b=0;this.c=b;},L:function(){return this.b<this.c.length;},H:function(){this.d=boot.n.CP(this.a)[this.c[this.b++]];return this.d;},CN:function(){if(this.b<=0){}else{this.a.CH(this.d);}},$0:function(a,b,c){boot.bf.prototype.$1.call(this,a,b);}});boot.define("n",boot.bb,{$0:function(){boot.bb.prototype.$0.call(this);this.a=0;this.b={};},BE:function(){return this.a;},BH:function(a){return this.CO(a) in this.b;},CF:function(a,b){b=this.CO(a);if(b in this.b==0){this.a=this.a+1;this.b[b]=a;return 1;}else{return 0;}},CH:function(a,b){b=this.CO(a);if(b in this.b!=0){this.a=this.a-1;delete this.b[b];return 1;}else{return 0;}},CB:function(){this.a=0;this.b=[];},G:function(){return new boot.bf(this,this.b.keys(),null,0);},CO:function(a){return (a==null?-1:a.hashCode());},BJ:function(a){return this.b[this.CO(a)];},BL:function(a,b,c){b=null;c=this.CO(a);if(c in this.b==0){this.a=this.a+1;}else{b=this.b[c];}this.b[c]=a;return b;},BN:function(a,b,c){b=null;c=this.CO(a);if(c in this.b==0){}else{b=this.b[c];this.a=this.a-1;delete this.b[c];}return b;},_CP:function(a){return a.b;}});boot.define("p",boot.bb,{$1:function(a){this.a=a;boot.bb.prototype.$0.call(this);},BE:function(){return boot.i.CD(this.a).BE();},BH:function(a){return boot.i.CD(this.a).BH(a);},G:function(){return new boot.be(this.a,boot.i.CD(this.a).G(),1,null,0);},CF:function(a){return 0;},CH:function(a){return boot.i.CD(this.a).CH(a);},CB:function(){boot.i.CD(this.a).CB();},$0:function(a,b){boot.p.prototype.$1.call(this,a);}});boot.define("o",{$1:function(a,b){this.a=a;this.b=b;},CA:function(){return this.a;},BK:function(){return this.b;},DD:function(a,b){b=this.b;this.b=a;return b;},hashCode:function(){return this.a.hashCode();},$0:function(a,b,c){boot.o.prototype.$1.call(this,a,b);}});boot.define("ba",boot.bc,{$1:function(a){this.a=a;boot.bc.prototype.$0.call(this);},BE:function(){return boot.i.CD(this.a).BE();},BH:function(a){return this.a.BI(a);},G:function(){return new boot.be(this.a,boot.i.CD(this.a).G(),0,null,0);},CF:function(a){return 0;},CH:function(a,b,c){b=this.G();l2:while (b.L()!=0) {c=b.H();if(c!=a){}else{b.CN();return 1;}continue l2;}return 0;},CB:function(){boot.i.CD(this.a).CB();},$0:function(a,b){boot.ba.prototype.$1.call(this,a);}});boot.define("i",{$0:function(){this.a=new boot.n(0);},BE:function(){return this.a.BE();},BF:function(){return this.a.BF();},BG:function(a){return this.a.BH(a);},BI:function(a,b,c){c=this.BD().G();l1:while (c.L()!=0) {b=c.H();if(b!=a){}else{return 1;}continue l1;}return 0;},BC:function(a,b){b=this.a.BJ(a);return (b==null?null:b.BK());},K:function(a,b,c){c=this.a.BL(new boot.o(a,b,null,0));if(c!=null){return c.BK();}else{return null;}},BM:function(a,b){b=this.a.BN(a);if(b!=null){return b.BK();}else{return null;}},BO:function(a,b,c){c=a.BP().G();l1:for (;c.L()!=0;this.K(b.CA(),b.BK())) {b=c.H();}},CB:function(){this.a.CB();},CC:function(){return new boot.p(this,null,0);},BD:function(){return new boot.ba(this,null,0);},BP:function(){return this.a;},_CD:function(a){return a.a;}});boot.define("m",{_:function(){boot.m.b=new boot.i(0);boot.m.c=new boot.m("Ahri",0);boot.m.d=new boot.m("Akali",0);boot.m.e=new boot.m("Alistar",0);boot.m.f=new boot.m("Amumu",0);boot.m.g=new boot.m("Ashe",0);boot.m.h=new boot.m("Blitzcrank",0);boot.m.i=new boot.m("Brand",0);boot.m.j=new boot.m("Caitlyn",0);boot.m.k=new boot.m("Cassiopeia",0);boot.m.l=new boot.m("Chogath",0);boot.m.m=new boot.m("Corki",0);boot.m.n=new boot.m("Darius",0);boot.m.o=new boot.m("Diana",0);boot.m.p=new boot.m("Dr.Mundo",0);boot.m.ba=new boot.m("Elise",0);boot.m.bb=new boot.m("Evelynn",0);boot.m.bc=new boot.m("Ezreal",0);boot.m.bd=new boot.m("Fiddlesticks",0);boot.m.be=new boot.m("Fiora",0);boot.m.bf=new boot.m("Fizz",0);boot.m.bg=new boot.m("Galio",0);boot.m.bh=new boot.m("Gangplank",0);boot.m.bi=new boot.m("Garen",0);boot.m.bj=new boot.m("Gragas",0);boot.m.bk=new boot.m("Graves",0);boot.m.bl=new boot.m("Hecarim",0);boot.m.bm=new boot.m("Heimerdinger",0);boot.m.bn=new boot.m("Irelia",0);boot.m.bo=new boot.m("Janna",0);boot.m.bp=new boot.m("Jarvan IV",0);boot.m.ca=new boot.m("Jax",0);boot.m.cb=new boot.m("Jayce",0);boot.m.cc=new boot.m("Karma",0);boot.m.cd=new boot.m("Karthus",0);boot.m.ce=new boot.m("Kassadin",0);boot.m.cf=new boot.m("Katarina",0);boot.m.cg=new boot.m("Kayle",0);boot.m.ch=new boot.m("Kennen",0);boot.m.ci=new boot.m("Kha'zix",0);boot.m.cj=new boot.m("Kog'maw",0);boot.m.ck=new boot.m("LeBlanc",0);boot.m.cl=new boot.m("Lee Sin",0);boot.m.cm=new boot.m("Leona",0);boot.m.cn=new boot.m("Lulu",0);boot.m.co=new boot.m("Lux",0);boot.m.cp=new boot.m("Malphite",0);boot.m.da=new boot.m("Maokai",0);boot.m.db=new boot.m("Master Yi",0);boot.m.dc=new boot.m("Miss Fortune",0);boot.m.dd=new boot.m("Mordekaiser",0);boot.m.de=new boot.m("Morgana",0);boot.m.df=new boot.m("Nasus",0);boot.m.dg=new boot.m("Nautilus",0);boot.m.dh=new boot.m("Nidalee",0);boot.m.di=new boot.m("Nocturne",0);boot.m.dj=new boot.m("Nunu",0);boot.m.dk=new boot.m("Olaf",0);boot.m.dl=new boot.m("Orianna",0);boot.m.dm=new boot.m("Pantheon",0);boot.m.dn=new boot.m("Poppy",0);boot.m.dp=new boot.m("Rammus",0);boot.m.ea=new boot.m("Renekton",0);boot.m.eb=new boot.m("Rengar",0);boot.m.ec=new boot.m("Riven",0);boot.m.ed=new boot.m("Rumble",0);boot.m.ee=new boot.m("Ryze",0);boot.m.ef=new boot.m("Sejuani",0);boot.m.eg=new boot.m("Shaco",0);boot.m.eh=new boot.m("Shen",0);boot.m.ei=new boot.m("Shyvana",0);boot.m.ej=new boot.m("Singed",0);boot.m.ek=new boot.m("Sion",0);boot.m.el=new boot.m("Sivir",0);boot.m.em=new boot.m("Skarner",0);boot.m.en=new boot.m("Sona",0);boot.m.eo=new boot.m("Soraka",0);boot.m.ep=new boot.m("Swain",0);boot.m.fa=new boot.m("Syndra",0);boot.m.fb=new boot.m("Talon",0);boot.m.fc=new boot.m("Taric",0);boot.m.fd=new boot.m("Teemo",0);boot.m.fe=new boot.m("Tristana",0);boot.m.ff=new boot.m("Trundle",0);boot.m.fg=new boot.m("Tryndamere",0);boot.m.fh=new boot.m("Twisted Fate",0);boot.m.fi=new boot.m("Twitch",0);boot.m.fj=new boot.m("Udyr",0);boot.m.fk=new boot.m("Urgot",0);boot.m.fl=new boot.m("Varus",0);boot.m.fm=new boot.m("Vayne",0);boot.m.fn=new boot.m("Veigar",0);boot.m.fo=new boot.m("Viktor",0);boot.m.fp=new boot.m("Vladimir",0);boot.m.ga=new boot.m("Volibear",0);boot.m.gb=new boot.m("Warwick",0);boot.m.gc=new boot.m("Wukong",0);boot.m.gd=new boot.m("Xerath",0);boot.m.ge=new boot.m("Xin Zhao",0);boot.m.gf=new boot.m("Yorick",0);boot.m.gg=new boot.m("Zed",0);boot.m.gh=new boot.m("Ziggs",0);boot.m.gi=new boot.m("Zilean",0);boot.m.gj=new boot.m("Zyra",0);},$0:function(a){this.a=a;this.gk=this.BA().toLowerCase();boot.m.b.K(a,this);},BA:function(){return this.a.replace(/\./g,"").replace(/\s/g,"").replace(/\'/g,"");},_BB:function(a){return boot.m.b.BC(a);},_N:function(){return boot.m.b.BD();}});boot.define("h",{$0:function(){boot.h.prototype.$1.call(this,"div");},$1:function(a){this.a=$("<"+a+">");}});boot.define("bg",{$0:function(a){this.a=a;this.b=0;},L:function(){return this.b<this.a.size();},DF:function(){return $(this.a.get(this.b++));},CN:function(){},H:function(){return this.DF();}});boot.defineNative("Document",{$0:function(){},createElement:function(a){return this.createElement(a);}});boot.defineNative("jQuery",{$0:function(){},F:function(a){return $(document.createElement(a)).appendTo(this);},DE:function(a){return this.F("span").addClass(a);},G:function(){return new boot.bg(this,0);}});boot.define("l",{$0:function(a,b){this.a=a;this.b=b;},handler:function(a,b,c,d){b=this.b.val().toLowerCase().replace(/\s/g,"");d=boot.g.M(this.a).BP().G();l2:while (d.L()!=0) {c=d.H();if(this.a.J(c.CA()).toLowerCase().indexOf(b) != -1==0){c.BK().addClass("unselected");continue l2;}else{c.BK().removeClass("unselected");continue l2;}}}});boot.define("g",boot.h,{$0:function(){boot.h.prototype.$0.call(this);this.b=new boot.i(0);this.c=this.E();},D:function(a,b,c,d,e){this.a.addClass("j");b=this.a.F("input").css("display","block");b.keyup(new boot.l(this,b,0));d=this.c.G();l4:for (;d.L()!=0;this.b.K(c,e)) {c=d.H();e=this.a.F("span").css("background-image","url("+this.I(c)+")");e.F("span").text(this.J(c));}a.append(this.a);},_M:function(a){return a.b;}});boot.define("d",boot.g,{$0:function(a){this.d=a;boot.g.prototype.$0.call(this);},E:function(){return boot.m.N();},O:function(a){return a.a;},P:function(a){return "src/main/resources/teemowork/icon/"+a.BA()+".png";},J:function(a){return this.O(a);},I:function(a){return this.P(a);}});boot.define("f",{$0:function(a,b){this.a=a;;this.b=b.F("ul");},C:function(a,b,c){c=this.b.F("li");c.F("a").attr("href",b).text(a);return new boot.f(this.a,c,0);},$1:function(a,b,c){boot.f.prototype.$0.call(this,a,b);}});boot.define("b",{$0:function(){},B:function(a){}});boot.define("e",{$0:function(){this.a=$("#Header").addClass("bi");},C:function(a,b,c){c=this.a.F("li");c.F("a").attr("href",b).text(a);return new boot.f(this,c,null,1);}});boot.define("a",boot.b,{$0:function(){boot.b.prototype.$0.call(this);this.a=new boot.d(this,0);},A:function(a,b,c){$("body").css("padding","150px");a=$("#Content");b=new boot.e(0);b.C("< ^ v ^ > Teemowork","test.html");b.C("Patch","#");c=b.C("Data","#");c.C("Champion","#");c.C("Item","#");c.C("Mastery","#");c.C("Rune","#");b.C("Builder","#");b.C("About","#");b.C("Contact","#");this.a.D(a);}});