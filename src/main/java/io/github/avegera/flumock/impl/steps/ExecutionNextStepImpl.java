package io.github.avegera.flumock.impl.steps;

import io.github.avegera.flumock.api.steps.ExecutionNextStep;
import io.github.avegera.flumock.impl.model.ExecutionContext;

import static io.github.avegera.flumock.impl.ResultVerifier.verifyInvocation;

public class ExecutionNextStepImpl<T> implements ExecutionNextStep<T> {

    protected final ExecutionContext<T> context;

    public ExecutionNextStepImpl(ExecutionContext<T> context) {
        this.context = context;
    }

    public <M> InvocationStepImpl<T, M> thenInvoke(M mock) {
        context.getMocks().add(mock);
        return new InvocationStepImpl<>(context, mock);
    }

    public T thenReturnResult() {
        return verifyInvocation(context);
    }
}