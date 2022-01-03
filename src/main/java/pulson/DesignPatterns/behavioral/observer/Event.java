package pulson.DesignPatterns.behavioral.observer;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class Event<TArgs> {
    private int count = 0;
    private Map<Integer, Consumer<TArgs>> handlers = new HashMap<>();

    public Subscription addHandler(Consumer<TArgs> handler) {
        int i = count;
        handlers.put(count++, handler);
        return new Subscription(this, i);
    }

    public void fire(TArgs args) {
        handlers.values().forEach(handler -> {
            handler.accept(args);
        });
    }

    @AllArgsConstructor
    public class Subscription implements AutoCloseable {
        private Event<TArgs> event;
        private int id;

        @Override
        public void close() {
            event.handlers.remove(id);
        }
    }
}

@AllArgsConstructor
class PropertyChangedEventArgs {
    public Object source;
    public String name;
}

@Getter
class Personn {
    public Event<PropertyChangedEventArgs> propertyChanged = new Event<>();
    private int age;

    public void setAge(int age) {
        if(this.age == age) return;
        this.age = age;
        propertyChanged.fire(new PropertyChangedEventArgs(this, "age"));
    }
}

class Testt {
    public static void main(String[] args) {
        Personn personn = new Personn();
        Event<PropertyChangedEventArgs>.Subscription sub = personn.propertyChanged.addHandler(x -> {
            System.out.println("Person's " + x.name + " has changed");
        });
        personn.setAge(17);
        personn.setAge(18);
        sub.close();
        personn.setAge(19);
    }
}
