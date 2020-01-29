package com.javaguru.shoppinglist.repository;

import java.util.HashMap;
import java.util.Map;

public class InMemoryRepository<T extends Identifiable> implements Repository<T> {
    private Long id = 0L;
    private Map<Long, T> items = new HashMap<>();

    public Long insert(T item) {
        item.setId(id);
        items.put(id, item);
        id++;
        return item.getId();
    }

    public T findById(Long id) {
        return items.get(id);
    }

    public void removeById(Long id) {
        items.remove(id);
    }
}
