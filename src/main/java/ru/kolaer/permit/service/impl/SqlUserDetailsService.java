package ru.kolaer.permit.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.kolaer.permit.dao.AccountDtoDao;
import ru.kolaer.permit.dto.AccountDto;
import ru.kolaer.permit.entity.RoleEntity;

import java.util.stream.Collectors;

/**
 * Created by danilovey on 14.04.2017.
 */
public class SqlUserDetailsService implements UserDetailsService {

    private final AccountDtoDao accountDtoDao;
    private final BCryptPasswordEncoder encoder;

    @Autowired
    public SqlUserDetailsService(AccountDtoDao accountDtoDao, BCryptPasswordEncoder encoder) {
        this.accountDtoDao = accountDtoDao;
        this.encoder = encoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final AccountDto account = this.accountDtoDao.findAccountByUsername(username);
        if(account == null)
            throw new UsernameNotFoundException("Пользователь \"" + username + "\" не найден!");

        return new User(username, encoder.encode(account.getPassword()),
                true, true, true, true, account.getRoles().stream()
                .map(RoleEntity::getRole)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList()));
    }
}
