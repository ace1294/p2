
dbase(vpl,[violetMiddleLabels,violetAssociation,violetInterface,violetClass]).

table(violetClass,[id,"name","fields","methods",x,y]).
violetClass(classnode0,'customer','','',433,168).
violetClass(classnode1,'receipt','','',824,168).
violetClass(classnode2,'contract','','',825,369).
violetClass(classnode3,'CarRentalCompany','','',420,363).
violetClass(classnode4,'RentalCar','','',425,509).

table(violetInterface,[id,"name","methods",x,y]).
violetInterface(interfacenode0,'Don','',830,519).
violetInterface(interfacenode1,'Don','',262,260).

table(violetAssociation,[id,"role1","arrow1",type1,"role2","arrow2",type2,"bentStyle","lineStyle",cid1,cid2]).
violetAssociation(id0,'1','BLACK_DIAMOND',classnode,'*','',classnode,'','',classnode0,classnode2).
violetAssociation(id1,'1','BLACK_DIAMOND',classnode,'paid','',classnode,'HVH','',classnode0,classnode1).
violetAssociation(id2,'1','BLACK_DIAMOND',classnode,'1','DIAMOND',classnode,'HVH','',classnode2,classnode1).
violetAssociation(id3,'1','',classnode,'1','BLACK_DIAMOND',classnode,'','',classnode2,classnode4).
violetAssociation(id4,'1 belongs_to','',classnode,'* owns','V',classnode,'','',classnode3,classnode4).
violetAssociation(id5,'*','BLACK_DIAMOND',classnode,'*','BLACK_DIAMOND',classnode,'','',classnode3,classnode0).

table(violetMiddleLabels,[id,cid1,cid2,"label"]).
