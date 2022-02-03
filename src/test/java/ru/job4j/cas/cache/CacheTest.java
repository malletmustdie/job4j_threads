package ru.job4j.cas.cache;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class CacheTest {

    @Test
    public void whenAddTwoBaseInCache() {
        var cache = new Cache();
        var base1 = new Base(1, 1);
        var base2 = new Base(2, 1);
        cache.add(base1);
        cache.add(base2);
        var baseList = cache.findAllBase();
        Assert.assertEquals(2, baseList.size());
        Assert.assertThat(List.of(base1, base2), Matchers.is(baseList));
    }

    @Test
    public void whenUpdateBaseThatUpdateBaseAndVersionInCache() {
        var cache = new Cache();
        var base = new Base(1, 1);
        cache.add(base);
        var expectedVersionBeforeUpdate = base.getVersion();
        var actualVersionBeforeUpdate = cache.findAllBase().get(0).getVersion();
        Assert.assertEquals(base, cache.findAllBase().get(0));
        Assert.assertEquals(expectedVersionBeforeUpdate, actualVersionBeforeUpdate);
        base.setName("Base");
        Assert.assertTrue(cache.update(base));
        var expectedVersionAfterUpdate = base.getVersion() + 1;
        var actualVersionAfterUpdate = cache.findAllBase().get(0).getVersion();
        Assert.assertEquals(expectedVersionAfterUpdate, actualVersionAfterUpdate);
    }

    @Test(expected = OptimisticException.class)
    public void whenUpdateBaseMultipleTimesThanThrowException() {
        var cache = new Cache();
        var base = new Base(1, 1);
        cache.add(base);
        base.setName("Base");
        cache.update(base);
        base.setName("Base-Base");
        cache.update(base);
    }

    @Test
    public void delete() {
        var cache = new Cache();
        var base = new Base(1, 1);
        cache.add(base);
        cache.delete(base);
        Assert.assertTrue(cache.isEmpty());
    }

}