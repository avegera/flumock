# Flumock

Flumock is a fluent API for Mockito that simplifies the creation and verification of mocks in tests. With a readable DSL, it makes test code more declarative and eliminates boilerplate.

```java
String result = executionOf(() -> service.someMethod2(123L, "value1"))
        .shouldInOrder()
            .invoke(service1).voidMethod(s -> s.firstMethod(123L))
            .invoke(service3).method(s -> s.thirdMethod(123L, "value1")).returns("result1")
            .invoke(service4).method(s -> s.fourthMethod("result1")).returns(true)
        .noMoreInvocationsIncluding(service2)
        .thenReturnResult();

assertThat(result).isEqualTo("result1");
```