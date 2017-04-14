package ru.kolaer.permit.dao;

import lombok.NonNull;

import java.util.List;

/**
 * Created by danilovey on 14.04.2017.
 */
public interface BaseDao<T> {
    T persist(@NonNull T entity);
    List<T> persistAll(@NonNull List<T> entity);

    T update(@NonNull T entity);
    List<T> updateAll(@NonNull List<T> entity);

    List<T> findAll();
    T findById(@NonNull Integer id);
}
