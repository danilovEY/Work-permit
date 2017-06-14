package ru.kolaer.permit.dao;

import ru.kolaer.permit.component.EmptyObjects;
import ru.kolaer.permit.entity.PostEntity;

/**
 * Created by danilovey on 14.04.2017.
 */
public interface PostPageDao extends BasePageDao<PostEntity> {
    @Override
    default PostEntity getEmptyEntity() {
        return EmptyObjects.DEFAULT_POST;
    }

    boolean existPost(PostEntity postEntity);
}
