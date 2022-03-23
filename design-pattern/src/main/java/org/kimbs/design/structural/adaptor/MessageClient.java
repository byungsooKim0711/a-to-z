package org.kimbs.design.structural.adaptor;

public class MessageClient {

    private MessageSender defaultMessageSender;

    public MessageClient(MessageSender defaultMessageSender) {
        this.defaultMessageSender = defaultMessageSender;
    }

    public void sendMessage() {
        defaultMessageSender.sendMessage();
    }
}
