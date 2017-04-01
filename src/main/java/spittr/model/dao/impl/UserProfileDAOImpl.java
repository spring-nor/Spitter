package spittr.model.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import spittr.model.dao.IUserProfileDAO;
import spittr.model.entity.UserProfile;
import java.util.List;

/**
 * Created by norman on 01/04/17.
 */

@Repository("userProfileDao")
public class UserProfileDAOImpl extends AbstractDAOImpl<Long, UserProfile> implements IUserProfileDAO {

    @Override
    public List<UserProfile> findAll() {
        Criteria crit = createEntityCriteria();
        crit.addOrder(Order.asc("type"));
        return (List<UserProfile>)crit.list();
    }

    @Override
    public UserProfile findByType(String type) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("type", type));
        return (UserProfile) crit.uniqueResult();
    }

    @Override
    public UserProfile findById(long id) {
        return getByKey(id);
    }
}
