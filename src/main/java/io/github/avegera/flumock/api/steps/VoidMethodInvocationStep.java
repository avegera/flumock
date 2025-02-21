package io.github.avegera.flumock.api.steps;

public interface VoidMethodInvocationStep<T> {

    <M2> MethodInvocationStep<T, M2> invoke(M2 nextMock);

    NoInvocationStep<T> thenVerifyThatNoInvocations(Object... mocks);
}