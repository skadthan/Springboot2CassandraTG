 CREATE TABLE ashu.customer_disposition (
    ssoid text,
    offername text,
    dispositiontype text,
    recordtime timeuuid,
    layout text,
    placement text,
    style text,
    PRIMARY KEY (ssoid, offername, dispositiontype, recordtime)
);


CREATE TABLE ashu.customer_disposition (
    ssoid text,
    offername text,
    dispositiontype text,
    recordtime timestamp,
    layout text,
    placement text,
    style text,
    PRIMARY KEY (ssoid, offername, dispositiontype, recordtime)
) WITH CLUSTERING ORDER BY (offername ASC, dispositiontype ASC, recordtime ASC);


CREATE TABLE ashu.customer_offer_disposition_lastupdate(
	ssoid text,
    offername text,
    dispositiontype text,
    dispositiondate date,
	PRIMARY KEY (ssoid,offername,dispositiontype));

CREATE TABLE ashu.customer_targetting_history(
	ssoid text,
    offername text,
    lastvieweddate date,
	lastacceptedate date,
    lastclickedate date,
	lastdeclineddate date,
	lastlikeddate date,
	lastdislikedate date,
	lastpostponeddate date,
	lastconvertedate date,
	PRIMARY KEY (ssoid,offername));

CREATE TABLE ashu.customer_offer_disposition_count_by_date(
	ssoid text,
	    offername text,
	    dispositiontype text,
	    dispositiondate date,
	    dispositioncount counter,
	PRIMARY KEY ((ssoid,offername,dispositiontype), dispositiondate));

CREATE TABLE ashu.customer_offer_count_by_date(
	ssoid text,
	    offername text,
	    dispositiondate date,
	    dispositioncount counter,
	PRIMARY KEY ((ssoid,offername), dispositiondate));


CREATE FUNCTION getDays(indate date)
RETURNS NULL ON NULL INPUT
RETURNS int
LANGUAGE java
AS '
int days;
		
		if (indate != null ) {
			String strDate = indate.toString();
			java.time.LocalDate parsedDate = java.time.LocalDate.parse(strDate);
             java.time.Duration duration = java.time.Duration.between(parsedDate.atStartOfDay(),java.time.LocalDate.now().atStartOfDay() );
			 days = (int) duration.toDays();
			
		}	
		else {
			days = 3650;
		}
		return days;
';