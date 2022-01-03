package pulson.DesignPatterns.structural.decorator;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MagicString {
    private String string;

    public long getNumberOfVowels(){
        return string.chars().mapToObj(character -> (char) character)
                .filter(character -> "aeiou".contains(String.valueOf(character)))
                .count();
    }

    @Override
    public String toString() {
        return string;
    }

//////////////////////Zachowane metody klasy String

    public int length() {
        return string.length();
    }

    public boolean isEmpty() {
        return string.isEmpty();
    }

    public char charAt(int index) {
        return string.charAt(index);
    }
}

class Test {
    public static void main(String[] args) {
        MagicString ms = new MagicString("Hello");
        System.out.println(ms + " has " + ms.getNumberOfVowels()+ " vovels");
    }
}
