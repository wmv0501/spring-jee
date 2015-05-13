package com.wmv.poc.jpa.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by wvergara on 5/11/15.
 */
@Repository
@Transactional

public abstract class GenericDaoImpl<E, K extends Serializable>
        implements GenericDao<E, K> {

    @Autowired
    private EntityManager em;

    protected Class<E> daoType;

    /**
     * By defining this class as abstract, we prevent Spring from creating
     * instance of this class If not defined as abstract,
     * getClass().getGenericSuperClass() would return Object. There would be
     * exception because Object class does not hava constructor with parameters.
     */
    public GenericDaoImpl() {
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        daoType = (Class) pt.getActualTypeArguments()[0];
    }

    protected EntityManager getEm() {
        return em;
    }

   // @Override
    public E saveOrUpdate(E entity) {
        return getEm().merge(entity);
    }

    @Override
    public E update(E entity) {
        return getEm().merge(entity);
    }

    @Override
    public void remove(E entity) {
        getEm().remove(entity);
    }

    @Override
    public E find(K key) {
        return (E) getEm().find(daoType, key);
    }

    @Override
    public List<E> getAll() {
        CriteriaBuilder criteriaBuilder = getEm().getCriteriaBuilder();
        CriteriaQuery<E> criteriaQuery = criteriaBuilder.createQuery(daoType);
        final Root stateRoot = criteriaQuery.from(daoType);
        TypedQuery<E> query = getEm().createQuery(criteriaQuery.select(stateRoot));
        return (List<E>)query.getResultList();

    }
}
