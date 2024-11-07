package io.github.avegera.fluent.mockito.impl.step;

import io.github.avegera.fluent.mockito.impl.model.ExecutionContext;

import static io.github.avegera.fluent.mockito.impl.ResultVerifier.verifyInvocation;

public class NoInvocationStep<T> {

    private final ExecutionContext<T> context;

    public NoInvocationStep(ExecutionContext<T> context) {
        this.context = context;
    }

    public T thenReturnResult() {
        return verifyInvocation(context);
    }
}