package io.github.avegera.flumock.impl.steps;

import io.github.avegera.flumock.api.steps.ExecutionInitialStep;
import io.github.avegera.flumock.impl.model.ExecutionContext;

public class ExecutionInitialStepImpl<T> implements ExecutionInitialStep<T> {

    private final ExecutionContext<T> context;

    public ExecutionInitialStepImpl(ExecutionContext<T> context) {
        this.context = context;
    }

    public <M> InvocationStepImpl<T, M> shouldInvoke(M mock) {
        context.getMocks().add(mock);
        return new InvocationStepImpl<>(context, mock);
    }
}