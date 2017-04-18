package ru.kolaer.permit.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.kolaer.permit.dao.BasePageDaoAbstract;
import ru.kolaer.permit.dao.PostPageDao;
import ru.kolaer.permit.entity.PostEntity;

/**
 * Created by danilovey on 18.04.2017.
 */
@Repository
public class PostPageDaoImpl extends BasePageDaoAbstract<PostEntity> implements PostPageDao {

    public PostPageDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Class<PostEntity> getEntityClass() {
        return PostEntity.class;
    }
}
