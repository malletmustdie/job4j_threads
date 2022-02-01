package ru.job4j.jcip.storage;

import org.junit.Assert;
import org.junit.Test;

public class UserStorageImplTest {

    @Test
    public void whenAddTwoUsersToStorageThenReturnTwoUsers() {
        var store = new UserStorageImpl();
        var user0 = new User(1, 100);
        Assert.assertTrue(store.add(user0));
        store.add(new User(2, 200));
        var users = store.findAllUsers();
        Assert.assertEquals(2, users.size());
        Assert.assertEquals(users.get(0), user0);
    }

    @Test
    public void whenAddTwoUsersWithSameIdToStorageThenReturnOneUser() {
        var store = new UserStorageImpl();
        store.add(new User(1, 100));
        store.add(new User(1, 200));
        var users = store.findAllUsers();
        Assert.assertEquals(1, users.size());
    }

    @Test
    public void whenUpdateUser() {
        var store = new UserStorageImpl();
        var user = new User(1, 100);
        store.add(user);
        var actualUserBeforeUpdate = store.findAllUsers().get(0);
        Assert.assertEquals(user, actualUserBeforeUpdate);
        user.setAmount(200);
        Assert.assertTrue(store.update(user));
        var actualUserAfterUpdate = store.findAllUsers().get(0);
        Assert.assertEquals(user, actualUserAfterUpdate);
    }

    @Test
    public void whenDeleteUser() {
        var store = new UserStorageImpl();
        var user = new User(1, 100);
        store.add(user);
        Assert.assertTrue(store.delete(user));
        Assert.assertEquals(0, store.findAllUsers().size());
    }

    @Test
    public void whenTransferMoneyFromUser1ToUser2() {
        var store = new UserStorageImpl();
        var user0 = new User(1, 100);
        var user1 = new User(2, 200);
        store.add(user0);
        store.add(user1);
        Assert.assertTrue(store.transfer(user0.getId(), user1.getId(), 50));
        Assert.assertEquals(50, user0.getAmount());
        Assert.assertEquals(250, user1.getAmount());
    }

}