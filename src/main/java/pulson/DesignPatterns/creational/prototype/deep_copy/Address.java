package pulson.DesignPatterns.creational.prototype.deep_copy;

import lombok.ToString;

@ToString
public class Address implements Cloneable {
    public String streetName;
    public int houseNumber;

    public Address(String streetName, int houseNumber) {
        this.streetName = streetName;
        this.houseNumber = houseNumber;
    }

    //deep copy
    @Override
    public Object clone() throws CloneNotSupportedException {
        return new Address(streetName, houseNumber);
    }
}



@ToString
class Person implements Cloneable {
    public String[] names;
    public Address address;

    public Person(String[] names, Address address) {
        this.names = names;
        this.address = address;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        //Nie jest to deep copy! Zostanie skopiowana referencja do obiektu Address
        //return new Person(names, address);
        return new Person(names.clone(), (Address) address.clone());
    }
}


class Demo {
    public static void main(String[] args) throws CloneNotSupportedException {
        Person john = new Person(new String[]{"John", "Smith"}, new Address("London Road", 123));
        //Chcąc stworzyć sąsiada Johna nie musimy tworzyć tego samego długiego konstruktora. Wystarczy go skopiować.

        Person jane = john;// do obiektu Jane przypisujemy referencję obiektu John. Jane korzysta z tej samej przestrzeni w pamięci
        //i przechowuje te same dane... Zmiany w jednym obiekcie będą widoczne w drugim. Tego nie chcemy!
        jane.names[0] = "Jane";
        jane.address.houseNumber = 124;
        //System.out.println(john);//Person{names=[Jane, Smith], address=Address{streetName='London Road', houseNumber=124}}
        //System.out.println(jane);//Person{names=[Jane, Smith], address=Address{streetName='London Road', houseNumber=124}}


        //Shallow copy to skopiowanie referencji z obiektu do obiektu. Zmiany w jednym obiekcie będą widoczne w drugim.
        //Deep copy to replikacja każdej wartości obiektu.
        Person john2 = new Person(new String[]{"John", "Smith"}, new Address("London Road", 123));
        Person jane2 = (Person) john2.clone();
        jane2.names[0] = "Jane";
        jane2.address.houseNumber = 124;
        System.out.println(john2);//Person{names=[John, Smith], address=Address{streetName='London Road', houseNumber=123}}
        System.out.println(jane2);//Person{names=[Jane, Smith], address=Address{streetName='London Road', houseNumber=124}}
    }
}
