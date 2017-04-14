package ru.kolaer.permit.dao;

import lombok.NonNull;
import ru.kolaer.permit.dto.Page;

/**
 * Created by danilovey on 14.04.2017.
 */
public interface BasePageDao<T> extends BaseDao<T> {
    Page<T> findAll(@NonNull Integer number, @NonNull Integer pageSize);
}
