package org.kimbs.design.behavioral.templateMethod.sender;

import org.kimbs.design.behavioral.templateMethod.Message;

public class KafkaMessageSender implements MessageSender {

    @Override
    public void send(Message message) {
        System.out.printf("kafka send. uid: %s, content: %s\n", message.getMessageUid(), message.getContent());
    }
}
