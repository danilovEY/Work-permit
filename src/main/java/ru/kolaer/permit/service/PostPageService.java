package ru.kolaer.permit.service;

import ru.kolaer.permit.entity.PostEntity;

/**
 * Created by danilovey on 14.04.2017.
 */
public interface PostPageService extends BasePageService<PostEntity> {

    boolean existPost(PostEntity postEntity);
}
