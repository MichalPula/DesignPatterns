package pulson.DesignPatterns.structural.flyweight;

import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

@AllArgsConstructor
public class User {
    private String fullName;
}

class User2 {
    static List<String> strings = new ArrayList<>();
    private int[] names;
    public User2(String fullName) {
        Function<String, Integer> getOrAdd = s -> {
            int index = strings.indexOf(s);
            if(index != -1){
                return index;
            } else {
                strings.add(s);
                return strings.size()-1;
            }
        };
        names = Arrays.stream(fullName.split(" "))
                .mapToInt(s -> getOrAdd.apply(s)).toArray();
    }
}


class Test {
    public static void main(String[] args) {
        User2 user1 = new User2("John Smith");
        User2 user2 = new User2("Jane Smith");
    }
}
