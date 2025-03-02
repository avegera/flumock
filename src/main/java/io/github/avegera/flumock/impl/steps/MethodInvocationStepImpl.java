package io.github.avegera.flumock.impl.steps;

import io.github.avegera.flumock.api.steps.InvocationVerificationStep;
import io.github.avegera.flumock.api.steps.MethodInvocationStep;
import io.github.avegera.flumock.impl.model.ExecutionContext;
import io.github.avegera.flumock.impl.model.VoidMethodInvocation;

import java.util.function.Consumer;
import java.util.function.Function;

public class MethodInvocationStepImpl<T, M> implements MethodInvocationStep<T, M> {

    private final ExecutionContext<T> context;

    private final M mock;

    public MethodInvocationStepImpl(ExecutionContext<T> context, M mock) {
        this.context = context;
        this.mock = mock;
    }

    public <R> MethodInvocationResultStepImpl<T, M, R> method(Function<M, R> function) {
        return new MethodInvocationResultStepImpl<>(context, mock, function);
    }

    public InvocationVerificationStep<T> voidMethod(Consumer<M> consumer) {
        context.getInvocations().add(new VoidMethodInvocation<>(mock, consumer));
        return new InvocationVerificationStepImpl<>(context);
    }
}