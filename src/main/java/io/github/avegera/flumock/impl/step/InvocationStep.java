package io.github.avegera.flumock.impl.step;

import io.github.avegera.flumock.impl.model.ExecutionContext;

import java.util.function.Consumer;
import java.util.function.Function;

public class InvocationStep<T, M> {

    private final ExecutionContext<T> context;

    private final M mock;

    public InvocationStep(ExecutionContext<T> context, M mock) {
        this.context = context;
        this.mock = mock;
    }

    public <R> MethodInvocationStep<T, M, R> method(Function<M, R> function) {
        return new MethodInvocationStep<>(context, mock, function);
    }

    public VoidMethodInvocationStep<T, M> voidMethod(Consumer<M> consumer) {
        return new VoidMethodInvocationStep<>(context, mock, consumer);
    }
}