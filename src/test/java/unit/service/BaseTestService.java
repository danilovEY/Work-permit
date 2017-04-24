package unit.service;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by danilovey on 18.04.2017.
 */
@RunWith(SpringRunner.class)
@ContextHierarchy({
        @ContextConfiguration({"/test-config/spring/test-spring-core-config.xml"})
})
public abstract class BaseTestService {
}
