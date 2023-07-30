package com.asteroid.game;

@FunctionalInterface
public interface Action<TEventArgs extends EventArgs> {
    public void invoke(TEventArgs eventArgs);
}

