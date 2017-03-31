package spittr.model.dao;

import spittr.model.entity.Spitter;

import java.util.List;

/**
 * Created by norman on 27/03/17.
 */

//@Repository
public interface ISpitterDAO extends IDAOExt<Spitter> {

    public List<Spitter> findByEmail(String email);

    public List<Spitter> findByFullName(String fullName);

}
