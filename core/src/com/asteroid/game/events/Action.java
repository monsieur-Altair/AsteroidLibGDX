package com.asteroid.game.events;

@FunctionalInterface
public interface Action<TEventArgs extends EventArgs> {
    public void invoke(TEventArgs eventArgs);
}

