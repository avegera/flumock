package io.github.avegera.flumock.impl.steps;

import io.github.avegera.flumock.api.steps.InvocationStep;
import io.github.avegera.flumock.impl.model.ExecutionContext;

public class InvocationStepImpl<T> implements InvocationStep<T> {

    private final ExecutionContext<T> context;

    public InvocationStepImpl(ExecutionContext<T> context) {
        this.context = context;
    }

    public <M> MethodInvocationStepImpl<T, M> invoke(M mock) {
        context.getMocks().add(mock);
        return new MethodInvocationStepImpl<>(context, mock);
    }
}