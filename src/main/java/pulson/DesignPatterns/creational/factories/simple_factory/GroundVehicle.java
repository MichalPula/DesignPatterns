package pulson.DesignPatterns.creational.factories.simple_factory;

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

enum GroundVehicleType {CAR, BIKE}

class GroundVehicleFactory {
    public GroundVehicle createGroundVehicle(GroundVehicleType type) {
        GroundVehicle groundVehicle = null;
        switch (type) {
            case CAR:
                groundVehicle = new Car();
                break;
            case BIKE:
                groundVehicle = new Bike();
                break;
        }
        return groundVehicle;
    }
}

class Test {
    public static void main(String[] args) {
        GroundVehicleFactory gvf = new GroundVehicleFactory();
        Car car = (Car) gvf.createGroundVehicle(GroundVehicleType.CAR);
        Bike bike = (Bike) gvf.createGroundVehicle(GroundVehicleType.BIKE);
        car.drive();//Samir you're breaking the car!
        bike.drive();//Riding a bike <3
    }
}
