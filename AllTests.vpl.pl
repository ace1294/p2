dbase(vpl,[violetMiddleLabels,violetAssociation,violetInterface,violetClass]).

table(violetClass,[id,"name","fields","methods",x,y]).

violetClass(class0,'ClassA','','',339,168).
violetClass(class1,'ClassA','','',339,168).

violetClass(class1,'','','',339,168).

table(violetInterface,[id,"name","fields","methods",x,y]).
violetInterface(interface0,'InterfaceA','','',649,166).
violetInterface(interface0,'InterfaceA','','',649,166).

violetInterface(interface0,'ClassA','','',649,166).

violetInterface(interface0,'','','',649,166).


table(violetAssociation,[id,"role1","arrow1",type1,"role2","arrow2",type2,"bentStyle","lineStyle",cid1,cid2]).
violetAssociation(bDiamondRule1,'','BLACK_DIAMOND',classnode,'','',classnode,'VH','',class0,class1).
violetAssociation(bDiamondRule2,'','',classnode,'','BLACK_DIAMOND',classnode,'VH','',class0,class1).

violetAssociation(diamondRule1,'','DIAMOND',classnode,'','',classnode,'VH','',class0,class1).
violetAssociation(diamondRule2,'','',classnode,'','DIAMOND',classnode,'VH','',class0,class1).

violetAssociation(triangleRule1,'','TRIANGLE',classnode,'','DIAMOND',classnode,'VH','',class0,class1).
violetAssociation(triangleRule2,'','DIAMOND',classnode,'','TRIANGLE',classnode,'VH','',class0,class1).

violetAssociation(dottedInheritanceRule1,'','',classnode,'','',classnode,'VH','DOTTED',class0,class1).
violetAssociation(dottedInheritanceRule2,'','',interfaces,'','',interfaces,'VH','DOTTED',class0,class1).

violetAssociation(dottedAssociationRule,'','',classnode,'','',classnode,'VH','DOTTED',class0,class1).


table(violetMiddleLabels,[id,cid1,cid2,"label"]).