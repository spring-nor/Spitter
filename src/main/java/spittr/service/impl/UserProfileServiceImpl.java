package spittr.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spittr.model.dao.IUserProfileDAO;
import spittr.model.entity.UserProfile;
import spittr.service.IUserProfileService;
import java.util.List;

/**
 * Created by norman on 01/04/17.
 */

@Service("userProfileService")
@Transactional
public class UserProfileServiceImpl implements IUserProfileService {

    @Autowired
    private IUserProfileDAO dao;

    @Override
    public UserProfile findById(long id) {
        return dao.findById(id);
    }

    @Override
    public UserProfile findByType(String type) {
        return dao.findByType(type);
    }

    @Override
    public List<UserProfile> findAll() {
        return dao.findAll();
    }
}
