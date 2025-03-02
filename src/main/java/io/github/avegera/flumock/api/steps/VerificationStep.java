package io.github.avegera.flumock.api.steps;

public interface VerificationStep<T> {

    ResultStep<T> allowMoreInvocations();

    ResultStep<T> allowMoreInvocationsExcept(Object... mocks);

    ResultStep<T> allowMoreInvocationsOnlyFor(Object... mocks);

    ResultStep<T> noMoreInvocations();

    ResultStep<T> noMoreInvocationsIncluding(Object... mocks);
}