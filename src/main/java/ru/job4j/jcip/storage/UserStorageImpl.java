package ru.job4j.jcip.storage;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ThreadSafe
public class UserStorageImpl implements UserStorage {

    @GuardedBy("this")
    private final Map<Integer, User> storage = new HashMap<>();

    @Override
    public synchronized boolean add(User user) {
        boolean result = true;
        if (storage.containsKey(user.getId())) {
            result = false;
        } else {
            storage.put(user.getId(), user);
        }
        return result;
    }

    @Override
    public synchronized boolean update(User user) {
        boolean result = true;
        if (!storage.containsKey(user.getId())) {
            result = false;
        } else {
            storage.replace(user.getId(), user);
        }
        return result;
    }

    @Override
    public synchronized boolean delete(User user) {
        boolean result = true;
        if (!storage.containsKey(user.getId())) {
            result = false;
        } else {
            storage.remove(user.getId());
        }
        return result;
    }

    @Override
    public synchronized boolean transfer(int fromId, int toId, int amount) {
        boolean result = false;
        User src = storage.get(fromId);
        User dest = storage.get(toId);
        if (src != null && dest != null && src.getAmount() >= amount) {
            src.setAmount(src.getAmount() - amount);
            dest.setAmount(dest.getAmount() + amount);
            result = true;
        }
        return result;
    }

    @Override
    public synchronized List<User> findAllUsers() {
        return new ArrayList<>(storage.values());
    }

}
