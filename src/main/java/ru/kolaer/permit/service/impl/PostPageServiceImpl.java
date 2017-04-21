package ru.kolaer.permit.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kolaer.permit.dao.PostPageDao;
import ru.kolaer.permit.entity.PostEntity;
import ru.kolaer.permit.service.BasePageServiceAbstract;
import ru.kolaer.permit.service.PostPageService;

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
}
