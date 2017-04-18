package unit.service;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

/**
 * Created by danilovey on 18.04.2017.
 */
@RunWith(SpringRunner.class)
@ContextHierarchy({
        @ContextConfiguration(classes = {AccountPageServiceConfig.class}),
        @ContextConfiguration({"/test-config/spring/test-spring-core-config.xml"})
})
public abstract class BaseTestService {
}
