package io.github.avegera.flumock.api.steps;

public interface MethodInvocationResultStep<T, R> {

    InvocationVerificationStep<T> returns(R value);

    InvocationVerificationStep<T> returnsNull();
}