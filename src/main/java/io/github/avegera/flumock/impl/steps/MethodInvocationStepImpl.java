package io.github.avegera.flumock.impl.steps;

import io.github.avegera.flumock.api.steps.MethodInvocationStep;
import io.github.avegera.flumock.impl.model.ExecutionContext;

import java.util.function.Consumer;
import java.util.function.Function;

public class MethodInvocationStepImpl<T, M> implements MethodInvocationStep<T, M> {

    private final ExecutionContext<T> context;

    private final M mock;

    public MethodInvocationStepImpl(ExecutionContext<T> context, M mock) {
        this.context = context;
        this.mock = mock;
    }

    public <R> ResultMethodInvocationStepImpl<T, M, R> method(Function<M, R> function) {
        return new ResultMethodInvocationStepImpl<>(context, mock, function);
    }

    public VoidMethodInvocationStepImpl<T, M> voidMethod(Consumer<M> consumer) {
        return new VoidMethodInvocationStepImpl<>(context, mock, consumer);
    }
}