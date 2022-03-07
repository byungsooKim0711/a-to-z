package org.kimbs.design.creational.builder;

import java.time.LocalDateTime;

public class Book {

    private final String isbn;
    private final String title;
    private final String author;
    private final LocalDateTime published;
    private final String description;

    @Override
    public String toString() {
        return "{" +
                "\"isbn\":\"" + isbn + '\"' +
                ", \"title\":\"" + title + '\"' +
                ", \"author\":\"" + author + '\"' +
                ", \"published\":\"" + published + '\"' +
                ", \"description\":\"" + description + '\"' +
                '}';
    }

    private Book(BookBuilder builder) {
        this.isbn = builder.isbn;
        this.title = builder.title;
        this.author = builder.author;
        this.published = builder.published;
        this.description = builder.description;
    }

    public static class BookBuilder {

        private String isbn;
        private String title;
        private String author;
        private LocalDateTime published;
        private String description;


        public BookBuilder isbn(String isbn) {
            this.isbn = isbn;
            return this;
        }

        public BookBuilder title(String title) {
            this.title = title;
            return this;
        }

        public BookBuilder author(String author) {
            this.author = author;
            return this;
        }

        public BookBuilder published(LocalDateTime published) {
            this.published = published;
            return this;
        }

        public BookBuilder description(String description) {
            this.description = description;
            return this;
        }

        public Book build() {
            return new Book(this);
        }
    }
}
