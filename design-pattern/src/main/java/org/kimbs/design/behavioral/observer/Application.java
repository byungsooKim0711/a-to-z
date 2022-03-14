package org.kimbs.design.behavioral.observer;

public class Application {

    public static void main(String[] args) {
        GpsController gpsController = new GpsController();

        Observer gpsVoice = new GpsVoice(gpsController);
        Observer gpsDisplay = new GpsDisplay(gpsController);
        gpsController.subscribe(gpsVoice);
        gpsController.subscribe(gpsDisplay);

        gpsController.changedPosition(new Position(37.394888103750716, 127.11115962572424));
        gpsController.changedPosition(new Position(37.39675412360333, 127.11130423083594));
        gpsController.changedPosition(new Position(37.39889644641797, 127.11112777581303));
        gpsController.changedPosition(new Position(37.39983399411538, 127.11118473178654));

        gpsController.unsubscribe(gpsVoice);

        gpsController.changedPosition(new Position(37.40130848679819, 127.108856574399));
    }
}
