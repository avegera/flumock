package io.github.avegera.flumock.impl.model;

import org.mockito.InOrder;

public interface Invocation {

    void setup();

    //TODO: avoid Mockito mentions in interfaces (replace with generic context in future)
    void verify(InOrder inOrder);
}