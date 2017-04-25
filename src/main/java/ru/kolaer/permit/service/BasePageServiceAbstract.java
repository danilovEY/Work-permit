package ru.kolaer.permit.service;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import ru.kolaer.permit.dao.BasePageDao;
import ru.kolaer.permit.dto.Page;
import ru.kolaer.permit.entity.BaseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created by danilovey on 18.04.2017.
 */
@Slf4j
public abstract class BasePageServiceAbstract<T extends BaseEntity> implements BasePageService<T> {
    private final List<Exception> exceptions = new ArrayList<>();
    private final BasePageDao<T> dao;

    public BasePageServiceAbstract(BasePageDao<T> dao) {
        this.dao = dao;
    }

    @Override
    public Page<T> getAll(@NonNull Integer number, @NonNull Integer pageSize) {
        return this.dao.findAll(number, pageSize);
    }

    @Override
    public T add(@NonNull T entity) {
        if(entity.getId() != null)
            throw new IllegalArgumentException(this.dao.getEntityClass().getName() +
                    " - ID должен быть пустым!");

        return this.dao.persist(entity);
    }

    @Override
    public List<T> addAll(@NonNull List<T> entity) {
        if(entity.isEmpty())
            return entity;

        final long countIdNulls = entity.stream()
                .map(T::getId)
                .filter(Objects::nonNull)
                .count();

        if(countIdNulls > 0)
            throw new IllegalArgumentException(this.dao.getEntityClass().getName() +
                    " - ID должены быть пустыми!");

        return this.dao.persistAll(entity);
    }

    @Override
    public T saveOrUpdate(@NonNull T entity) {
        return entity.getId() == null
                ? this.dao.persist(entity)
                : this.dao.update(entity);
    }

    @Override
    public List<T> saveOrUpdateAll(@NonNull List<T> entity) {
        if (entity.isEmpty())
            return entity;

        final List<T> entityWithId = entity.stream()
                .filter(e -> e.getId() != null)
                .collect(Collectors.toList());

        final List<T> entityWithOutId = entity.stream()
                .filter(e -> e.getId() == null)
                .collect(Collectors.toList());

        final List<T> result = new ArrayList<>();
        result.addAll(this.updateAll(entityWithId));
        result.addAll(this.addAll(entityWithOutId));

        return result;
    }

    @Override
    public T update(@NonNull T entity) {
        if(entity.getId() == null && entity.getId() < 1)
            throw new IllegalArgumentException(this.dao.getEntityClass().getName() +
                    " - ID не должен быть пустым или меньше 1!");

        return this.dao.update(entity);
    }

    @Override
    public List<T> updateAll(@NonNull List<T> entity) {
        if(entity.isEmpty())
            return entity;

        final long countIdNulls = entity.stream()
                .map(T::getId)
                .filter(id -> id == null || id < 1)
                .count();

        if(countIdNulls > 0)
            throw new IllegalArgumentException(this.dao.getEntityClass().getName() +
                    " - ID должены быть пустыми или больше 1!");

        return this.dao.updateAll(entity);
    }

    @Override
    public List<T> getAll() {
        return this.dao.findAll();
    }

    @Override
    public T getById(@NonNull Integer id) {
        return id < 1
                ? this.dao.getEmptyEntity()
                : this.dao.findById(id);
    }

    protected void addException(Exception ex) {
        this.exceptions.add(ex);
        log.error("Ошибка в сервисе!", ex);
    }

    @Override
    public List<Exception> getExceptions() {
        return this.exceptions;
    }

    @Override
    public T delete(T entity) {
        if(entity.getId() == null)
            return entity;
        return this.dao.delete(entity);
    }

    @Override
    public List<T> deleteAll(List<T> entities) {
        return this.dao.deleteAll(entities);
    }
}
