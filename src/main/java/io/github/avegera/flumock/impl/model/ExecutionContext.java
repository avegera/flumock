package io.github.avegera.flumock.impl.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

public class ExecutionContext<T> {

    private final Supplier<T> method;

    private final Set<Object> mocks = new HashSet<>();

    private final List<Invocation> invocations = new ArrayList<>();

    public ExecutionContext(Supplier<T> method) {
        this.method = method;
    }

    public Supplier<T> getMethod() {
        return method;
    }

    public Set<Object> getMocks() {
        return mocks;
    }

    public List<Invocation> getInvocations() {
        return invocations;
    }
}