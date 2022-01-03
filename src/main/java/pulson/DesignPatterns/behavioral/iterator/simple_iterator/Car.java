package pulson.DesignPatterns.behavioral.iterator.simple_iterator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.Iterator;

@Getter
@AllArgsConstructor
@ToString
public class Car {
    private String manufacturer, model;
    private Integer price;
}

interface Collection {
    Iterator<?> iterator();
}

class MyCollection<E> implements Collection {
    private static final int DEFAULT_CAPACITY = 10;
    private static final int MAX_CAPACITY = Integer.MAX_VALUE;
    private int size = 0;
    private Object[] elementData;

    public MyCollection(int initialCapacity) {
        this.elementData = new Object[initialCapacity];
    }

    public void add(E element) {
        elementData[size] = element;
        size = size + 1;
    }

    @Override
    public Iterator<E> iterator() {
        return new MyIterator();
    }


    private class MyIterator implements Iterator<E> {
        private int currentIndex = -1;//this is wrong but it's enough for this example

        public MyIterator() {}

        @SuppressWarnings("unchecked")
        @Override
        public E next() {
            currentIndex += 1;
            return (E) elementData[currentIndex];
        }

        @Override
        public boolean hasNext() {
            if(currentIndex +1 >= elementData.length || elementData[currentIndex+1] == null) {
                return false;
            } else return true;
        }
    }
}

class Test {
    public static void main(String[] args) {
        MyCollection<Car> cars = new MyCollection<>(15);
        cars.add(new Car("Tesla", "Model S", 50_000));
        cars.add(new Car("Volkswagen", "Sharan", 2000));
        cars.add(new Car("Lamborghini", "Huracan", 300_000));

        Iterator<Car> iterator = cars.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}





