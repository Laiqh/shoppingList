package com.javaguru.shoppinglist.repository;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Profile("inmemory")
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
        if (items.containsKey(id)) {
            return items.get(id);
        } else throw new NoSuchElementException("No item found for ID " + id);
    }

    public void remove(Long id) {
        items.remove(id);
    }

    public List<T> getAll() {
        return new ArrayList<>(items.values());
    }
}
