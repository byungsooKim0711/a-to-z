package org.kimbs.design.creational.builder;

import java.time.LocalDateTime;
import java.util.UUID;

public class Application {

    public static void main(String[] args) {
        Book book = new Book.BookBuilder()
                .isbn(UUID.randomUUID().toString())
                .author("kbs")
                .title("builder pattern")
                .description("builder pattern test")
                .published(LocalDateTime.now())
                .build();

        System.out.println("created book. " + book);
    }
}
