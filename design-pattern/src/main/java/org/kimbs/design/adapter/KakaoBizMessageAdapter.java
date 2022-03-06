package org.kimbs.design.adapter;

public class KakaoBizMessageAdapter implements MessageSender {

    private final KakaoBizMessageSender kakaoMessageSender;

    public KakaoBizMessageAdapter(KakaoBizMessageSender kakaoMessageSender) {
        this.kakaoMessageSender = kakaoMessageSender;
    }

    public void sendMessage() {
        kakaoMessageSender.sendMessage();
    }
}
