package io.github.avegera.fluent.mockito.impl.model;

import org.mockito.InOrder;

import java.util.function.Function;

import static org.mockito.Mockito.when;

public class MethodInvocation<M, R> implements Invocation {

    private final M mock;

    private final Function<M, R> invocation;

    private final R result;

    public MethodInvocation(M mock, Function<M, R> invocation, R result) {
        this.mock = mock;
        this.invocation = invocation;
        this.result = result;
    }

    public void setup() {
        when(invocation.apply(mock)).thenReturn(result);
    }

    public void verify(InOrder inOrder) {
        invocation.apply(inOrder.verify(mock));
    }
}