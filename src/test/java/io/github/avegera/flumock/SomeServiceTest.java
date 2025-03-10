package io.github.avegera.flumock;

import io.github.avegera.flumock.test.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.exceptions.verification.NoInteractionsWanted;
import org.mockito.exceptions.verification.TooManyActualInvocations;

import static io.github.avegera.flumock.Flumock.executionOf;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

class SomeServiceTest {

    private static final Long TEST_ARG_1 = 123L;
    private static final String TEST_GENERATED_ID = TEST_ARG_1 + "456";
    private static final String TEST_ARG_2 = "object/path";
    private static final boolean TEST_BOOL_RESULT = true;

    @Test
    void testSomeMethod() {
        Service1 service1 = mock(Service1.class);
        Service2 service2 = mock(Service2.class);
        Service3 service3 = mock(Service3.class);
        Service4 service4 = mock(Service4.class);
        SomeService service = new SomeService(service1, service2, service3, service4);

        doNothing().when(service1).firstMethod(TEST_ARG_1);
        doNothing().when(service2).secondMethod();
        doReturn(TEST_GENERATED_ID).when(service3).thirdMethod(TEST_ARG_1, TEST_ARG_2);
        doReturn(TEST_BOOL_RESULT).when(service4).fourthMethod(TEST_GENERATED_ID);

        String result = service.someMethod(TEST_ARG_1, TEST_ARG_2);

        org.mockito.InOrder inOrder = inOrder(service1, service2, service3, service4);
        inOrder.verify(service1).firstMethod(TEST_ARG_1);
        inOrder.verify(service2).secondMethod();
        inOrder.verify(service3).thirdMethod(TEST_ARG_1, TEST_ARG_2);
        inOrder.verify(service4).fourthMethod(TEST_GENERATED_ID);
        inOrder.verifyNoMoreInteractions();

        assertThat(result).isEqualTo(TEST_GENERATED_ID + TEST_BOOL_RESULT);
    }

    @Nested
    @DisplayName("In order")
    class InOrder {

        @Nested
        @DisplayName("Allow more invocations")
        class AllowMoreInvocations {

            @Test
            void successfullyVerifyInvocations() {
                Service1 service1 = mock(Service1.class);
                Service2 service2 = mock(Service2.class);
                Service3 service3 = mock(Service3.class);
                Service4 service4 = mock(Service4.class);
                SomeService service = new SomeService(service1, service2, service3, service4);

                String result = executionOf(() -> service.someMethod2(TEST_ARG_1, TEST_ARG_2))
                        .shouldInOrder()
                            .invoke(service3).method(s -> s.thirdMethod(TEST_ARG_1, TEST_ARG_2)).returnsNull()
                            .invoke(service4).method(s -> s.fourthMethod(null)).returns(TEST_BOOL_RESULT)
                        .allowMoreInvocations()
                        .thenReturnResult();

                assertThat(result).isEqualTo(TEST_ARG_2);
            }
        }

        @Nested
        @DisplayName("Allow more invocations except")
        class AllowMoreInvocationsExcept {

            @Test
            void successfullyVerifyInvocations() {
                Service1 service1 = mock(Service1.class);
                Service2 service2 = mock(Service2.class);
                Service3 service3 = mock(Service3.class);
                Service4 service4 = mock(Service4.class);
                SomeService service = new SomeService(service1, service2, service3, service4);

                String result = executionOf(() -> service.someMethod3(TEST_ARG_1, TEST_ARG_2))
                        .shouldInOrder()
                            .invoke(service1).voidMethod(s -> s.firstMethod(TEST_ARG_1))
                        .allowMoreInvocationsExcept(service1)
                        .thenReturnResult();

                assertThat(result).isEqualTo(TEST_ARG_2);
            }

            @Test
            void throwsVerificationException() {
                Service1 service1 = mock(Service1.class);
                Service2 service2 = mock(Service2.class);
                Service3 service3 = mock(Service3.class);
                Service4 service4 = mock(Service4.class);
                SomeService service = new SomeService(service1, service2, service3, service4);

                assertThatThrownBy(() -> executionOf(() -> service.someMethod3(TEST_ARG_1, TEST_ARG_2))
                        .shouldInOrder()
                            .invoke(service1).voidMethod(s -> s.firstMethod(TEST_ARG_1))
                        .allowMoreInvocationsExcept(service1, service2)
                        .thenReturnResult()
                ).isInstanceOf(NoInteractionsWanted.class);
            }
        }

