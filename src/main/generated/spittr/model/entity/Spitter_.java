package spittr.model.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Spitter.class)
public abstract class Spitter_ {

	public static volatile SingularAttribute<Spitter, String> firstName;
	public static volatile SingularAttribute<Spitter, String> lastName;
	public static volatile SingularAttribute<Spitter, String> password;
	public static volatile SingularAttribute<Spitter, String> fullName;
	public static volatile SingularAttribute<Spitter, Long> id;
	public static volatile SingularAttribute<Spitter, String> email;
	public static volatile SingularAttribute<Spitter, Boolean> updateByEmail;
	public static volatile SingularAttribute<Spitter, String> username;

}

