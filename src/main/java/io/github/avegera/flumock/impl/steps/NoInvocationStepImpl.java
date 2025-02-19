package io.github.avegera.flumock.impl.steps;

import io.github.avegera.flumock.api.steps.NoInvocationStep;
import io.github.avegera.flumock.impl.model.ExecutionContext;

import static io.github.avegera.flumock.impl.ResultVerifier.verifyInvocation;

public class NoInvocationStepImpl<T> implements NoInvocationStep<T> {

    private final ExecutionContext<T> context;

    public NoInvocationStepImpl(ExecutionContext<T> context) {
        this.context = context;
    }

    public T thenReturnResult() {
        return verifyInvocation(context);
    }
}