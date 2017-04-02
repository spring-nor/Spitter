package spittr.model.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import spittr.model.dao.IUserProfileDAO;
import spittr.model.entity.UserProfile;
import spittr.model.entity.UserProfile_;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by norman on 01/04/17.
 */

@Repository("userProfileDao")
public class UserProfileDAOImpl extends AbstractDAOImpl<Long, UserProfile> implements IUserProfileDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<UserProfile> findAll() {

        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<UserProfile> criteria = builder.createQuery(UserProfile.class);
        Root<UserProfile> userProfileRoot = criteria.from(UserProfile.class);
//        criteria.from(UserProfile.class);
        criteria.orderBy(builder.asc(userProfileRoot.get("type")));
        List<UserProfile> userProfile = session.createQuery(criteria).getResultList();
        session.close();
        return userProfile;


//        Criteria crit = createEntityCriteria();
//        crit.addOrder(Order.asc("type"));
//        return (List<UserProfile>)crit.list();
    }

    @Override
    public UserProfile findByType(String type) {
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<UserProfile> criteria = builder.createQuery(UserProfile.class);
        Root<UserProfile> userProfileRoot = criteria.from(UserProfile.class);
//        criteria.from(UserProfile.class);
        criteria.where(builder.equal(userProfileRoot.get(UserProfile_.type), type));

        TypedQuery<UserProfile> typed = session.createQuery(criteria);
        UserProfile userProfileList = typed.getSingleResult();
        session.close();
        return userProfileList;


//        Criteria crit = createEntityCriteria();
//        crit.add(Restrictions.eq("type", type));
//        return (UserProfile) crit.uniqueResult();
    }

    @Override
    public UserProfile findById(long id) {
        return getByKey(id);
    }
}
