package io.github.avegera.fluent.mockito.impl.step;

import io.github.avegera.fluent.mockito.impl.model.ExecutionContext;
import io.github.avegera.fluent.mockito.impl.model.MethodInvocation;

import java.util.function.Function;

public class MethodInvocationStep<T, M, R> {

    private final ExecutionContext<T> context;

    private final M mock;

    private final Function<M, R> function;

    public MethodInvocationStep(ExecutionContext<T> context, M mock, Function<M, R> function) {
        this.context = context;
        this.mock = mock;
        this.function = function;
    }

    public ExecutionNextStep<T> thatReturn(R value) {
        context.getInvocations().add(new MethodInvocation<>(mock, function, value));
        return new ExecutionNextStep<>(context);
    }

    public ExecutionNextStep<T> thatReturnNull() {
        context.getInvocations().add(new MethodInvocation<>(mock, function, null));
        return new ExecutionNextStep<>(context);
    }
}