        @Nested
        @DisplayName("Allow more invocations only for")
        class AllowMoreInvocationsOnlyFor {

            @Test
            void successfullyVerifyInvocations() {
                Service1 service1 = mock(Service1.class);
                Service2 service2 = mock(Service2.class);
                Service3 service3 = mock(Service3.class);
                Service4 service4 = mock(Service4.class);
                SomeService service = new SomeService(service1, service2, service3, service4);

                String result = executionOf(() -> service.someMethod3(TEST_ARG_1, TEST_ARG_2))
                        .shouldInOrder()
                            .invoke(service1).voidMethod(s -> s.firstMethod(TEST_ARG_1))
                        .allowMoreInvocationsOnlyFor(service2)
                        .thenReturnResult();

                assertThat(result).isEqualTo(TEST_ARG_2);
            }

            @Test
            void throwsVerificationException() {
                Service1 service1 = mock(Service1.class);
                Service2 service2 = mock(Service2.class);
                Service3 service3 = mock(Service3.class);
                Service4 service4 = mock(Service4.class);
                SomeService service = new SomeService(service1, service2, service3, service4);

                assertThatThrownBy(() -> executionOf(() -> service.someMethod3(TEST_ARG_1, TEST_ARG_2))
                        .shouldInOrder()
                            .invoke(service1).voidMethod(s -> s.firstMethod(TEST_ARG_1))
                        .allowMoreInvocationsExcept(service2)
                        .thenReturnResult()
                ).isInstanceOf(NoInteractionsWanted.class);
            }
        }

        @Nested
        @DisplayName("No more invocations")
        class NoMoreInvocations {

            @Test
            void successfullyVerifyInvocations() {
                Service1 service1 = mock(Service1.class);
                Service2 service2 = mock(Service2.class);
                Service3 service3 = mock(Service3.class);
                Service4 service4 = mock(Service4.class);
                SomeService service = new SomeService(service1, service2, service3, service4);

                String result = executionOf(() -> service.someMethod(TEST_ARG_1, TEST_ARG_2))
                        .shouldInOrder()
                            .invoke(service1).voidMethod(s -> s.firstMethod(TEST_ARG_1))
                            .invoke(service2).voidMethod(Service2::secondMethod)
                            .invoke(service3).method(s -> s.thirdMethod(TEST_ARG_1, TEST_ARG_2)).returns(TEST_GENERATED_ID)
                            .invoke(service4).method(s -> s.fourthMethod(TEST_GENERATED_ID)).returns(TEST_BOOL_RESULT)
                        .noMoreInvocations()
                        .thenReturnResult();

                assertThat(result).isEqualTo(TEST_GENERATED_ID + TEST_BOOL_RESULT);
            }

//            @Test
//            void throwsVerificationException() {
//                Service1 service1 = mock(Service1.class);
//                Service2 service2 = mock(Service2.class);
//                Service3 service3 = mock(Service3.class);
//                Service4 service4 = mock(Service4.class);
//                SomeService service = new SomeService(service1, service2, service3, service4);
//
//                assertThatThrownBy(() -> executionOf(() -> service.someMethod3(TEST_ARG_1, TEST_ARG_2))
//                        .shouldInOrder()
//                            .invoke(service1).voidMethod(s -> s.firstMethod(TEST_ARG_1))
//                            //TODO: uncomment in future when times(N) will be implemented
//                            .invoke(service2).voidMethod(Service2::secondMethod)
//                        .noMoreInvocations()
//                        .thenReturnResult()
//                ).isInstanceOf(TooManyActualInvocations.class);
//            }
        }

        @Nested
        @DisplayName("No more invocations including")
        class NoMoreInvocationsIncluding {

            @Test
            void successfullyVerifyInvocations() {
                Service1 service1 = mock(Service1.class);
                Service2 service2 = mock(Service2.class);
                Service3 service3 = mock(Service3.class);
                Service4 service4 = mock(Service4.class);
                SomeService service = new SomeService(service1, service2, service3, service4);

                String result = executionOf(() -> service.someMethod2(TEST_ARG_1, TEST_ARG_2))
                        .shouldInOrder()
                            .invoke(service3).method(s -> s.thirdMethod(TEST_ARG_1, TEST_ARG_2)).returns(TEST_GENERATED_ID)
                            .invoke(service4).method(s -> s.fourthMethod(TEST_GENERATED_ID)).returns(TEST_BOOL_RESULT)
                            .invoke(service1).voidMethod(s -> s.firstMethod(TEST_ARG_1))
                        .noMoreInvocationsIncluding(service2)
                        .thenReturnResult();

                assertThat(result).isEqualTo(TEST_ARG_2);
            }

