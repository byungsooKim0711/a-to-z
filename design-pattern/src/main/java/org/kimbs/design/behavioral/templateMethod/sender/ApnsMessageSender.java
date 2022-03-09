package org.kimbs.design.behavioral.templateMethod.sender;

import org.kimbs.design.behavioral.templateMethod.Message;

public class ApnsMessageSender implements MessageSender {

    @Override
    public void send(Message message) {
        System.out.printf("apns send. uid: %s, content: %s\n", message.getMessageUid(), message.getContent());
    }
}
