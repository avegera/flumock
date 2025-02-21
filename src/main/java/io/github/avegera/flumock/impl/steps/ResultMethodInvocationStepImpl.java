package io.github.avegera.flumock.impl.steps;

import io.github.avegera.flumock.api.steps.ResultMethodInvocationStep;
import io.github.avegera.flumock.impl.model.ExecutionContext;
import io.github.avegera.flumock.impl.model.MethodInvocation;

import java.util.function.Function;

public class ResultMethodInvocationStepImpl<T, M, R> implements ResultMethodInvocationStep<T, R> {

    private final ExecutionContext<T> context;

    private final M mock;

    private final Function<M, R> function;

    public ResultMethodInvocationStepImpl(ExecutionContext<T> context, M mock, Function<M, R> function) {
        this.context = context;
        this.mock = mock;
        this.function = function;
    }

    public InvocationNextStepImpl<T> thatReturn(R value) {
        context.getInvocations().add(new MethodInvocation<>(mock, function, value));
        return new InvocationNextStepImpl<>(context);
    }

    public InvocationNextStepImpl<T> thatReturnNull() {
        context.getInvocations().add(new MethodInvocation<>(mock, function, null));
        return new InvocationNextStepImpl<>(context);
    }
}