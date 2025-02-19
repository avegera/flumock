package io.github.avegera.flumock.impl.steps;

import io.github.avegera.flumock.api.steps.MethodInvocationStep;
import io.github.avegera.flumock.impl.model.ExecutionContext;
import io.github.avegera.flumock.impl.model.MethodInvocation;

import java.util.function.Function;

public class MethodInvocationStepImpl<T, M, R> implements MethodInvocationStep<T, R> {

    private final ExecutionContext<T> context;

    private final M mock;

    private final Function<M, R> function;

    public MethodInvocationStepImpl(ExecutionContext<T> context, M mock, Function<M, R> function) {
        this.context = context;
        this.mock = mock;
        this.function = function;
    }

    public ExecutionNextStepImpl<T> thatReturn(R value) {
        context.getInvocations().add(new MethodInvocation<>(mock, function, value));
        return new ExecutionNextStepImpl<>(context);
    }

    public ExecutionNextStepImpl<T> thatReturnNull() {
        context.getInvocations().add(new MethodInvocation<>(mock, function, null));
        return new ExecutionNextStepImpl<>(context);
    }
}