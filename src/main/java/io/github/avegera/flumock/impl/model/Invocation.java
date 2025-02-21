package io.github.avegera.flumock.impl.model;

import org.mockito.InOrder;

public interface Invocation {

    void setup();

    void verify();

    void verify(InOrder inOrder);
}