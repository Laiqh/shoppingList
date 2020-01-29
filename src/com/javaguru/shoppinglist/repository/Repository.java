package com.javaguru.shoppinglist.repository;

public interface Repository<T> {
    Long insert(T item);

    T findById(Long id);

    void removeById(Long id);
}
