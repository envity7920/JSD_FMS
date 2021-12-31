# JSD_FMS
## REQUIREMENTS
 - jdk: 8
 - java: 1.8
 - maven: 

## DATABASE SETUP
- update your personal credential in src/util/DbConnector.java
- log in to mysql and execute schema:

```
// login to rdbms
mysql -uroot -p

// execute schema
source db/schema.sql
source db/seeds.sql
```

## HOW TO RUN 
 - mvn exec:java
 
## SPECIFICATION
https://drive.google.com/drive/shared-with-me?fbclid=IwAR0gPNni3Q23OqjMuYC1-zgArtIQnPWQeCpYSAGKr-rabLUn2bR4HbaMnfU
