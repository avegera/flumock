package io.github.avegera.flumock;

import io.github.avegera.flumock.api.steps.ExecutionInitialStep;
import io.github.avegera.flumock.impl.model.ExecutionContext;
import io.github.avegera.flumock.impl.steps.ExecutionInitialStepImpl;

import java.util.function.Supplier;

public class Flumock {

    private Flumock() {
    }

    public static <T> ExecutionInitialStep<T> executionOf(Supplier<T> supplier) {
        return new ExecutionInitialStepImpl<>(new ExecutionContext<>(supplier));
    }
}