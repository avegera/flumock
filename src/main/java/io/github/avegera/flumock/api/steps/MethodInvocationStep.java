package io.github.avegera.flumock.api.steps;

public interface MethodInvocationStep<T, R> {

    ExecutionNextStep<T> thatReturn(R value);

    ExecutionNextStep<T> thatReturnNull();
}