
dbase(vpl,[violetMiddleLabels,violetAssociation,violetInterface,violetClass]).

table(violetClass,[id,"name","fields","methods",x,y]).
violetClass(classnode0,'A','','',498,114).
violetClass(classnode1,'B','','',769,114).
violetClass(classnode2,'C','','',598,248).
violetClass(classnode3,'C','','',981,231).
violetClass(unconnected0,'','','',844,224).

table(violetInterface,[id,"name","methods",x,y]).

table(violetAssociation,[id,"role1","arrow1",type1,"role2","arrow2",type2,"bentStyle","lineStyle",cid1,cid2]).
violetAssociation(id0,'','',classnode,'','V',classnode,'HVH','',classnode0,classnode1).
violetAssociation(id1,'','',classnode,'super','TRIANGLE',classnode,'VHV','DOTTED',classnode0,classnode2).
violetAssociation(id2,'has','V',classnode,'','V',classnode,'','DOTTED',classnode2,classnode1).

table(violetMiddleLabels,[id,cid1,cid2,"label"]).
