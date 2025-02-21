package io.github.avegera.flumock.impl.steps;

import io.github.avegera.flumock.api.steps.InvocationNextStep;
import io.github.avegera.flumock.impl.model.ExecutionContext;

import static io.github.avegera.flumock.impl.ResultVerifier.verifyInvocation;

public class InvocationNextStepImpl<T> implements InvocationNextStep<T> {

    protected final ExecutionContext<T> context;

    public InvocationNextStepImpl(ExecutionContext<T> context) {
        this.context = context;
    }

    public <M> MethodInvocationStepImpl<T, M> invoke(M mock) {
        context.getMocks().add(mock);
        return new MethodInvocationStepImpl<>(context, mock);
    }

    public T thenReturnResult() {
        return verifyInvocation(context);
    }
}