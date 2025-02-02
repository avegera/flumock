# Flumock

Flumock is a fluent API for Mockito that simplifies the creation and verification of mocks in tests. With a readable DSL, it makes test code more declarative and eliminates boilerplate.

```java
String result = executionOf(() -> service.someMethod(123L, "value1"))
    .shouldInvoke(service1).voidMethod(s -> s.firstMethod(123L))
    .thenInvoke(service2).voidMethod(Service2::secondMethod)
    .thenInvoke(service3).method(s -> s.thirdMethod(123L, "value1")).thatReturn("result1")
    .thenInvoke(service4).method(s -> s.fourthMethod("result1")).thatReturn(true)
    .thenReturnResult();

assertThat(result).isEqualTo("result1");
```