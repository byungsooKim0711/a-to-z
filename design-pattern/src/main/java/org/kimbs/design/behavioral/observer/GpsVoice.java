package org.kimbs.design.behavioral.observer;

public class GpsVoice implements Observer {

    private final GpsController gpsController;

    public GpsVoice(GpsController gpsController) {
        this.gpsController = gpsController;
    }

    @Override
    public void update() {
        Position position = gpsController.getPosition();
        System.out.printf("[Voice] current position. latitude: %.15f, longitude: %.15f\n", position.getLatitude(), position.getLongitude());
    }
}
