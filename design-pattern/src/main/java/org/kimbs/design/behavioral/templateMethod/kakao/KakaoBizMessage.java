package org.kimbs.design.behavioral.templateMethod.kakao;

import org.kimbs.design.behavioral.templateMethod.Message;

public class KakaoBizMessage extends Message {

    private final String messageType;

    private final String phoneNumber;

    @Override
    public String toString() {
        return String.format("kakao business message. type: %s, phone-number: %s, uid: %s, content: %s",
                this.messageType, this.phoneNumber, getMessageUid(), getContent());
    }

    public String getMessageType() {
        return messageType;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    private KakaoBizMessage(KakaoBizMessageBuilder builder) {
        super(builder);
        this.messageType = builder.messageType;
        this.phoneNumber = builder.phoneNumber;
    }

    public static class KakaoBizMessageBuilder extends Message.AbstractMessageBuilder<KakaoBizMessage> {
        private String messageType;
        private String phoneNumber;

        public KakaoBizMessageBuilder(String messageUid, String content) {
            super(messageUid, content);
        }

        public KakaoBizMessageBuilder messageType(String messageType) {
            this.messageType = messageType;
            return this;
        }

        public KakaoBizMessageBuilder phoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        @Override
        public KakaoBizMessage build() {
            return new KakaoBizMessage(this);
        }
    }
}
