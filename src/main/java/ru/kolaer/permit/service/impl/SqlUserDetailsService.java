package ru.kolaer.permit.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.kolaer.permit.dao.AccountDao;
import ru.kolaer.permit.dto.AccountDto;
import ru.kolaer.permit.entity.RoleEntity;

import java.util.Collections;
import java.util.stream.Collectors;

/**
 * Created by danilovey on 14.04.2017.
 */
@Slf4j
public class SqlUserDetailsService implements UserDetailsService {

    private final boolean isUser;
    private final String defaultLogin;
    private final String defaultPass;

    private final AccountDto defaultAccount;
    private final AccountDao accountDao;
    private final PasswordEncoder encoder;

    @Autowired
    public SqlUserDetailsService(@Value("${default.user}")boolean isUser,
                                 @Value("${default.login}") String defaultLogin,
                                 @Value("${default.pass}")String defaultPass,
                                 AccountDao accountDao,
                                 PasswordEncoder encoder) {
        this.isUser = isUser;
        this.defaultLogin = defaultLogin;
        this.defaultPass = defaultPass;
        this.accountDao = accountDao;
        this.encoder = encoder;

        final RoleEntity roleEntity = new RoleEntity();
        roleEntity.setRole("ROLE_DEFAULT");

        this.defaultAccount = new AccountDto();
        this.defaultAccount.setUsername(this.defaultLogin);
        this.defaultAccount.setPassword(this.defaultPass);
        this.defaultAccount.setRoles(Collections.singletonList(roleEntity));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AccountDto account = this.accountDao.findAccountByUsername(username);
        if(account == null) {
            if(this.isUser) {
                log.info("SET DEFAULT USER");
                account = this.defaultAccount;
            } else {
                throw new UsernameNotFoundException("Пользователь \"" + username + "\" не найден!");
            }
        }

        return new User(username, encoder.encode(account.getPassword()),
                true, true, true, true, account.getRoles().stream()
                .map(RoleEntity::getRole)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList()));
    }
}
