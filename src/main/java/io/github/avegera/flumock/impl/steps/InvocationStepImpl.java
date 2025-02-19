package io.github.avegera.flumock.impl.steps;

import io.github.avegera.flumock.api.steps.InvocationStep;
import io.github.avegera.flumock.impl.model.ExecutionContext;

import java.util.function.Consumer;
import java.util.function.Function;

public class InvocationStepImpl<T, M> implements InvocationStep<T, M> {

    private final ExecutionContext<T> context;

    private final M mock;

    public InvocationStepImpl(ExecutionContext<T> context, M mock) {
        this.context = context;
        this.mock = mock;
    }

    public <R> MethodInvocationStepImpl<T, M, R> method(Function<M, R> function) {
        return new MethodInvocationStepImpl<>(context, mock, function);
    }

    public VoidMethodInvocationStepImpl<T, M> voidMethod(Consumer<M> consumer) {
        return new VoidMethodInvocationStepImpl<>(context, mock, consumer);
    }
}