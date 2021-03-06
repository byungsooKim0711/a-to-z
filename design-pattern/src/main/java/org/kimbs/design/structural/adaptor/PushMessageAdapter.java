package org.kimbs.design.structural.adaptor;

public class PushMessageAdapter implements MessageSender {

    private final PushMessageSender pushMessageSender;

    public PushMessageAdapter(PushMessageSender pushMessageSender) {
        this.pushMessageSender = pushMessageSender;
    }

    public void sendMessage() {
        pushMessageSender.sendMessage();
    }
}
