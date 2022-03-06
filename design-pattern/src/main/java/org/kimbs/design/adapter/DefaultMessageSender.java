package org.kimbs.design.adapter;

public class DefaultMessageSender implements MessageSender {

    public void sendMessage() {
        System.out.println("send default message.");
    }
}
