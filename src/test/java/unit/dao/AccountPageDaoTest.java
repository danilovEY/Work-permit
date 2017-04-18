package unit.dao;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.kolaer.permit.dao.AccountPageDao;
import ru.kolaer.permit.entity.AccountEntity;
import ru.kolaer.permit.entity.EmployeeEntity;
import ru.kolaer.permit.entity.RoleEntity;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by danilovey on 18.04.2017.
 */
@DatabaseSetup("/test-config/db/accounts.xml")
public class AccountPageDaoTest extends BaseTestDao {

    @Autowired
    private AccountPageDao accountPageDao;

    @Test
    public void findFirstAccountWithEmployee(){
        final AccountEntity account = accountPageDao.findById(1);
        assertTrue(account.getId() > 0);

        final EmployeeEntity employeeEntity = this.accountPageDao.findEmployeeByIdAccount(account.getId());
        assertTrue(employeeEntity.getId() > 0);
        System.out.println(employeeEntity.getBirthday());
    }

    @Test
    public void findFirstAccountWithRoles(){
        final AccountEntity account = accountPageDao.findById(1);
        assertTrue(account.getId() > 0);

        final List<RoleEntity> roles = this.accountPageDao.findRoleByIdAccount(account.getId());
        assertFalse(roles.isEmpty());
    }

}