package com.javaguru.shoppinglist.repository;

import java.util.List;
import java.util.NoSuchElementException;

public interface Repository<T> {
    Long insert(T item);

    T get(Long id) throws NoSuchElementException;

    void remove(Long id);

    List<T> getAll();
}
