package io.github.avegera.flumock.api.steps;

public interface ExecutionNextStep<T> {

    <M> InvocationStep<T, M> thenInvoke(M mock);

    T thenReturnResult();
}