package io.github.avegera.flumock.api.steps;

import java.util.function.Consumer;
import java.util.function.Function;

public interface MethodInvocationStep<T, M> {

    <R> ResultMethodInvocationStep<T, R> method(Function<M, R> function);

    VoidMethodInvocationStep<T> voidMethod(Consumer<M> consumer);
}