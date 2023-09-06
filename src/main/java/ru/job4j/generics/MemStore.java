package ru.job4j.generics;

import java.util.HashMap;
import java.util.Map;

public final class MemStore<T extends Base> implements Store<T> {

    private final Map<String, T> storage = new HashMap<>();

    @Override
    public void add(T model) {
        storage.putIfAbsent(model.getId(), model);
    }

    @Override
    public boolean replace(String id, T model) {
        boolean rslt = false;
        if (findById(id) != null) {
            storage.put(findById(id).getId(), model);
            rslt = true;
        }
        return rslt;
    }

    @Override
    public void delete(String id) {
        storage.remove(id);

    }

    @Override
    public T findById(String id) {
        return storage.getOrDefault(id, null);
    }
}
