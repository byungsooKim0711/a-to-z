package org.kimbs.design.behavioral.templateMethod;

import java.util.Objects;

public abstract class Message {

    private final String messageUid;
    private final String content;

    protected Message(AbstractMessageBuilder<?> builder) {
        Objects.requireNonNull(builder);
        this.messageUid = builder.messageUid;
        this.content = builder.content;
    }

    public String getMessageUid() {
        return messageUid;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return String.format("Abstract message. uid: %s, content: %s", messageUid, content);
    }

    protected static abstract class AbstractMessageBuilder<M extends Message> {
        private final String messageUid;
        private final String content;

        protected AbstractMessageBuilder(String messageUid, String content) {
            Objects.requireNonNull(messageUid, "cat not be null messageUid.");
            Objects.requireNonNull(content, "can not be null content.");

            this.messageUid = messageUid;
            this.content = content;
        }

        public abstract M build();
    }
}
