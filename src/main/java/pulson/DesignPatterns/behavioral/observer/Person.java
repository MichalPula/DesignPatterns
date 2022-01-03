package pulson.DesignPatterns.behavioral.observer;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
class PropertyChangedEverntArgs<T> {
    public T source;
    public String propertyName;
    public Object newValue;
}

interface Observer<T> {
    void handle(PropertyChangedEverntArgs<T> args);
}

class Observable<T> {
    private List<Observer<T>> observers = new ArrayList<>();

    public void subscribe(Observer<T> observer) {
        observers.add(observer);
    }

    protected void propertyChanged(T source, String propertyValue, Object newValue) {
        observers.forEach(observer -> {
            observer.handle(new PropertyChangedEverntArgs<T>(source, propertyValue, newValue));
        });
    }
}


@Getter
public class Person extends Observable<Person> {
    private int age;

    public void setAge(int age) {
        if(this.age == age) return;
        this.age = age;
        propertyChanged(this, "age", age);
    }
}

class Test implements Observer<Person> {
    public static void main(String[] args) {
        new Test();
    }
    public Test() {
        Person person = new Person();
        person.subscribe(this);
        for (int i = 20; i < 24; i++) {
            person.setAge(i);
        }
    }

    @Override
    public void handle(PropertyChangedEverntArgs<Person> args) {
        System.out.println("Person's " + args.propertyName + " has changed to " + args.newValue);
    }
}
