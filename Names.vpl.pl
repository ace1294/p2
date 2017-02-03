
dbase(vpl,[violetMiddleLabels,violetAssociation,violetInterface,violetClass]).

table(violetClass,[id,"name","fields","methods",x,y]).
violetClass(classnode0,'classA','','',422,228).

table(violetInterface,[id,"name","methods",x,y]).
violetInterface(interfacenode0,'classA','',722,316).

table(violetAssociation,[id,"role1","arrow1",type1,"role2","arrow2",type2,"bentStyle","lineStyle",cid1,cid2]).

table(violetMiddleLabels,[id,cid1,cid2,"label"]).
