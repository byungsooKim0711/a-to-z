package org.kimbs.design.structural.proxy;

import java.util.UUID;

public class Application {

    public static void main(String[] args) {
        String id1 = UUID.randomUUID().toString();
        String id2 = UUID.randomUUID().toString();
        String id3 = UUID.randomUUID().toString();

        Subject realSubject = new RealSubject();
        System.out.println(realSubject.doSomething(id1) == realSubject.doSomething(id1));

        Subject proxy = new Proxy(realSubject);
        System.out.println(proxy.doSomething(id1) == proxy.doSomething(id1));
        System.out.println(proxy.doSomething(id1) == proxy.doSomething(id1));
        System.out.println(proxy.doSomething(id1) == proxy.doSomething(id1));

        System.out.println(proxy.doSomething(id3) == proxy.doSomething(id3));
        System.out.println(proxy.doSomething(id3) == proxy.doSomething(id3));
        System.out.println(proxy.doSomething(id3) == proxy.doSomething(id3));
        System.out.println(proxy.doSomething(id3) == proxy.doSomething(id3));

        System.out.println(proxy.doSomething(id2) == proxy.doSomething(id2));

    }
}