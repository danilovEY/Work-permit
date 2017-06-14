package ru.kolaer.permit.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.kolaer.permit.dao.BasePageDaoAbstract;
import ru.kolaer.permit.dao.PostPageDao;
import ru.kolaer.permit.entity.PostEntity;

import java.util.Optional;

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

    @Override
    @Transactional(readOnly = true)
    public boolean existPost(PostEntity postEntity) {
        return this.sessionFactory.getCurrentSession()
                .createQuery("SELECT id FROM PostEntity p WHERE p.name = :name AND p.id <> :id")
                .setParameter("id", Optional.ofNullable(postEntity.getId()).orElse(-1))
                .setParameter("name", postEntity.getName())
                .uniqueResultOptional()
                .isPresent();
    }
}
