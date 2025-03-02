package io.github.avegera.flumock.api.steps;

import java.util.function.Consumer;
import java.util.function.Function;

public interface MethodInvocationStep<T, M> {

    <R> MethodInvocationResultStep<T, R> method(Function<M, R> function);

    InvocationVerificationStep<T> voidMethod(Consumer<M> consumer);
}