package io.github.avegera.flumock.impl;

import io.github.avegera.flumock.impl.model.ExecutionContext;
import io.github.avegera.flumock.impl.model.Invocation;
import io.github.avegera.flumock.impl.verifiers.Verifier;
import org.mockito.InOrder;

import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.inOrder;

public class ResultVerifier {

    private ResultVerifier() {
    }

    public static <T> T verifyInvocation(ExecutionContext<T> context) {
        setupInvocations(context);
        T result = context.getMethod().get();
        if (context.getVerifyInOrder()) {
            verifyInOrderInvocation(context.getInvocations(), context.getVerifier(), context.getMocks());
        } else {
            verifyAnyOrderInvocation(context.getInvocations(), context.getVerifier());
        }
        return result;
    }

    private static <T> void setupInvocations(ExecutionContext<T> context) {
        for (Invocation invocation : context.getInvocations()) {
            invocation.setup();
        }
    }

    private static void verifyAnyOrderInvocation(List<Invocation> invocations, Verifier verifier) {
        for (Invocation invocation : invocations) {
            invocation.verify();
        }
        verifier.verifyMoreInvocations();
    }

    private static void verifyInOrderInvocation(List<Invocation> invocations, Verifier verifier, Set<Object> mocks) {
        InOrder inOrder = inOrder(mocks.toArray());
        for (Invocation invocation : invocations) {
            invocation.verify(inOrder);
        }
        verifier.verifyMoreInvocations();
    }
}