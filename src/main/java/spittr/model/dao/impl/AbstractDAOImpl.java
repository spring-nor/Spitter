package spittr.model.dao.impl;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

/**
 * Created by norman on 01/04/17.
 */

public abstract class AbstractDAOImpl<PK extends Serializable, T> {

    private final Class<T> persistentClass;

    @SuppressWarnings("unchecked")
    public AbstractDAOImpl() {
        this.persistentClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }

    @Autowired
    private SessionFactory sessionFactory;
//
//    protected Session getSession() {
//        return sessionFactory.getCurrentSession();
//    }

    @SuppressWarnings("unchecked")
    public T getByKey(PK key) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        T resoult = session.get(persistentClass, key);
        session.close();

        return (T) resoult;

//        return (T) getSession().get(persistentClass, key);
    }

    public void persist(T entity) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
//        session.saveOrUpdate(entity);
        session.persist(entity);
//        session.getTransaction().commit();
        // Close the session
        session.close();

//        getSession().persist(entity);
    }

    public void update(T entity) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(entity);
        session.close();

//        getSession().update(entity);
    }

    public void delete(T entity) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(entity);
        session.close();

//        getSession().delete(entity);
    }

//    protected Criteria createEntityCriteria(){
//        Session session = sessionFactory.openSession();
//        session.beginTransaction();
//        Criteria resoult = session.createCriteria(persistentClass);
//        session.close();
//        return resoult;
//
////        return getSession().createCriteria(persistentClass);
//    }


}
