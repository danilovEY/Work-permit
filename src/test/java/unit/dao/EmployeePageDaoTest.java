package unit.dao;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.kolaer.permit.dao.AccountPageDao;
import ru.kolaer.permit.dao.EmployeePageDao;
import ru.kolaer.permit.dto.Page;
import ru.kolaer.permit.entity.AccountEntity;
import ru.kolaer.permit.entity.EmployeeEntity;
import ru.kolaer.permit.entity.RoleEntity;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by danilovey on 18.04.2017.
 */
@DatabaseSetup("/test-config/db/employees.xml")
public class EmployeePageDaoTest extends BaseTestDao {

    @Autowired
    private EmployeePageDao employeePageDao;

    @Test
    public void findFirstEmployee(){
        final EmployeeEntity account = employeePageDao.findById(1);
        assertTrue(account.getId() > 0);
    }

    @Test
    public void findPageEmployee(){
        final Page<EmployeeEntity> account = employeePageDao.findAll(1, 15);
        assertTrue(account.getData().size() > 0);
    }

}