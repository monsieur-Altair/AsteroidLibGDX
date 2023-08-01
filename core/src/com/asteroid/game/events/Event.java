package com.asteroid.game.events;

import com.badlogic.gdx.utils.Disposable;

import java.util.ArrayList;

public class Event implements Disposable {
    private final ArrayList<EventListener> eventDelegateArray;

    public Event(){
        eventDelegateArray = new ArrayList<>();
    }

    public void subscribe(EventListener methodReference) {
        eventDelegateArray.add(methodReference);
    }

    public void unSubscribe(EventListener methodReference) {
        eventDelegateArray.remove(methodReference);
    }

    public void invoke() {
        if (eventDelegateArray.size() > 0)
            eventDelegateArray.forEach(EventListener::invoke);
    }

    public void dispose() {
        if (eventDelegateArray.size() > 0)
            eventDelegateArray.clear();
    }
}
