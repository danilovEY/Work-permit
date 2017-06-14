package ru.kolaer.permit.service.impl;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kolaer.permit.dao.PostPageDao;
import ru.kolaer.permit.dto.Page;
import ru.kolaer.permit.entity.PostEntity;
import ru.kolaer.permit.service.BasePageServiceAbstract;
import ru.kolaer.permit.service.PostPageService;

import java.util.List;

/**
 * Created by danilovey on 21.04.2017.
 */
@Service
public class PostPageServiceImpl extends BasePageServiceAbstract<PostEntity> implements PostPageService {

    private PostPageDao postPageDao;

    @Autowired
    public PostPageServiceImpl(PostPageDao postPageDao) {
        super(postPageDao);
        this.postPageDao = postPageDao;
    }

    @Override
    public Page<PostEntity> getAll(@NonNull Integer number, @NonNull Integer pageSize) {
        return this.postPageDao.findAll(number, pageSize, false);
    }

    @Override
    public List<PostEntity> getAll() {
        return this.postPageDao.findAll(false);
    }

    @Override
    public PostEntity getById(@NonNull Integer id) {
        return this.postPageDao.findById(id, false);
    }


    @Override
    public PostEntity delete(PostEntity entity) {
        return this.postPageDao.delete(entity, true);
    }

    @Override
    public List<PostEntity> deleteAll(List<PostEntity> entities) {
        return this.postPageDao.deleteAll(entities, true);
    }

    @Override
    public boolean existPost(PostEntity postEntity) {
        return this.postPageDao.existPost(postEntity);
    }
}
