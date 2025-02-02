package io.github.avegera.flumock.impl.step;

import io.github.avegera.flumock.impl.model.ExecutionContext;

import static io.github.avegera.flumock.impl.ResultVerifier.verifyInvocation;

public class NoInvocationStep<T> {

    private final ExecutionContext<T> context;

    public NoInvocationStep(ExecutionContext<T> context) {
        this.context = context;
    }

    public T thenReturnResult() {
        return verifyInvocation(context);
    }
}