package spittr.service.impl;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import spittr.service.ICustomUserDetailsService;
import spittr.service.IUserService;

/**
 * Created by norman on 31/03/17.
 */

@Service("customUserDetailsService")
public class CustomUserDetailsServiceImpl implements ICustomUserDetailsService{
//    static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsServiceImpl.class);

    @Autowired
    private IUserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
