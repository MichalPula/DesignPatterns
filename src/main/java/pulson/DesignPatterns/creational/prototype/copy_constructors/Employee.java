package pulson.DesignPatterns.creational.prototype.copy_constructors;

import lombok.ToString;

@ToString
public class Employee {

    public String name;
    public Address address;

    public Employee(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    //copy constructor
    public Employee(Employee other) {
        this.name = other.name;
        this.address = new Address(other.address);
    }
}

@ToString
class Address {
    public String streetAddress, city, country;

    public Address(String streetAddress, String city, String country) {
        this.streetAddress = streetAddress;
        this.city = city;
        this.country = country;
    }

    //copy constructor przyjmuje obiekt tego samego typu i tworzy kopię wszystkich jego pól
    //jest to lepsze rozwiązanie niż implementacje Cloneable
    public Address(Address other) {
        this(other.streetAddress, other.city, other.country);
    }
}

class Demo {
    public static void main(String[] args) {
        Employee john = new Employee("John", new Address("123 London Road", "London", "UK"));
        Employee chris = new Employee(john);
        chris.name = "Chris";
        System.out.println(john);//Employee(name=John, address=Address(streetAddress=123 London Road, city=London, country=UK))
        System.out.println(chris);//Employee(name=Chris, address=Address(streetAddress=123 London Road, city=London, country=UK))
    }
}
