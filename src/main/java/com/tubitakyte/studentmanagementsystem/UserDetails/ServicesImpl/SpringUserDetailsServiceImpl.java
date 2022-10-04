package com.tubitakyte.studentmanagementsystem.UserDetails.ServicesImpl;

import com.tubitakyte.studentmanagementsystem.User.DataAccess.UserDao;
import com.tubitakyte.studentmanagementsystem.User.entity.User;
import com.tubitakyte.studentmanagementsystem.UserDetails.Services.SpringUserDetailsService;
import com.tubitakyte.studentmanagementsystem.UserDetails.entity.UserDetailsManager;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class SpringUserDetailsServiceImpl implements SpringUserDetailsService {

    private final  UserDao<User> userDao;
    private final static String USER_NOT_FOUND = "User with that username not found.";

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user= userDao.findByUsernameQuery(username);
        //System.out.println("somebody called me, loadByUsername");
        if(user==null) {
            throw new UsernameNotFoundException(USER_NOT_FOUND);
        }
        return UserDetailsManager.build(user);
    }
}
