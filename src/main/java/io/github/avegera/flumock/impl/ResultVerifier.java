package io.github.avegera.flumock.impl;

import io.github.avegera.flumock.impl.model.ExecutionContext;
import io.github.avegera.flumock.impl.model.Invocation;
import org.mockito.InOrder;

import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.verifyNoMoreInteractions;

public class ResultVerifier {

    private ResultVerifier() {
    }

    public static <T> T verifyInvocation(ExecutionContext<T> context) {
        setupInvocations(context);
        T result = context.getMethod().get();
        if (context.getVerifyInOrder()) {
            verifyInOrderInvocation(context.getMocks(), context.getInvocations());
        } else {
            verifyAnyOrderInvocation(context.getMocks(), context.getInvocations());
        }
        return result;
    }

    private static <T> void setupInvocations(ExecutionContext<T> context) {
        for (Invocation invocation : context.getInvocations()) {
            invocation.setup();
        }
    }

    private static void verifyAnyOrderInvocation(Set<Object> mocks, List<Invocation> invocations) {
        for (Invocation invocation : invocations) {
            invocation.verify();
        }
        verifyNoMoreInteractions(mocks.toArray());
    }

    private static void verifyInOrderInvocation(Set<Object> mocks, List<Invocation> invocations) {
        InOrder inOrder = inOrder(mocks.toArray());
        for (Invocation invocation : invocations) {
            invocation.verify(inOrder);
        }
        inOrder.verifyNoMoreInteractions();
    }
}