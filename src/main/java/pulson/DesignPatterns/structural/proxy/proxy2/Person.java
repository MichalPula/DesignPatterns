package pulson.DesignPatterns.structural.proxy.proxy2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

interface Human {
    void talk();
    void walk();
}

class Person implements Human {
    @Override
    public void talk() {
        System.out.println("I am talking");
    }

    @Override
    public void walk() {
        System.out.println("I am walking");
    }
}

class LoggingHandler implements InvocationHandler {
    private final Object target;
    private Map<String, Integer> methodNameToNumberOfCalls = new HashMap<>();

    public LoggingHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String name = method.getName();

        if(name.contains("toString")) {
            return methodNameToNumberOfCalls.toString();
        }

        methodNameToNumberOfCalls.merge(name, 1, Integer::sum);
        return method.invoke(target, args);
    }
}

class Test {
    public static <T> T withLogging(T target, Class<T> interf) {
        return (T) Proxy.newProxyInstance(interf.getClassLoader(), new Class<?>[] {interf} , new LoggingHandler(target));
    }

    public static void main(String[] args) {
        Person person = new Person();
        Human logged = withLogging(person, Human.class);
        logged.talk();
        logged.walk();
        logged.walk();
        System.out.println(logged);
    }
}
