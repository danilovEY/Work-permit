package unit.dao;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.kolaer.permit.dao.AccountDtoDao;
import ru.kolaer.permit.dao.EmployeePageDao;
import ru.kolaer.permit.dto.AccountDto;
import ru.kolaer.permit.dto.Page;
import ru.kolaer.permit.entity.EmployeeEntity;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by danilovey on 18.04.2017.
 */
@DatabaseSetup("/test-config/db/employees.xml")
public class AccountDtoDaoTest extends BaseTestDao {

    @Autowired
    private AccountDtoDao accountDtoDao;

    @Test
    public void findFirstEmployee(){
        final AccountDto user1 = accountDtoDao.findAccountByUsername("user1");
        assertNotNull(user1);
    }

}