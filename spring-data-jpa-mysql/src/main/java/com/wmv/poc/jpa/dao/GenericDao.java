package com.wmv.poc.jpa.dao;

import java.util.List;

/**
 * Created by wvergara on 5/11/15.
 */
public interface GenericDao<E, K> {

    public E saveOrUpdate(E entity);

    public E update(E entity);

    public void remove(E entity);

    public E find(K key);

    public List<E> getAll();
}