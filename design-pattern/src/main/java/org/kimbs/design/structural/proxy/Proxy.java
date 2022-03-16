package org.kimbs.design.structural.proxy;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Proxy implements Subject {

    private static final Map<String, CacheObject> cache = new ConcurrentHashMap<>();

    public final Subject subject;

    public Proxy(Subject subject) {
        this.subject = subject;
    }

    @Override
    public CacheObject doSomething(String id) {
        if (cache.containsKey(id)) {
            return cache.get(id);
        }

        CacheObject object = subject.doSomething(id);
        cache.put(id, object);

        return object;
    }
}
