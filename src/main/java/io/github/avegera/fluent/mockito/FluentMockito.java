package io.github.avegera.fluent.mockito;

import io.github.avegera.fluent.mockito.impl.model.ExecutionContext;
import io.github.avegera.fluent.mockito.impl.step.ExecutionInitialStep;

import java.util.function.Supplier;

public class FluentMockito {

    private FluentMockito() {
    }

    public static <T> ExecutionInitialStep<T> executionOf(Supplier<T> supplier) {
        return new ExecutionInitialStep<>(new ExecutionContext<>(supplier));
    }
}