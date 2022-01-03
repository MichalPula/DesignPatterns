package pulson.DesignPatterns.structural.proxy;


import lombok.AllArgsConstructor;

interface Drivable {
    void drive();
}

@AllArgsConstructor
public class Car implements Drivable {
    protected Driver driver;

    @Override
    public void drive() {
        System.out.println("Car being driven");
    }
}

@AllArgsConstructor
class Driver {
    int age;
}

class CarProxy extends Car {
    public CarProxy(Driver driver) {
        super(driver);
    }

    @Override
    public void drive() {
        if (driver.age >= 16) {
            super.drive();
        } else {
            System.out.println("Driver is too young!");
        }
    }
}

class Test {
    public static void main(String[] args) {
        Car car = new CarProxy(new Driver(12));
        car.drive(); //Driver is too young!
    }
}
