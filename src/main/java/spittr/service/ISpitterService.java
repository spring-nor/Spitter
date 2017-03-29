package spittr.service;

import spittr.model.entity.Spitter;

import java.util.List;

/**
 * Created by norman on 27/03/17.
 */

public interface ISpitterService extends IDAOExtService<Spitter> {
    public List<Spitter> findByEmail(String email);
    public List<Spitter> findByFullName(String fullName);

    public Spitter toSpitter(Spitter spitterForm);
}
