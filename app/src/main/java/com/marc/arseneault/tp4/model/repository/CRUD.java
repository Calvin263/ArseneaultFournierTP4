package com.marc.arseneault.tp4.model.repository;

import java.util.List;

/**
 * Created by 1333297 on 2015-04-29.
 */
public interface CRUD<T> {

    long save(T o);

    void saveMany(Iterable<T> list);

    void saveMany(T... list);

    T getById(Long p);

    List<T> getAll();

    void deleteOne(Long o);

    void deleteOne(T o);

    void deleteAll();

}
