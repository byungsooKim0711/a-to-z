package org.kimbs.design.behavioral.templateMethod.push;

import org.kimbs.design.behavioral.templateMethod.sender.MessageSender;
import org.kimbs.design.behavioral.templateMethod.AbstractMessageService;

public class PushMessageService extends AbstractMessageService<PushMessage> {

    public PushMessageService(MessageSender sender) {
        super(sender);
    }

    @Override
    protected void checkMessageUid(PushMessage message) {
        System.out.printf("push duplicate check. uid: %s\n", message.getMessageUid());
    }

    @Override
    protected void log(PushMessage message) {
        System.out.printf("push message log. type: %s, uid: %s, toekn: %s, content: %s\n",
                message.getMessageType(), message.getMessageUid(), message.getToken(), message.getContent());
    }

    @Override
    protected boolean isAfterProcess() {
        return false;
    }

    @Override
    protected void afterProcess(PushMessage message) {
        System.out.println("push message service. do nothing after processing...");
    }
}
