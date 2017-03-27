package spittr.model.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import spittr.model.entity.Spitter;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by norman on 27/03/17.
 */

@Repository
public class SpitterDAO implements IDAOExt<Spitter> {

//    private EntityManager entityManager;
//
//    @PersistenceContext
//    public void setEntityManager(EntityManager entityManager) {
//        this.entityManager = entityManager;
//    }

//    @Autowired
    private SessionFactory sessionFactory;

    @Inject
    public SpitterDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;		 //<co id="co_InjectSessionFactory"/>
    }

    private Session currentSession() {
        return sessionFactory.getCurrentSession();//<co id="co_RetrieveCurrentSession"/>
    }

    @Override
    public Spitter findById(long id) {
//        Session session = (Session) entityManager.getDelegate();
        Session session = sessionFactory.getCurrentSession();

        Spitter spitter = session.get(Spitter.class, id);
        session.close();
        return spitter;
    }

    @Override
    public Spitter merge(Spitter persistentE) {
        return null;
//        return entityManager.merge(persistentE);
    }

    @Override
    public void refresh(Spitter persistentE) {
//        entityManager.refresh(persistentE);
    }

    @Override
    public void remove(Spitter persistentE) {
//        entityManager.remove(persistentE);
    }

    @Override
    public void persist(Spitter persistentE) {
//        entityManager.persist(persistentE);
    }

    @Override
    public List<Spitter> findByCriteria(List<Criterion> criterionList) {
//        Session session = (Session) entityManager.getDelegate();
////TODO da cambiare
//        Criteria criteria = session.createCriteria(Spitter.class);
//
//        for (Criterion c: criterionList){
//            criteria.add(c);
//        }
//        return Collections.checkedList(criteria.list(), Spitter.class);
        return null;
    }

    @Override
    public List<Spitter> findByQuery(String query, Map<String, Object> parameters) {
//        Query q = entityManager.createQuery(query);
//        for (Map.Entry<String, Object> entry : parameters.entrySet()) {
//            q.setParameter(entry.getKey(), entry.getValue());
//        }
//        return q.getResultList();
        return null;
    }

    public List<Spitter> findByEmail(String email) {
        return null;
    }

    public List<Spitter> findByFullName(String fullName) {
        return null;
    }

}
