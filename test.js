boot.defineClass("b",{$0:function(){}});boot.defineClass("f",{$1:function(){},$0:function(a){}});boot.defineClass("e",{$0:function(){this.ea=[];},H:function(){return this.ea.length;},I:function(){return this.ea.length==0;},J:function(a){return this.ea.indexOf(a)!=-1;},D:function(){return this.ea.it();},G:function(a){this.ea.push(a);return 1;},K:function(a){return 0;},L:function(a){return 0;},M:function(a){return 0;},N:function(a,b){return 0;},O:function(a){return 0;},P:function(a){return 0;},BA:function(){},BB:function(a){return null;},BC:function(a,b){return null;},BD:function(a,b){},C:function(a){this.BE(a);return this.ea.splice(a,1)[0];},BF:function(a){return 0;},BG:function(a){return 0;},BH:function(){return null;},BI:function(a){return null;},BJ:function(a,b){return null;},BE:function(a){if (a>=0) {if (this.H()>a) {return;} else {throw new boot.f("Index is overflowed. Size: "+this.H()+"  Index: "+a,0);}} else {throw new boot.f("Negative index is unacceptable. Size: "+this.H()+"  Index: "+a,0);}}});boot.defineClass("d",{_:function(){boot.d.dd=new boot.e(0);boot.d.db=new boot.d("Shard of True Ice",0);boot.d.dc=new boot.d("Liandry's Torment",0);boot.d.da=new boot.d("Haunting Guise",0);},$0:function(a){this.de=new boot.e(0);this.df=a;},BK:function(a){this.dg=a;return this;},F:function(a){this.dg=this.dg;return this;},_BL:function(a,b,c){c=boot.d.dd.D();l1 : while (c.hasNext()!=0) {b=c.next();if (b.df.equals(a)==0) {} else {return b;}continue l1;}return null;},_BM:function(){return boot.d.dd;}});boot.defineClass("c",{_:function(){boot.c.cb=new boot.c(1510,2012,11,13,"End of Season 2",null,0);boot.c.cb.E(boot.d.da).F(20);boot.c.cc=new boot.c(1520,2012,12,3,"Preseason 3",boot.c.cb,0);boot.c.cc.E(boot.d.db);boot.c.cc.E(boot.d.dc);boot.c.cc.E(boot.d.da).F(15);boot.c.ca=boot.c.cc;},$0:function(a,b,c,d,e,f){this.cd=new boot.e(0);this.ce=a;this.cf=e;this.cg=f;},E:function(a){this.cd.G(a);return a;},B:function(){return this.cd;}});boot.defineClass("a",{$0:function(){},_A:function(a,b,c){a=boot.c.ca.B();console.log(a);console.log("Remove  "+a.C(0));c=a.D();l4 : for (;c.hasNext()!=0;console.log(b)) {b=c.next();}}});