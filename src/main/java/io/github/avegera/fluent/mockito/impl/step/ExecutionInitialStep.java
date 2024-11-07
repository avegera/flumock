package io.github.avegera.fluent.mockito.impl.step;

import io.github.avegera.fluent.mockito.impl.model.ExecutionContext;

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