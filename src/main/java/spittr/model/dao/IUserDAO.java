package spittr.model.dao;

import spittr.model.entity.User;
import java.util.List;

/**
 * Created by norman on 31/03/17.
 */

public interface IUserDAO {

    User findById(long id);

    User findBySSO(String sso);

    void save(User user);

    void deleteBySSO(String sso);

    List<User> findAllUsers();
}
