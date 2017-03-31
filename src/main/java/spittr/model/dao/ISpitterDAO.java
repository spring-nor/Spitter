package spittr.model.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import spittr.model.entity.Spitter;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;
import java.util.Map;

/**
 * Created by norman on 27/03/17.
 */

//@Repository
public interface ISpitterDAO extends IDAOExt<Spitter> {

    public List<Spitter> findByEmail(String email);

    public List<Spitter> findByFullName(String fullName);

}
