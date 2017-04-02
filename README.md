
**Hibernate JPA 2 Metamodel generation**

Intellij settings in the Compiler/Annotation Processors are as follows:

Go to `Preferences -> Build, Execution, Deployment -> Annotation Processors`;

Check Enable annotation processing checkbox;

In "_Store generated sources relative to:_" select Module content root.

- "_Obtain processors from project classpath_" is selected.
- Processor FQ name set to: `org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor`
- Annotations Processor options: `fullyAnnotationConfigured = true`

Gradle dependencies:
`compile group: 'org.hibernate', name: 'hibernate-jpamodelgen', version: '$hibernateVersion'
`



/* Populate USER_PROFILE Table */
INSERT INTO USER_PROFILE(type)
VALUES ('USER');

INSERT INTO USER_PROFILE(type)
VALUES ('ADMIN');

INSERT INTO USER_PROFILE(type)
VALUES ('DBA');


/* Populate one Admin User which will further create other users for the application using GUI */
INSERT INTO APP_USER(sso_id, password, first_name, last_name, email)
VALUES ('sam','$2a$10$4eqIF5s/ewJwHK1p8lqlFOEm2QIA0S8g6./Lok.pQxqcxaBZYChRm', 'Sam','Smith','samy@xyz.com');

/* Populate JOIN Table */
INSERT INTO APP_USER_USER_PROFILE (user_id, user_profile_id)
  SELECT auser.id, profile.id FROM app_user auser, user_profile profile
where auser.sso_id='sam' and profile.type='ADMIN';

sam
abc125