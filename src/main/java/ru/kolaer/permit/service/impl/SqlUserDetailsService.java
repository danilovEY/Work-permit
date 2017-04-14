package ru.kolaer.permit.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kolaer.permit.dao.AccountDao;

import java.util.Collections;

/**
 * Created by danilovey on 14.04.2017.
 */
public class SqlUserDetailsService implements UserDetailsService {

    private final AccountDao accountDao;

    @Autowired
    public SqlUserDetailsService(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new User(username, "password",
                true, true, true, true, Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
    }
}
