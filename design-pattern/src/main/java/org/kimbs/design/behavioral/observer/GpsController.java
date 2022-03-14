package org.kimbs.design.behavioral.observer;

public class GpsController extends Subject {

    private Position position;

    public void changedPosition(Position position) {
        this.position = position;
        notifyObservers();
    }

    public Position getPosition() {
        return this.position;
    }
}
