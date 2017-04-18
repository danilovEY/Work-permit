package unit.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.kolaer.permit.component.EmptyObjects;
import ru.kolaer.permit.dao.AccountPageDao;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by danilovey on 18.04.2017.
 */
@Configuration
public class AccountPageServiceConfig {

    @Bean
    public AccountPageDao accountPageDao() {
        AccountPageDao mock = mock(AccountPageDao.class);
        when(mock.getEmptyEntity()).thenReturn(EmptyObjects.DEFAULT_ACCOUNT);
        return mock;
    }

}
