CREATE TABLE 'person' ('personId' SERIAL PRIMARY KEY,'birthday' DATE NULL, 'employmentStatus' VARCHAR(355) NULL, 'gwa' REAL NULL, 'gender' VARCHAR(15) NULL);

CREATE TABLE name (personId INT PRIMARY KEY, firstName VARCHAR(100) NULL, middleName VARCHAR(100) NULL, lastName VARCHAR(100) NULL, suffix VARCHAR(100) NULL, title VARCHAR(100) NULL, CONSTRAINT fk_name_person FOREIGN KEY (personId) REFERENCES person (personId));

CREATE TABLE address (personId INT PRIMARY KEY, stNo INT NULL, brgy VARCHAR(355) NULL, subdivision VARCHAR(355) NULL, city VARCHAR(355) NULL, zipcode INT NULL, CONSTRAINT fk_address_person FOREIGN KEY (personId) REFERENCES person (personId));

CREATE TABLE contact (contactId SERIAL PRIMARY KEY, description VARCHAR(20) NULL, number BIGINT NULL, personId INT NULL, CONSTRAINT fk_contacts_person FOREIGN KEY (personId) REFERENCES person (personId));

