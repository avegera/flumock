package io.github.avegera.flumock.api.steps;

public interface VoidMethodInvocationStep<T> {

    <M2> InvocationStep<T, M2> thenInvoke(M2 nextMock);

    NoInvocationStep<T> thenVerifyThatNoInvocations(Object... mocks);
}