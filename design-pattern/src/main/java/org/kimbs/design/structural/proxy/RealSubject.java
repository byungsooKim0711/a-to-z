package org.kimbs.design.structural.proxy;

public class RealSubject implements Subject {

    @Override
    public CacheObject doSomething(String id) {
        return new CacheObject(id);
    }
}
