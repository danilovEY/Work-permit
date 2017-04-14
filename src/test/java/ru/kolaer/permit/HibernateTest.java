package ru.kolaer.permit;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import ru.kolaer.permit.dao.EmployeePageDao;
import ru.kolaer.permit.dto.Page;
import ru.kolaer.permit.entity.DepartmentEntity;
import ru.kolaer.permit.entity.EmployeeEntity;
import ru.kolaer.permit.entity.PostEntity;

import java.util.Date;

/**
 * Created by danilovey on 14.04.2017.
 */
@Slf4j
public class HibernateTest {

    private FileSystemXmlApplicationContext fileSystemXmlApplicationContext;

    @Before
    public void initializeContext() {
        this.fileSystemXmlApplicationContext = new FileSystemXmlApplicationContext("src/main/webapp/WEB-INF/config/spring/spring-core-config.xml");
    }

    @Test
    public void testHibernateConnection() {
        log.debug("Hibernate is init!");
    }

    @Ignore
    @Test
    public void testHibernateInsert() {
        final SessionFactory bean = this.fileSystemXmlApplicationContext.getBean(SessionFactory.class);
        final Session session = bean.openSession();
        final Transaction transaction = session.getTransaction();

        final DepartmentEntity departmentEntity = new DepartmentEntity();
        departmentEntity.setId(1);
        departmentEntity.setAbbreviatedName("аб");
        departmentEntity.setName("абабабаб");

        final PostEntity postEntity = new PostEntity();
        postEntity.setAbbreviatedName("123");
        postEntity.setName("3333");
        postEntity.setRang(1);
        postEntity.setTypeRang("");

        final EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setDepartment(departmentEntity);
        employeeEntity.setPost(postEntity);
        employeeEntity.setInitials("123 123 123");
        employeeEntity.setBirthday(new Date());
        employeeEntity.setPersonalNumber(123);

        transaction.begin();
        session.persist(postEntity);
        session.persist(employeeEntity);
        transaction.commit();

        session.close();
    }

    @Test
    public void testGetAllEmployee() {
        final EmployeePageDao bean = this.fileSystemXmlApplicationContext.getBean(EmployeePageDao.class);
        final Page<EmployeeEntity> all = bean.findAll(1, 15);
        Assert.assertTrue(all.getData().size() > 0);
    }

}
