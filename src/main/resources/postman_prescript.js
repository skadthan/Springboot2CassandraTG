var offers = ["Offer1","Offer2","Offer3","Offer4","Offer5","Offer6","Offer7","Offer8","Offer9","Offer10","Offer11","Offer12","Offer13","Offer14","Offer15","Offer16","Offer17","Offer18","Offer19","Offer20","Offer21","Offer22","Offer23","Offer24"]

var offer = offers[Math.floor(Math.random()*offers.length)];

var ssoid = Math.random().toString(36).substring(2, 15) + Math.random().toString(36).substring(2, 15)+Math.random().toString(36).substring(2, 15);
ssoid=ssoid.toString(1,34);

var dispotypes = ["Clicked","Viewed","Converted","Accepted","Declined","Liked","Disliked","Postponed"];

var disptype = dispotypes[Math.floor(Math.random()*dispotypes.length)];

var layouts = ["L1","L2","L3","L4","L5","L6","L7","L8"];

var layout = layouts[Math.floor(Math.random()*layouts.length)];

var placements = ["CustomerMessage","AccountMessage","InterPage","AccountAction","Mudflap","CustomerAction"];

var placement = placements[Math.floor(Math.random()*placements.length)];

var styles = ["S1","S2","S3","S4","S5","S6","S7","S8"];

var style = styles[Math.floor(Math.random()*styles.length)];

postman.setEnvironmentVariable("offername", offer);
postman.setEnvironmentVariable("ssoid", ssoid);
postman.setEnvironmentVariable("disptype", disptype);
postman.setEnvironmentVariable("layout", layout);
postman.setEnvironmentVariable("placement", placement);
postman.setEnvironmentVariable("style", style);

