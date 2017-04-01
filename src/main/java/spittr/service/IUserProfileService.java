package spittr.service;

import spittr.model.entity.UserProfile;
import java.util.List;

/**
 * Created by norman on 01/04/17.
 */

public interface IUserProfileService {

    UserProfile findById(long id);

    UserProfile findByType(String type);

    List<UserProfile> findAll();
}
