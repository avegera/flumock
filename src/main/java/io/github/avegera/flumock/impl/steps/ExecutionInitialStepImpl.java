package io.github.avegera.flumock.impl.steps;

import io.github.avegera.flumock.api.steps.ExecutionInitialStep;
import io.github.avegera.flumock.api.steps.InvocationStep;
import io.github.avegera.flumock.impl.model.ExecutionContext;

public class ExecutionInitialStepImpl<T> implements ExecutionInitialStep<T> {

    private final ExecutionContext<T> context;

    public ExecutionInitialStepImpl(ExecutionContext<T> context) {
        this.context = context;
    }

    public InvocationStep<T> shouldInOrder() {
        context.setVerifyInOrder(true);
        return new InvocationStepImpl<>(context);
    }

    @Override
    public InvocationStep<T> shouldInAnyOrder() {
        context.setVerifyInOrder(false);
        return new InvocationStepImpl<>(context);
    }
}