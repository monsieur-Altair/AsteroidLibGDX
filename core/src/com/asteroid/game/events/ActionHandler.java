package com.asteroid.game.events;

import com.badlogic.gdx.utils.Disposable;

import java.util.ArrayList;

public class ActionHandler<TEventArgs extends EventArgs> implements Disposable {
    private final ArrayList<Action<TEventArgs>> eventDelegateArray = new ArrayList<>();

    public void subscribe(Action<TEventArgs> methodReference) {
        eventDelegateArray.add(methodReference);
    }

    public void unSubscribe(Action<TEventArgs> methodReference) {
        eventDelegateArray.remove(methodReference);
    }

    public void invoke(TEventArgs eventArgs) {
        if (eventDelegateArray.size() > 0)
            eventDelegateArray.forEach(p -> p.invoke(eventArgs));
    }

    public void dispose() {
        if (eventDelegateArray.size() > 0)
            eventDelegateArray.clear();
    }
}

