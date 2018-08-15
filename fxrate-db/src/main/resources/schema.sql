	
	
	drop table if exists basecurrency; 
	create table basecurrency(
	      id int unsigned not null AUTO_INCREMENT,
	      country varchar_ignorecase(5) not null,      
	      currency varchar_ignorecase(20) not null,      
	      PRIMARY KEY(id)
	);
	
	drop table if exists fxrate; 
	create table fxrate(
	      id int unsigned not null AUTO_INCREMENT,
	      timestamp BIGINT,
	      basecurrency varchar_ignorecase(5),      
	      rate varchar_ignorecase(10000),
	      buyRate varchar_ignorecase(20),
	      sellRate varchar_ignorecase(20),
	      spread varchar_ignorecase(20),
	      PRIMARY KEY(id)
	);
