package io.github.avegera.flumock.api.steps;

public interface InvocationStep<T> {

    <M> MethodInvocationStep<T, M> invoke(M mock);
}