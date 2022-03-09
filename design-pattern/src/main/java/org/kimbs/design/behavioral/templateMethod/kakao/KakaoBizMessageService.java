package org.kimbs.design.behavioral.templateMethod.kakao;

import org.kimbs.design.behavioral.templateMethod.sender.MessageSender;
import org.kimbs.design.behavioral.templateMethod.AbstractMessageService;

public class KakaoBizMessageService extends AbstractMessageService<KakaoBizMessage> {

    public KakaoBizMessageService(MessageSender sender) {
        super(sender);
    }

    @Override
    protected void checkMessageUid(KakaoBizMessage message) {
        // check duplicate uid...
        System.out.printf("kakao duplicate check. uid: %s\n", message.getMessageUid());
    }

    @Override
    protected void log(KakaoBizMessage message) {
        System.out.printf("kakao message log. type: %s, uid: %s, content: %s\n",
                message.getMessageType(), message.getMessageUid(), message.getContent());
    }

    @Override
    protected boolean isAfterProcess() {
        return true;
    }

    @Override
    protected void afterProcess(KakaoBizMessage message) {
        System.out.println("kakao message service. do something after processing...");
    }
}
