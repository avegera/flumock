package io.github.avegera.flumock.api.steps;

public interface InvocationNextStep<T> extends InvocationStep<T> {

    T thenReturnResult();
}