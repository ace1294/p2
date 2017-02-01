
dbase(vpl,[violetMiddleLabels,violetAssociation,violetInterface,violetClass]).

table(violetClass,[id,"name","fields","methods",x,y]).
violetClass(classnode0,'Long','','a;b;d;c;d;e;f',727,236).
violetClass(classnode1,'Short','','',1190,250).

table(violetInterface,[id,"name","methods",x,y]).

table(violetAssociation,[id,"role1","arrow1",type1,"role2","arrow2",type2,"bentStyle","lineStyle",cid1,cid2]).
violetAssociation(id0,'','',classnode,'','TRIANGLE',classnode,'','',classnode0,classnode1).
violetAssociation(id1,'','DIAMOND',classnode,'','',classnode,'HVH','',classnode0,classnode1).

table(violetMiddleLabels,[id,cid1,cid2,"label"]).
