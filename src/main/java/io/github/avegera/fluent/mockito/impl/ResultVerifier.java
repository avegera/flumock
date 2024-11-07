package io.github.avegera.fluent.mockito.impl;

import io.github.avegera.fluent.mockito.impl.model.ExecutionContext;
import io.github.avegera.fluent.mockito.impl.model.Invocation;
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
        verifyOrderInvocation(context.getMocks(), context.getInvocations());
        return result;
    }

    private static <T> void setupInvocations(ExecutionContext<T> context) {
        for (Invocation invocation : context.getInvocations()) {
            invocation.setup();
        }
    }

    private static void verifyOrderInvocation(Set<Object> mocks, List<Invocation> context) {
        InOrder inOrder = inOrder(mocks.toArray());
        for (Invocation invocation : context) {
            invocation.verify(inOrder);
        }
        inOrder.verifyNoMoreInteractions();
    }
}