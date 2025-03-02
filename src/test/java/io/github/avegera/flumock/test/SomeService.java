package io.github.avegera.flumock.test;

public class SomeService {

    private final Service1 service1;

    private final Service2 service2;

    private final Service3 service3;

    private final Service4 service4;

    public SomeService(Service1 service1, Service2 service2, Service3 service3, Service4 service4) {
        this.service1 = service1;
        this.service2 = service2;
        this.service3 = service3;
        this.service4 = service4;
    }

    public String someMethod(Long arg1, String arg2) {
        service1.firstMethod(arg1);
        service2.secondMethod();
        String generatedId = service3.thirdMethod(arg1, arg2);
        boolean response = service4.fourthMethod(generatedId);
        return generatedId + response;
    }

    public String someMethod2(Long arg1, String arg2) {
        String generatedId = service3.thirdMethod(arg1, arg2);
        service4.fourthMethod(generatedId);
        service1.firstMethod(arg1);
        return arg2;
    }

    public String someMethod3(Long arg1, String arg2) {
        service1.firstMethod(arg1);
        service2.secondMethod();
        service2.secondMethod();
        service2.secondMethod();
        return arg2;
    }
}