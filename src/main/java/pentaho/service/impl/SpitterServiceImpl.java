package pentaho.service.impl;

import org.hibernate.criterion.Criterion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pentaho.model.dao.ISpitterDAO;
import pentaho.model.entity.Spitter;
import pentaho.service.ISpitterService;

import java.util.List;
import java.util.Map;

/**
 * Created by norman on 27/03/17.
 */

@Service
public class SpitterServiceImpl implements ISpitterService {

    @Autowired
    private ISpitterDAO dao;

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true, noRollbackFor = Exception.class)
    public Spitter findById(long id) {
        return dao.findById(id);
    }

    @Transactional(propagation = Propagation.REQUIRED, noRollbackFor = Exception.class)
    public Spitter merge(Spitter persistentE) {
        return dao.merge(persistentE);
    }

    @Transactional(propagation = Propagation.REQUIRED, noRollbackFor = Exception.class)
    public void refresh(Spitter persistentE) {
        dao.refresh(persistentE);
    }

    @Transactional(propagation = Propagation.REQUIRED, noRollbackFor = Exception.class)
    public void remove(Spitter persistentE) {
        dao.remove(persistentE);
    }

    @Transactional(propagation = Propagation.REQUIRED, noRollbackFor = Exception.class)
    public void persist(Spitter persistentE) {
        dao.persist(persistentE);
    }

    @Override
    public List<Spitter> findAll() {
        return dao.findAll();    }

    @Override
    public List<Spitter> findByEmail(String email) {
        return dao.findByEmail(email);
    }

    @Override
    public List<Spitter> findByFullName(String fullName) {
        return dao.findByFullName(fullName);
    }

    @Transactional(propagation = Propagation.REQUIRED, noRollbackFor = Exception.class)
    public List<Spitter> findByCriteria(List<Criterion> criterionList) {
        return dao.findByCriteria(criterionList);
    }

    @Override
    public List<Spitter> findByQuery(String query, Map<String, Object> parameters) {
        return dao.findByQuery(query, parameters);
    }

    @Override
    public Spitter toSpitter(Spitter spitterForm) {
        spitterForm.setFullName(spitterForm.getFirstName() + " - " + spitterForm.getLastName());
        return new Spitter(spitterForm);
    }
}
