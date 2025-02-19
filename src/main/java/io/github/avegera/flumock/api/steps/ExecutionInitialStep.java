package io.github.avegera.flumock.api.steps;

public interface ExecutionInitialStep<T> {

    <M> InvocationStep<T, M> shouldInvoke(M mock);
}