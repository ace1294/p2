
dbase(vpl,[violetMiddleLabels,violetAssociation,violetInterface,violetClass]).

table(violetClass,[id,"name","fields","methods",x,y]).
violetClass(classnode0,'Figure','position','draw()',322,234).
violetClass(classnode1,'Group','','draw()',237,392).
violetClass(classnode2,'Polygon','','draw()',405,387).

table(violetInterface,[id,"name","methods",x,y]).

table(violetAssociation,[id,"role1","arrow1",type1,"role2","arrow2",type2,"bentStyle","lineStyle",cid1,cid2]).
violetAssociation(id0,'partOf','DIAMOND',classnode,'consistsOf','V',classnode,'','',classnode1,classnode0).
violetAssociation(id1,'','',classnode,'','TRIANGLE',classnode,'VHV','',classnode2,classnode0).
violetAssociation(id2,'','',classnode,'','TRIANGLE',classnode,'VHV','',classnode1,classnode0).

table(violetMiddleLabels,[id,cid1,cid2,"label"]).
