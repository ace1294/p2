
dbase(vpl,[violetMiddleLabels,violetAssociation,violetInterface,violetClass]).

table(violetClass,[id,"name","fields","methods",x,y]).
violetClass(classnode0,'DecayText','','toString',863,252).
violetClass(classnode1,'DecayProduct','','getName();getType();getTextName()',623,48).
violetClass(classnode2,'ParticleType','','getAntiName();getantiTexName();GetData();getDecayChannel();getPDGID()',645,249).
violetClass(classnode3,'Family','','getFamily();getParticle()',428,249).
violetClass(classnode4,'Yappi','','getFamily;getParticle()',303,424).
violetClass(classnode5,'XMLYappi','','',296,590).
violetClass(classnode6,'Data','','getName();getConfidenceLevel();getNegError();getPosError();getScaleFactor();getUnit()',647,522).
violetClass(classnode7,'DecayGroup','','getName',880,538).
violetClass(classnode8,'DecayChannel','','getConfidenceLevel();getDecayGroup();getDecayParticles();getFraction();getName()',1049,402).

table(violetInterface,[id,"name","methods",x,y]).

table(violetAssociation,[id,"role1","arrow1",type1,"role2","arrow2",type2,"bentStyle","lineStyle",cid1,cid2]).
violetAssociation(id0,'','',classnode,'','TRIANGLE',classnode,'VHV','',classnode3,classnode1).
violetAssociation(id1,'','',classnode,'','TRIANGLE',classnode,'VHV','',classnode2,classnode1).
violetAssociation(id2,'','',classnode,'','TRIANGLE',classnode,'VHV','',classnode0,classnode1).
violetAssociation(id3,'','',classnode,'','TRIANGLE',classnode,'VHV','',classnode5,classnode4).
violetAssociation(id4,'n','',classnode,'1','V',classnode,'VH','',classnode8,classnode1).
violetAssociation(id5,'1','',classnode,'n','V',classnode,'VH','',classnode3,classnode2).
violetAssociation(id6,'1','',classnode,'n','V',classnode,'HVH','',classnode3,classnode3).
violetAssociation(id7,'1','',classnode,'n','V',classnode,'VH','',classnode4,classnode3).
violetAssociation(id8,'1','',classnode,'n','V',classnode,'','',classnode4,classnode2).
violetAssociation(id9,'1','',classnode,'n','V',classnode,'HVH','',classnode2,classnode6).
violetAssociation(id10,'1','',classnode,'n','V',classnode,'HVH','',classnode2,classnode7).
violetAssociation(id11,'','DIAMOND',classnode,'1','V',classnode,'HVH','',classnode8,classnode7).
violetAssociation(id12,'1','',classnode,'n','V',classnode,'','',classnode2,classnode8).

table(violetMiddleLabels,[id,cid1,cid2,"label"]).
violetMiddleLabels(id13,classnode8,classnode1,'list').
violetMiddleLabels(id14,classnode3,classnode2,'map').
violetMiddleLabels(id15,classnode4,classnode3,'map').
violetMiddleLabels(id16,classnode4,classnode2,'pdgidName').
violetMiddleLabels(id17,classnode2,classnode6,'map').
violetMiddleLabels(id18,classnode2,classnode7,'map').
