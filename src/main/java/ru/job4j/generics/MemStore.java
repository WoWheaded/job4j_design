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
        T byId = findById(id);
        if (byId != null) {
            storage.put(byId.getId(), model);
            return true;
        }
        return false;
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
