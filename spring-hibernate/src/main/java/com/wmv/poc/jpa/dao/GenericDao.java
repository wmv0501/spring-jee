package com.wmv.poc.jpa.dao;

import java.util.List;

/**
 * Created by wvergara on 5/11/15.
 */
public interface GenericDao<E, K> {
    public void add(E entity);

    public void saveOrUpdate(E entity);

    public void update(E entity);

    public void remove(E entity);

    public E find(K key);

    public List<E> getAll();
}