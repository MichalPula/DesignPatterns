package pulson.DesignPatterns.creational.prototype.serialization;

import lombok.AllArgsConstructor;
import lombok.ToString;
import org.apache.commons.lang3.SerializationUtils;

import java.io.Serializable;

@ToString
@AllArgsConstructor
public class MySuperClass implements Serializable {

    public String[] whatever;
    public int stuff;

    /*
    Problemem copy kontruktorów jest to, że potrzebujemy copy konstruktora dla każdego typu w hierarchii.
    Mając 20 typów - jeden bez konstruktora sprawi, że zrobimy shallow zamiast deep copy.
    Jak można temu zaradzić? Dzięki serializacji.
     */
}

class Demo {
    public static void main(String[] args) {
        MySuperClass mySuperClass1 = new MySuperClass(new String[]{"Boi"}, 42);
        //Apache Commons Lang
        MySuperClass mySuperClass2 = SerializationUtils.roundtrip(mySuperClass1);
        mySuperClass2.whatever[0] = "xyz";

        System.out.println(mySuperClass1);//MySuperClass(whatever=Boi, stuff=42)
        System.out.println(mySuperClass2);//MySuperClass(whatever=xyz, stuff=42)
    }
}
