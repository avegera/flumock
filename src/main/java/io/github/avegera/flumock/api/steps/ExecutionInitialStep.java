package io.github.avegera.flumock.api.steps;

public interface ExecutionInitialStep<T> {

    InvocationStep<T> shouldInOrder();

    InvocationStep<T> shouldInAnyOrder();
}