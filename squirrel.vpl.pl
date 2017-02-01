
dbase(vpl,[violetMiddleLabels,violetAssociation,violetInterface,violetClass]).

table(violetClass,[id,"name","fields","methods",x,y]).
violetClass(classnode0,'A','','',791,115).
violetClass(classnode1,'B','','',592,278).
violetClass(classnode2,'C','','',750,397).
violetClass(classnode3,'D','','',983,281).
violetClass(classnode4,'E','','',973,396).

table(violetInterface,[id,"name","methods",x,y]).

table(violetAssociation,[id,"role1","arrow1",type1,"role2","arrow2",type2,"bentStyle","lineStyle",cid1,cid2]).
violetAssociation(id0,'','TRIANGLE',classnode,'','',classnode,'VHV','',classnode0,classnode1).
violetAssociation(id1,'','TRIANGLE',classnode,'','',classnode,'VHV','',classnode1,classnode2).
violetAssociation(id2,'','',classnode,'','TRIANGLE',classnode,'','',classnode0,classnode2).
violetAssociation(id3,'','',classnode,'','TRIANGLE',classnode,'VHV','',classnode1,classnode1).
violetAssociation(id4,'','V',classnode,'','V',classnode,'HVH','',classnode3,classnode1).
violetAssociation(id5,'0..1','DIAMOND',classnode,'','BLACK_DIAMOND',classnode,'','DOTTED',classnode2,classnode4).
violetAssociation(id6,'','',classnode,'','DIAMOND',classnode,'','',classnode4,classnode4).

table(violetMiddleLabels,[id,cid1,cid2,"label"]).