            @Test
            void throwsVerificationException() {
                Service1 service1 = mock(Service1.class);
                Service2 service2 = mock(Service2.class);
                Service3 service3 = mock(Service3.class);
                Service4 service4 = mock(Service4.class);
                SomeService service = new SomeService(service1, service2, service3, service4);

                assertThatThrownBy(() -> executionOf(() -> service.someMethod(TEST_ARG_1, TEST_ARG_2))
                        .shouldInOrder()
                            .invoke(service1).voidMethod(s -> s.firstMethod(TEST_ARG_1))
                            .invoke(service2).voidMethod(Service2::secondMethod)
                        .noMoreInvocationsIncluding(service3, service4)
                        .thenReturnResult()
                ).isInstanceOf(NoInteractionsWanted.class);
            }
        }
    }

    @Nested
    @DisplayName("In any order")
    class InAnyOrder {

        @Nested
        @DisplayName("Allow more invocations")
        class AllowMoreInvocations {

            @Test
            void successfullyVerifyInvocations() {
                Service1 service1 = mock(Service1.class);
                Service2 service2 = mock(Service2.class);
                Service3 service3 = mock(Service3.class);
                Service4 service4 = mock(Service4.class);
                SomeService service = new SomeService(service1, service2, service3, service4);

                String result = executionOf(() -> service.someMethod2(TEST_ARG_1, TEST_ARG_2))
                        .shouldInAnyOrder()
                            .invoke(service4).method(s -> s.fourthMethod(null)).returns(TEST_BOOL_RESULT)
                            .invoke(service3).method(s -> s.thirdMethod(TEST_ARG_1, TEST_ARG_2)).returnsNull()
                        .allowMoreInvocations()
                        .thenReturnResult();

                assertThat(result).isEqualTo(TEST_ARG_2);
            }
        }

        @Nested
        @DisplayName("Allow more invocations except")
        class AllowMoreInvocationsExcept {

            @Test
            void successfullyVerifyInvocations() {
                Service1 service1 = mock(Service1.class);
                Service2 service2 = mock(Service2.class);
                Service3 service3 = mock(Service3.class);
                Service4 service4 = mock(Service4.class);
                SomeService service = new SomeService(service1, service2, service3, service4);

                String result = executionOf(() -> service.someMethod3(TEST_ARG_1, TEST_ARG_2))
                        .shouldInAnyOrder()
                            .invoke(service1).voidMethod(s -> s.firstMethod(TEST_ARG_1))
                        .allowMoreInvocationsExcept(service1)
                        .thenReturnResult();

                assertThat(result).isEqualTo(TEST_ARG_2);
            }

            @Test
            void throwsVerificationException() {
                Service1 service1 = mock(Service1.class);
                Service2 service2 = mock(Service2.class);
                Service3 service3 = mock(Service3.class);
                Service4 service4 = mock(Service4.class);
                SomeService service = new SomeService(service1, service2, service3, service4);

                assertThatThrownBy(() -> executionOf(() -> service.someMethod3(TEST_ARG_1, TEST_ARG_2))
                        .shouldInAnyOrder()
                            .invoke(service1).voidMethod(s -> s.firstMethod(TEST_ARG_1))
                        .allowMoreInvocationsExcept(service1, service2)
                        .thenReturnResult()
                ).isInstanceOf(NoInteractionsWanted.class);
            }
        }

        @Nested
        @DisplayName("Allow more invocations only for")
        class AllowMoreInvocationsOnlyFor {

            @Test
            void successfullyVerifyInvocations() {
                Service1 service1 = mock(Service1.class);
                Service2 service2 = mock(Service2.class);
                Service3 service3 = mock(Service3.class);
                Service4 service4 = mock(Service4.class);
                SomeService service = new SomeService(service1, service2, service3, service4);

                String result = executionOf(() -> service.someMethod3(TEST_ARG_1, TEST_ARG_2))
                        .shouldInAnyOrder()
                            .invoke(service1).voidMethod(s -> s.firstMethod(TEST_ARG_1))
                        .allowMoreInvocationsOnlyFor(service1, service2)
                        .thenReturnResult();

                assertThat(result).isEqualTo(TEST_ARG_2);
            }

