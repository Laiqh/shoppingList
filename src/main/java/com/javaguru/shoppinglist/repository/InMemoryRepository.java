package com.javaguru.shoppinglist.repository;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class InMemoryRepository<T extends Identifiable> implements Repository<T> {
    private Long id = 0L;
    private Map<Long, T> items = new HashMap<>();

    public Long insert(T item) {
        item.setId(id);
        items.put(id, item);
        id++;
        return item.getId();
    }

    public T get(Long id) {
        return items.get(id);
    }

    public void remove(Long id) {
        items.remove(id);
    }

    public List<T> getAll() {
        return new ArrayList<>(items.values());
    }
}
