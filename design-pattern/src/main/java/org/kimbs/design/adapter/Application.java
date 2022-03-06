package org.kimbs.design.adapter;

public class Application {

    // Default Message
    // Push Message, Kakao Message, Email Message, Mt Message...
    public static void main(String[] args) {
        /**  default message */
        DefaultMessageSender defaultMessageSender = new DefaultMessageSender();
        MessageClient defaultClient = new MessageClient(defaultMessageSender);
        defaultClient.sendMessage();


        /**  push message */
        PushMessageSender pushMessageSender = new PushMessageSender();
        PushMessageAdapter pushMessageAdapter = new PushMessageAdapter(pushMessageSender);
        MessageClient pushClient = new MessageClient(pushMessageAdapter);
        pushClient.sendMessage();

        /**  kakao biz message */
        KakaoBizMessageSender kakaoBizMessageSender = new KakaoBizMessageSender();
        KakaoBizMessageAdapter kakaoBizMessageAdapter = new KakaoBizMessageAdapter(kakaoBizMessageSender);
        MessageClient kakaoClient = new MessageClient(kakaoBizMessageAdapter);
        kakaoClient.sendMessage();
    }
}
