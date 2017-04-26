package ru.kolaer.permit.dao;

import lombok.NonNull;
import ru.kolaer.permit.entity.BaseEntity;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by danilovey on 14.04.2017.
 */
public interface BaseDao<T extends BaseEntity> {
    T persist(@NonNull T entity);
    List<T> persistAll(@NonNull List<T> entity);

    T update(@NonNull T entity);
    List<T> updateAll(@NonNull List<T> entity);

    List<T> findAll(boolean findRemoved);
    T findById(@NonNull Integer id, boolean findRemoved);

    T delete(T entity);
    List<T> deleteAll(List<T> entities);

    Class<T> getEntityClass();

    T getEmptyEntity();

    default List<T> batchForeach(List<T> entities,
                                              int batchSize,
                                              EntityManager entityManager,
                                              Consumer<Object> consumer) {
        for (int i=0; i < entities.size(); i++) {
            consumer.accept(entities.get(i));
            if(i % batchSize == 0){
                entityManager.flush();
                entityManager.clear();
            }
        }
        return entities;
    }
}
