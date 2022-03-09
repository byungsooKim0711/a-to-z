package org.kimbs.design.behavioral.templateMethod;

import org.kimbs.design.behavioral.templateMethod.kakao.KakaoBizMessage;
import org.kimbs.design.behavioral.templateMethod.kakao.KakaoBizMessageService;
import org.kimbs.design.behavioral.templateMethod.push.PushMessage;
import org.kimbs.design.behavioral.templateMethod.push.PushMessageService;
import org.kimbs.design.behavioral.templateMethod.sender.ApnsMessageSender;
import org.kimbs.design.behavioral.templateMethod.sender.KafkaMessageSender;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicInteger;

public class Application {
    private final static AtomicInteger ai = new AtomicInteger();

    public static void main(String[] args) {

        String kakaoMessageUid = "AT_112_" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("MMddHHmmssSSS")) + (ai.getAndIncrement() % 1000);
        KakaoBizMessage kakaoBizMessage = new KakaoBizMessage.KakaoBizMessageBuilder(kakaoMessageUid, "카카오 알림톡입니다.")
                .messageType("AT")
                .phoneNumber("821049492891")
                .build();

        KakaoBizMessageService kakaoService = new KakaoBizMessageService(new KafkaMessageSender());
        kakaoService.sendMessage(kakaoBizMessage);

        System.out.println("-----------------------");

        String pushMessageUid = "PU_111_" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("MMddHHmmssSSS")) + (ai.getAndIncrement() % 1000);
        PushMessage pushMessage = new PushMessage.PushMessageBuilder(pushMessageUid, "앱푸시 메시지입니다.")
                .messageType("APNS")
                .uuidAndToken("uuid", "token")
                .build();

        PushMessageService pushService = new PushMessageService(new ApnsMessageSender());
        pushService.sendMessage(pushMessage);
    }
}
