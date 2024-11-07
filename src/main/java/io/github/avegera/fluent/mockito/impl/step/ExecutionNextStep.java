package io.github.avegera.fluent.mockito.impl.step;

import io.github.avegera.fluent.mockito.impl.model.ExecutionContext;

import static io.github.avegera.fluent.mockito.impl.ResultVerifier.verifyInvocation;

public class ExecutionNextStep<T> {

    protected final ExecutionContext<T> context;

    public ExecutionNextStep(ExecutionContext<T> context) {
        this.context = context;
    }

    public <M> InvocationStep<T, M> thenInvoke(M mock) {
        context.getMocks().add(mock);
        return new InvocationStep<>(context, mock);
    }

    public T thenReturnResult() {
        return verifyInvocation(context);
    }
}