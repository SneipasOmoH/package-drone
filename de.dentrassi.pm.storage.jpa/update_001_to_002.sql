ALTER TABLE ARTIFACTS
    ADD COLUMN `TYPE` VARCHAR(8) NOT NULL;
	
UPDATE ARTIFACTS SET `TYPE`='S';

CREATE TABLE VIRTUAL_ARTIFACTS (
    ID            VARCHAR(36) NOT NULL,
    
    PARENT        VARCHAR(36),
    
    NS            VARCHAR(255) NOT NULL,
    
    PRIMARY KEY (ID),
    FOREIGN KEY (PARENT) REFERENCES ARTIFACTS(ID)
);