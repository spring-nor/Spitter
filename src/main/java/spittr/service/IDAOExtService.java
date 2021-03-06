package spittr.service;

import org.hibernate.criterion.Criterion;

import java.util.List;
import java.util.Map;

/**
 * Created by norman on 27/03/17.
 */

public interface IDAOExtService<E> extends IDAOService<E> {
    public List<E> findByCriteria(List<Criterion> criterionList);
    public List<E> findByQuery(String query, Map<String, Object> parameters);
}
