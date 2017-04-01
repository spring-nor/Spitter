package spittr.service;

import spittr.model.entity.User;

import java.util.List;

/**
 * Created by norman on 31/03/17.
 */

public interface IUserService {
    User findById(long id);

    User findBySSO(String sso);

    void saveUser(User user);

    void updateUser(User user);

    void deleteUserBySSO(String sso);

    List<User> findAllUsers();

    boolean isUserSSOUnique(Long id, String sso);
}
