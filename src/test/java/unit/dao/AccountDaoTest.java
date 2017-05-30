package unit.dao;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.kolaer.permit.dao.AccountDao;
import ru.kolaer.permit.dto.AccountDto;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by danilovey on 18.04.2017.
 */
@DatabaseSetup("/test-config/db/employees.xml")
public class AccountDaoTest extends BaseTestDao {

    @Autowired
    private AccountDao accountDao;

    @Test
    public void findFirstEmployee(){
        final AccountDto user1 = accountDao.findAccountByUsername("user1");
        assertNotNull(user1);
    }

}