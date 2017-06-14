package ru.kolaer.permit.dao.impl;

import lombok.NonNull;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.kolaer.permit.dao.BasePageDaoAbstract;
import ru.kolaer.permit.dao.RolePageDao;
import ru.kolaer.permit.dto.Page;
import ru.kolaer.permit.entity.EmployeeEntity;
import ru.kolaer.permit.entity.RoleEntity;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Created by danilovey on 18.04.2017.
 */
@Repository
public class RolePageDaoImpl extends BasePageDaoAbstract<RoleEntity> implements RolePageDao {

    public RolePageDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Class<RoleEntity> getEntityClass() {
        return RoleEntity.class;
    }

    @Override
    @Transactional(readOnly = true)
    public List<RoleEntity> findRolesByIdEmployee(Integer idEmp) {
        return this.sessionFactory.getCurrentSession()
                .createQuery("FROM FullRoleEntity r WHERE r.employee.id = :id")
                .setParameter("id", idEmp)
                .list();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<RoleEntity> findAll(@NonNull Integer number, @NonNull Integer pageSize, boolean findRemoved, Integer sort) {
        final Session currentSession = this.sessionFactory.getCurrentSession();
        final CriteriaBuilder criteriaBuilder = currentSession.getCriteriaBuilder();
        final CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        final Root<RoleEntity> fromCount = countQuery.from(this.getEntityClass());
        CriteriaQuery<Long> selectCount = countQuery.select(criteriaBuilder.count(fromCount.get("id")));

        if(!findRemoved)
            selectCount = selectCount.where(criteriaBuilder.equal(fromCount.get("removed"), false));

        final Long count = currentSession
                .createQuery(selectCount)
                .getSingleResult();

        final CriteriaQuery<RoleEntity> selectQuery = criteriaBuilder.createQuery(this.getEntityClass());
        final Root<RoleEntity> fromSelect = selectQuery.from(this.getEntityClass());
        CriteriaQuery<RoleEntity> select = selectQuery.select(fromSelect);

        if(!findRemoved)
            select = select.where(criteriaBuilder.equal(fromSelect.get("removed"), false));

        switch (sort) {
            default: select = select.orderBy(criteriaBuilder.desc(selectQuery.from(EmployeeEntity.class).get("personnelNumber")));
        }

        TypedQuery<RoleEntity> typedQuery = currentSession
                .createQuery(select);

        if(number > 0) {
            typedQuery = typedQuery
                    .setFirstResult((number - 1) * pageSize)
                    .setMaxResults(pageSize);
        }

        final List<RoleEntity> resultList = typedQuery.getResultList();

        final long totalPage = count == pageSize.longValue() ? 1L : (count / pageSize) + 1;

        return new Page<>(number,
                totalPage,
                pageSize,
                Optional.ofNullable(resultList).orElse(Collections.emptyList()));
    }
}
