package pulson.DesignPatterns.creational.builder;

import lombok.ToString;


@ToString
public class Person {
    //address
    public String streetAddress, postcode, city;

    //employment
    public String companyName, position;
    public int annualIncome;
}


//Builder facade
class PersonBuilder {
    protected Person person = new Person();

    public PersonAddressBuilder lives() {
        return new PersonAddressBuilder(person);
    }

    public PersonJobBuilder works() {
        return new PersonJobBuilder(person);
    }

    public Person build(){
        return person;
    }
}



class PersonAddressBuilder extends PersonBuilder {
    public PersonAddressBuilder(Person person) {
        this.person = person;
    }

    public PersonAddressBuilder at(String streetAddress) {
        person.streetAddress = streetAddress;
        return this;
    }

    public PersonAddressBuilder withPostcode(String postcode) {
        person.postcode = postcode;
        return this;
    }

    public PersonAddressBuilder in(String city) {
        person.city = city;
        return this;
    }
}



class PersonJobBuilder extends PersonBuilder {
    public PersonJobBuilder(Person person) {
        this.person = person;
    }

    public PersonJobBuilder at(String companyName) {
        person.companyName = companyName;
        return this;
    }

    public PersonJobBuilder asA(String position) {
        person.position = position;
        return this;
    }

    public PersonJobBuilder earning(int annualIncome) {
        person.annualIncome = annualIncome;
        return this;
    }
}


class Demo {
    public static void main(String[] args) {
        PersonBuilder personBuilder = new PersonBuilder();
        Person person = personBuilder
                .lives()
                .at("123 London Road").in("London").withPostcode("SW12BC")
                .works().at("Fabrikam").asA("Engineer").earning(123000)
                .build();
        System.out.println(person);
        //Person{streetAddress='123 London Road', postcode='SW12BC', city='London', companyName='Fabrikam', position='Engineer', annualIncome=123000}
    }
}
