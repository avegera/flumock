package io.github.avegera.flumock.impl.model;

import org.mockito.InOrder;
import org.mockito.Mockito;

import java.util.function.Consumer;

import static org.mockito.Mockito.doAnswer;

public class VoidMethodInvocation<M> implements Invocation {

    private final M mock;

    private final Consumer<M> invocation;

    public VoidMethodInvocation(M mock, Consumer<M> invocation) {
        this.mock = mock;
        this.invocation = invocation;
    }

    public void setup() {
        invocation.accept(doAnswer(invocationOnMock -> null).when(mock));
    }

    @Override
    public void verify() {
        invocation.accept(Mockito.verify(mock));
    }

    @Override
    public void verify(InOrder inOrder) {
        invocation.accept(inOrder.verify(mock));
    }
}