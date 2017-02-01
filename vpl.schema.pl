dbase(vpl,[violetMiddleLabels,violetAssociation,violetInterface,violetClass]).

table(violetClass,[id,"name","fields","methods",x,y]).
table(violetInterface,[id,"name","methods",x,y]).
table(violetAssociation,[id,"role1","arrow1","type1","role2","arrow2","type2","bentStyle","lineStyle",cid1,cid2]).
table(violetMiddleLabels,[id,cid1,cid2,"label"]).

