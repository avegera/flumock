package io.github.avegera.fluent.mockito.impl.step;

import io.github.avegera.fluent.mockito.impl.model.ExecutionContext;
import io.github.avegera.fluent.mockito.impl.model.VoidMethodInvocation;

import java.util.function.Consumer;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class VoidMethodInvocationStep<T, M> {

    private final ExecutionContext<T> context;

    private final M mock;

    private final Consumer<M> invocation;

    public VoidMethodInvocationStep(ExecutionContext<T> context, M mock, Consumer<M> invocation) {
        this.context = context;
        this.mock = mock;
        this.invocation = invocation;
    }

    public <M2> InvocationStep<T, M2> thenInvoke(M2 nextMock) {
        context.getInvocations().add(new VoidMethodInvocation<>(mock, invocation));
        context.getMocks().add(nextMock);
        return new InvocationStep<>(context, nextMock);
    }

    public NoInvocationStep<T> thenVerifyThatNoInvocations(Object... mocks) {
        context.getInvocations().add(new VoidMethodInvocation<>(mock, invocation));
        context.getMocks().addAll(Stream.of(mocks).collect(toList()));
        return new NoInvocationStep<>(context);
    }
}