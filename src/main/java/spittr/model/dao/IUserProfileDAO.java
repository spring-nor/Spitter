package spittr.model.dao;

import spittr.model.entity.UserProfile;

import java.util.List;

/**
 * Created by norman on 31/03/17.
 */

public interface IUserProfileDAO {

    List<UserProfile> findAll();

    UserProfile findByType(String type);

    UserProfile findById(long id);
}
