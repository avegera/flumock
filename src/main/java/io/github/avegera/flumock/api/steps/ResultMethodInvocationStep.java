package io.github.avegera.flumock.api.steps;

public interface ResultMethodInvocationStep<T, R> {

    InvocationNextStep<T> thatReturn(R value);

    InvocationNextStep<T> thatReturnNull();
}