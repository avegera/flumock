package io.github.avegera.flumock;

import io.github.avegera.flumock.impl.model.ExecutionContext;
import io.github.avegera.flumock.impl.step.ExecutionInitialStep;

import java.util.function.Supplier;

public class Flumock {

    private Flumock() {
    }

    public static <T> ExecutionInitialStep<T> executionOf(Supplier<T> supplier) {
        return new ExecutionInitialStep<>(new ExecutionContext<>(supplier));
    }
}