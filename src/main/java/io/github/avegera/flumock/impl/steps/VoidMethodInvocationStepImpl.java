package io.github.avegera.flumock.impl.steps;

import io.github.avegera.flumock.api.steps.VoidMethodInvocationStep;
import io.github.avegera.flumock.impl.model.ExecutionContext;
import io.github.avegera.flumock.impl.model.VoidMethodInvocation;

import java.util.function.Consumer;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class VoidMethodInvocationStepImpl<T, M> implements VoidMethodInvocationStep<T> {

    private final ExecutionContext<T> context;

    private final M mock;

    private final Consumer<M> invocation;

    public VoidMethodInvocationStepImpl(ExecutionContext<T> context, M mock, Consumer<M> invocation) {
        this.context = context;
        this.mock = mock;
        this.invocation = invocation;
    }

    public <M2> InvocationStepImpl<T, M2> thenInvoke(M2 nextMock) {
        context.getInvocations().add(new VoidMethodInvocation<>(mock, invocation));
        context.getMocks().add(nextMock);
        return new InvocationStepImpl<>(context, nextMock);
    }

    public NoInvocationStepImpl<T> thenVerifyThatNoInvocations(Object... mocks) {
        context.getInvocations().add(new VoidMethodInvocation<>(mock, invocation));
        context.getMocks().addAll(Stream.of(mocks).collect(toList()));
        return new NoInvocationStepImpl<>(context);
    }
}