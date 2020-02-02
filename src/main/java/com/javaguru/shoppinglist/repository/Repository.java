package com.javaguru.shoppinglist.repository;

import java.util.List;

public interface Repository<T> {
    Long insert(T item);

    T get(Long id);

    void remove(Long id);

    List<T> getAll();
}
