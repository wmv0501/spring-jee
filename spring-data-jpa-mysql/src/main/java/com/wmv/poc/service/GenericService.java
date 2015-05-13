package com.wmv.poc.service;

import java.util.List;

/**
 * Created by wvergara on 5/12/15.
 */
public interface GenericService<E, K> {
    public E saveOrUpdate(E entity);

    public List<E> getAll();

    public E get(K id);


    public E update(E entity);

    public void remove(E entity);

}
