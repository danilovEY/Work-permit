package ru.kolaer.permit.service;

import lombok.NonNull;
import ru.kolaer.permit.entity.BaseEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by danilovey on 14.04.2017.
 */
public interface BaseService<T extends BaseEntity> {
    List<Exception> getExceptions();

    T add(@NonNull T entity);
    List<T> addAll(@NonNull List<T> entity);

    T saveOrUpdate(@NonNull T entity);
    List<T> saveOrUpdateAll(@NonNull List<T> entity);

    T update(@NonNull T entity);
    List<T> updateAll(@NonNull List<T> entity);

    List<T> getAll();
    T getById(@NonNull Integer id);

}
