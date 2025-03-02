package io.github.avegera.flumock.impl.steps;

import io.github.avegera.flumock.api.steps.ResultStep;
import io.github.avegera.flumock.impl.model.ExecutionContext;

import static io.github.avegera.flumock.impl.ResultVerifier.verifyInvocation;

public class ResultStepImpl<T> implements ResultStep<T> {

    protected final ExecutionContext<T> context;

    public ResultStepImpl(ExecutionContext<T> context) {
        this.context = context;
    }

    @Override
    public T thenReturnResult() {
        return verifyInvocation(context);
    }
}