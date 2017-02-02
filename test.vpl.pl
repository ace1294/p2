dbase(vpl,[violetMiddleLabels,violetAssociation,violetInterface,violetClass]).

table(violetClass,[id,"name","fields","methods",x,y]).
violetClass(class0,'ClassA','','',339,168).
violetClass(class1,'ClassB','','',339,168).

table(violetInterface,[id,"name","fields","methods",x,y]).
violetInterface(interface0,'ClassA','','',649,166).

table(violetAssociation,[id,"role1","arrow1",type1,"role2","arrow2",type2,"bentStyle","lineStyle",cid1,cid2]).
violetAssociation(id1,'','DIAMOND',classnode,'1','V',classnode,'VH','',class0,class1).

table(violetMiddleLabels,[id,cid1,cid2,"label"]).