package org.kimbs.design.structural.adaptor;

public class DefaultMessageSender implements MessageSender {

    public void sendMessage() {
        System.out.println("send default message.");
    }
}
