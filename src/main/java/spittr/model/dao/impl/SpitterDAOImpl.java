package spittr.model.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import spittr.model.dao.ISpitterDAO;
import spittr.model.entity.Spitter;
import spittr.model.entity.Spitter_;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
//import javax.persistence.metamodel.Metamodel;
import java.util.List;
import java.util.Map;

/**
 * Created by norman on 30/03/17.
 */


@Repository
public class SpitterDAOImpl implements ISpitterDAO {

    private EntityManager entityManager;

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Autowired
    private SessionFactory sessionFactory;

//    @Inject
//    public SpitterDAO(SessionFactory sessionFactory) {
//        this.sessionFactory = sessionFactory;		 //<co id="co_InjectSessionFactory"/>
//    }
//
//    private Session currentSession() {
//        return sessionFactory.getCurrentSession();//<co id="co_RetrieveCurrentSession"/>
//    }

    @Override
    public Spitter findById(long id) {
//        Session session = (Session) entityManager.getDelegate();
        Session session = sessionFactory.openSession();

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
        // Open a session
        Session session = sessionFactory.openSession();
        // Begin a transaction
        session.beginTransaction();
        // Save the category
        session.saveOrUpdate(persistentE);
        // Commit the transaction
        session.getTransaction().commit();
        // Close the session
        session.close();
    }

    @Override
    public List<Spitter> findAll() {
        // Open a session
        Session session = sessionFactory.openSession();

        // DEPRECATED as of Hibernate 5.2.0
        // List<Category> categories = session.createCriteria(Category.class).list();

        // Create CriteriaBuilder
        CriteriaBuilder builder = session.getCriteriaBuilder();

        // Create CriteriaQuery
        CriteriaQuery<Spitter> criteria = builder.createQuery(Spitter.class);

        // Specify criteria root
        criteria.from(Spitter.class);

        // Execute query
        List<Spitter> spitter = session.createQuery(criteria).getResultList();

        // Close session
        session.close();

        return spitter;
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
        Session session = sessionFactory.openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Spitter> cq = cb.createQuery(Spitter.class);

        Root<Spitter> spitter = cq.from(Spitter.class);
//        EntityType<Spitter> Spitter_ = spitter.getModel();
//        cq.where(cb.equal(spitter.get(Spitter_.getSingularAttribute("email")), email));
        cq.where(cb.equal(spitter.get(Spitter_.email), email));

//      Execute query
        TypedQuery<Spitter> q = session.createQuery(cq);
        List<Spitter> spitterSelectede = q.getResultList();

        return spitterSelectede;
    }

    public List<Spitter> findByFullName(String fullName) {
        return null;
    }

}
