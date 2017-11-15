package hu.rft.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(User.class)
public abstract class User_ {

	public static volatile SingularAttribute<User, String> forename;
	public static volatile SingularAttribute<User, Date> lastLogin;
	public static volatile SingularAttribute<User, String> password;
	public static volatile SingularAttribute<User, String> surname;
	public static volatile SingularAttribute<User, String> loginName;
	public static volatile SingularAttribute<User, String> emailAddr;
	public static volatile SingularAttribute<User, Date> dateOfBirth;
	public static volatile SingularAttribute<User, Date> registeredOn;
	public static volatile SingularAttribute<User, Integer> userId;

}

