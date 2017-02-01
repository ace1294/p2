
dbase(vpl,[violetMiddleLabels,violetAssociation,violetInterface,violetClass]).

table(violetClass,[id,"name","fields","methods",x,y]).
violetClass(classnode0,'ClassA','','',339,168).
violetClass(classnode1,'ClassB','','',649,166).

table(violetInterface,[id,"name","methods",x,y]).

table(violetAssociation,[id,"role1","arrow1",type1,"role2","arrow2",type2,"bentStyle","lineStyle",cid1,cid2]).
violetAssociation(id0,'abc','BLACK_DIAMOND',classnode,'def','TRIANGLE',classnode,'VHV','DOTTED',classnode0,classnode1).

table(violetMiddleLabels,[id,cid1,cid2,"label"]).
