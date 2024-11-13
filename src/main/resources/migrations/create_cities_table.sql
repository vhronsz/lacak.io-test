CREATE TABLE IF NOT EXISTS cities(
	id BIGINT  PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(200)  NOT NULL,
	latitude DOUBLE(11,8) ,
	longitude DOUBLE(11,8),
	country_code char(2),
	fips_code varchar(20),
	county_code varchar(80)
);