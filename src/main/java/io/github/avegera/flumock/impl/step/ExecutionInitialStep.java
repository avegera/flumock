package io.github.avegera.flumock.impl.step;

import io.github.avegera.flumock.impl.model.ExecutionContext;

public class ExecutionInitialStep<T> {

    private final ExecutionContext<T> context;

    public ExecutionInitialStep(ExecutionContext<T> context) {
        this.context = context;
    }

    public <M> InvocationStep<T, M> shouldInvoke(M mock) {
        context.getMocks().add(mock);
        return new InvocationStep<>(context, mock);
    }
}