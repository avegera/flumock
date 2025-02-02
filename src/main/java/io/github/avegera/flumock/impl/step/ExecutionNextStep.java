package io.github.avegera.flumock.impl.step;

import io.github.avegera.flumock.impl.model.ExecutionContext;

import static io.github.avegera.flumock.impl.ResultVerifier.verifyInvocation;

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