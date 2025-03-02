package io.github.avegera.flumock.impl.steps;

import io.github.avegera.flumock.api.steps.InvocationVerificationStep;
import io.github.avegera.flumock.api.steps.ResultStep;
import io.github.avegera.flumock.impl.model.ExecutionContext;
import io.github.avegera.flumock.impl.verifiers.*;

import java.util.Set;

import static java.util.Arrays.asList;
import static java.util.Arrays.stream;
import static java.util.stream.Stream.concat;
import static org.mockito.Mockito.verifyNoMoreInteractions;

public class InvocationVerificationStepImpl<T> implements InvocationVerificationStep<T> {

    private final ExecutionContext<T> context;

    public InvocationVerificationStepImpl(ExecutionContext<T> context) {
        this.context = context;
    }

    public <M> MethodInvocationStepImpl<T, M> invoke(M mock) {
        context.getMocks().add(mock);
        return new MethodInvocationStepImpl<>(context, mock);
    }

    @Override
    public ResultStep<T> allowMoreInvocations() {
        setVerifier(() -> {});
        return new ResultStepImpl<>(context);
    }

    @Override
    public ResultStep<T> allowMoreInvocationsExcept(Object... excludedMocks) {
        setVerifier(() -> verifyNoMoreInteractions(excludedMocks));
        return new ResultStepImpl<>(context);
    }

    @Override
    public ResultStep<T> allowMoreInvocationsOnlyFor(Object... allowedMocks) {
        setVerifier(() -> {
            Object[] mocks = context.getMocks().stream()
                    .filter(mock -> !asList(allowedMocks).contains(mock))
                    .toArray();

            if (mocks.length > 0) {
                verifyNoMoreInteractions(mocks);
            }
        });

        return new ResultStepImpl<>(context);
    }

    @Override
    public ResultStep<T> noMoreInvocations() {
        setVerifier(() -> verifyNoMoreInteractions(context.getMocks().toArray()));
        return new ResultStepImpl<>(context);
    }

    @Override
    public ResultStep<T> noMoreInvocationsIncluding(Object... includingMocks) {
        setVerifier(() -> verifyNoMoreInteractions(merge(context.getMocks(), includingMocks)));
        return new ResultStepImpl<>(context);
    }

    private Object[] merge(Set<Object> contextMocks, Object[] includingMocks) {
        return concat(contextMocks.stream(), stream(includingMocks)).toArray();
    }

    private void setVerifier(Verifier verifier) {
        context.setVerifier(verifier);
    }
}