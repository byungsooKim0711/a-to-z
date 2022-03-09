package org.kimbs.design.behavioral.templateMethod.push;

import org.kimbs.design.behavioral.templateMethod.Message;

public class PushMessage extends Message {

    private final String messageType;
    private final String uuid;
    private final String token;

    @Override
    public String toString() {
        return String.format("app push message. type: %s, uuid: %s, uid: %s, content: %s",
                this.messageType, this.uuid, getMessageUid(), getContent());
    }

    private PushMessage(PushMessageBuilder builder) {
        super(builder);
        this.messageType = builder.messageType;
        this.uuid = builder.uuid;
        this.token = builder.token;
    }

    public String getMessageType() {
        return messageType;
    }

    public String getUuid() {
        return uuid;
    }

    public String getToken() {
        return token;
    }

    public static class PushMessageBuilder extends AbstractMessageBuilder<PushMessage> {

        private String messageType;
        private String uuid;
        private String token;

        public PushMessageBuilder(String messageUid, String content) {
            super(messageUid, content);
        }

        public PushMessageBuilder messageType(String messageType) {
            this.messageType = messageType;
            return this;
        }

        public PushMessageBuilder uuidAndToken(String uuid, String token) {
            this.uuid = uuid;
            this.token = token;
            return this;
        }

        public PushMessage build() {
            return new PushMessage(this);
        }
    }
}
