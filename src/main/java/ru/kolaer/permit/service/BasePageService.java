package ru.kolaer.permit.service;

import lombok.NonNull;
import ru.kolaer.permit.dto.Page;
import ru.kolaer.permit.entity.BaseEntity;

/**
 * Created by danilovey on 14.04.2017.
 */
public interface BasePageService<T extends BaseEntity> extends BaseService<T> {
    Page<T> getAll(@NonNull Integer number, @NonNull Integer pageSize);
}
