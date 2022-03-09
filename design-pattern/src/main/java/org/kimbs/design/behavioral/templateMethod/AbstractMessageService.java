package org.kimbs.design.behavioral.templateMethod;

import org.kimbs.design.behavioral.templateMethod.sender.MessageSender;

import java.util.Objects;

public abstract class AbstractMessageService<M extends Message> {

    private final MessageSender sender;

    public AbstractMessageService(MessageSender sender) {
        Objects.requireNonNull(sender, "message sender can not be null...");
        this.sender = sender;
    }

    public void sendMessage(M message) {
        checkMessageUid(message);
        log(message);
        send(message);

        if (isAfterProcess()) {
            afterProcess(message);
        }
    }

    protected abstract void checkMessageUid(M message);
    protected abstract void log(M message);
    private void send(M message) {
        sender.send(message);
    }
    protected abstract boolean isAfterProcess();
    protected abstract void afterProcess(M message);
}
