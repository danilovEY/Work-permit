package unit.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.kolaer.permit.entity.AccountEntity;
import ru.kolaer.permit.service.AccountPageService;
import unit.service.BaseTestService;

/**
 * Created by danilovey on 18.04.2017.
 */
public class AccountPageServiceTest extends BaseTestService {

    @Autowired
    private AccountPageService accountPageService;

    @Test
    private void test() {
        final AccountEntity accountEntity = this.accountPageService.getById(1);
    }

}
