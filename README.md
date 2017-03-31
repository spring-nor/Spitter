
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