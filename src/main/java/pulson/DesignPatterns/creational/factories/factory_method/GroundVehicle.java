package pulson.DesignPatterns.creational.factories.factory_method;

public interface GroundVehicle {
    void drive();
}
class Car implements GroundVehicle {
    @Override
    public void drive() {
        System.out.println("Samir you're breaking the car!");
    }
}
class Bike implements GroundVehicle {
    @Override
    public void drive() {
        System.out.println("Riding a bike <3");
    }
}


interface GroundVehicleFactory {
    GroundVehicle createGroundVehicle();
}
class CarFactory implements GroundVehicleFactory {
    //dodatkowe metody wytwórcze z różnymi argumentami
    @Override
    public GroundVehicle createGroundVehicle() {
        return new Car();
    }
}
class BikeFactory implements GroundVehicleFactory {
    //dodatkowe metody wytwórcze z różnymi argumentami
    @Override
    public GroundVehicle createGroundVehicle() {
        return new Bike();
    }
}


class Test {
    public static void main(String[] args) {
        GroundVehicleFactory carFactory = new CarFactory();
        GroundVehicleFactory bikeFactory = new BikeFactory();
        GroundVehicle car = carFactory.createGroundVehicle();
        GroundVehicle bike = bikeFactory.createGroundVehicle();
        car.drive();//Samir you're breaking the car!
        bike.drive();//Riding a bike <3
    }
}