            @Test
            void throwsVerificationException() {
                Service1 service1 = mock(Service1.class);
                Service2 service2 = mock(Service2.class);
                Service3 service3 = mock(Service3.class);
                Service4 service4 = mock(Service4.class);
                SomeService service = new SomeService(service1, service2, service3, service4);

                assertThatThrownBy(() -> executionOf(() -> service.someMethod3(TEST_ARG_1, TEST_ARG_2))
                        .shouldInAnyOrder()
                            .invoke(service1).voidMethod(s -> s.firstMethod(TEST_ARG_1))
                        .allowMoreInvocationsExcept(service2)
                        .thenReturnResult()
                ).isInstanceOf(NoInteractionsWanted.class);
            }
        }

        @Nested
        @DisplayName("No more invocations")
        class NoMoreInvocations {

            @Test
            void successfullyVerifyInvocations() {
                Service1 service1 = mock(Service1.class);
                Service2 service2 = mock(Service2.class);
                Service3 service3 = mock(Service3.class);
                Service4 service4 = mock(Service4.class);
                SomeService service = new SomeService(service1, service2, service3, service4);

                String result = executionOf(() -> service.someMethod(TEST_ARG_1, TEST_ARG_2))
                        .shouldInAnyOrder()
                            .invoke(service1).voidMethod(s -> s.firstMethod(TEST_ARG_1))
                            .invoke(service2).voidMethod(Service2::secondMethod)
                            .invoke(service3).method(s -> s.thirdMethod(TEST_ARG_1, TEST_ARG_2)).returns(TEST_GENERATED_ID)
                            .invoke(service4).method(s -> s.fourthMethod(TEST_GENERATED_ID)).returns(TEST_BOOL_RESULT)
                        .noMoreInvocations()
                        .thenReturnResult();

                assertThat(result).isEqualTo(TEST_GENERATED_ID + TEST_BOOL_RESULT);
            }

            @Test
            void throwsVerificationException() {
                Service1 service1 = mock(Service1.class);
                Service2 service2 = mock(Service2.class);
                Service3 service3 = mock(Service3.class);
                Service4 service4 = mock(Service4.class);
                SomeService service = new SomeService(service1, service2, service3, service4);

                assertThatThrownBy(() -> executionOf(() -> service.someMethod3(TEST_ARG_1, TEST_ARG_2))
                        .shouldInAnyOrder()
                            .invoke(service1).voidMethod(s -> s.firstMethod(TEST_ARG_1))
                            .invoke(service2).voidMethod(Service2::secondMethod)
                        .noMoreInvocations()
                        .thenReturnResult()
                ).isInstanceOf(TooManyActualInvocations.class);
            }
        }

        @Nested
        @DisplayName("No more invocations including")
        class NoMoreInvocationsIncluding {

            @Test
            void successfullyVerifyInvocations() {
                Service1 service1 = mock(Service1.class);
                Service2 service2 = mock(Service2.class);
                Service3 service3 = mock(Service3.class);
                Service4 service4 = mock(Service4.class);
                SomeService service = new SomeService(service1, service2, service3, service4);

                String result = executionOf(() -> service.someMethod2(TEST_ARG_1, TEST_ARG_2))
                        .shouldInAnyOrder()
                            .invoke(service1).voidMethod(s -> s.firstMethod(TEST_ARG_1))
                            .invoke(service3).method(s -> s.thirdMethod(TEST_ARG_1, TEST_ARG_2)).returns(TEST_GENERATED_ID)
                            .invoke(service4).method(s -> s.fourthMethod(TEST_GENERATED_ID)).returns(TEST_BOOL_RESULT)
                        .noMoreInvocationsIncluding(service2)
                        .thenReturnResult();

                assertThat(result).isEqualTo(TEST_ARG_2);
            }

            @Test
            void throwsVerificationException() {
                Service1 service1 = mock(Service1.class);
                Service2 service2 = mock(Service2.class);
                Service3 service3 = mock(Service3.class);
                Service4 service4 = mock(Service4.class);
                SomeService service = new SomeService(service1, service2, service3, service4);

                assertThatThrownBy(() -> executionOf(() -> service.someMethod(TEST_ARG_1, TEST_ARG_2))
                        .shouldInAnyOrder()
                            .invoke(service1).voidMethod(s -> s.firstMethod(TEST_ARG_1))
                            .invoke(service2).voidMethod(Service2::secondMethod)
                        .noMoreInvocationsIncluding(service3, service4)
                        .thenReturnResult()
                ).isInstanceOf(NoInteractionsWanted.class);
            }
        }
    }
}