package io.github.avegera.flumock.impl.steps;

import io.github.avegera.flumock.api.steps.MethodInvocationResultStep;
import io.github.avegera.flumock.impl.model.ExecutionContext;
import io.github.avegera.flumock.impl.model.MethodInvocation;

import java.util.function.Function;

public class MethodInvocationResultStepImpl<T, M, R> implements MethodInvocationResultStep<T, R> {

    private final ExecutionContext<T> context;

    private final M mock;

    private final Function<M, R> function;

    public MethodInvocationResultStepImpl(ExecutionContext<T> context, M mock, Function<M, R> function) {
        this.context = context;
        this.mock = mock;
        this.function = function;
    }

    public InvocationVerificationStepImpl<T> returns(R value) {
        context.getInvocations().add(new MethodInvocation<>(mock, function, value));
        return new InvocationVerificationStepImpl<>(context);
    }

    public InvocationVerificationStepImpl<T> returnsNull() {
        context.getInvocations().add(new MethodInvocation<>(mock, function, null));
        return new InvocationVerificationStepImpl<>(context);
    }
}