package ru.job4j.cas.cache;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Cache {

    private final Map<Integer, Base> memory = new ConcurrentHashMap<>();

    public boolean add(Base model) {
        return memory.putIfAbsent(model.getId(), model) == null;
    }

    public boolean update(Base model) {
        return memory.computeIfPresent(
                model.getId(), (key, value) -> {
                    var cachedVersion = value.getVersion();
                    if (value.getVersion() != model.getVersion()) {
                        throw new OptimisticException("Versions are not equal");
                    }
                    var updatedBase = new Base(model.getId(), cachedVersion + 1);
                    updatedBase.setName(model.getName());
                    return updatedBase;
                }) != null;
    }

    public void delete(Base model) {
        memory.remove(model.getId());
    }

    public List<Base> findAllBase() {
        return new ArrayList<>(memory.values());
    }

    public boolean isEmpty() {
        return memory.isEmpty();
    }

}
