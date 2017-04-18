package unit.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.kolaer.permit.component.EmptyObjects;
import ru.kolaer.permit.dao.AccountPageDao;
import ru.kolaer.permit.entity.AccountEntity;
import ru.kolaer.permit.service.AccountPageService;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.when;

/**
 * Created by danilovey on 18.04.2017.
 */
public class UniformPageServiceTestByAccount extends BaseTestService {

    @Autowired
    private AccountPageDao mockAccountPageDao;

    @Autowired
    private AccountPageService accountPageService;

    @Test
    public void getAccountByIdIfAboveZero() {
        when(mockAccountPageDao.findById(1)).thenReturn(new AccountEntity());
        final AccountEntity accountEntity = this.accountPageService.getById(100);
        assertNotEquals(accountEntity, EmptyObjects.DEFAULT_ACCOUNT);
    }

    @Test
    public void getAccountByIdIfLessZero() {
        final AccountEntity accountEntity = this.accountPageService.getById(-100);
        assertEquals(accountEntity, EmptyObjects.DEFAULT_ACCOUNT);
    }

    @Test(expected = NullPointerException.class)
    public void getAccountByIdIfNull() {
        this.accountPageService.getById(null);
    }

}
