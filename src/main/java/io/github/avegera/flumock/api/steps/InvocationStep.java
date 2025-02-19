package io.github.avegera.flumock.api.steps;

import java.util.function.Consumer;
import java.util.function.Function;

public interface InvocationStep<T, M> {

    <R> MethodInvocationStep<T, R> method(Function<M, R> function);

    VoidMethodInvocationStep<T> voidMethod(Consumer<M> consumer);
}