package ru.kolaer.permit.dao;

import ru.kolaer.permit.component.EmptyObjects;
import ru.kolaer.permit.entity.RoleEntity;

/**
 * Created by danilovey on 14.04.2017.
 */
public interface RolePageDao extends BasePageDao<RoleEntity> {
    @Override
    default RoleEntity getEmptyEntity() {
        return EmptyObjects.DEFAULT_ROLE;
    }
}
