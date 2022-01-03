package pulson.DesignPatterns.behavioral.mediator;

import java.util.ArrayList;
import java.util.List;

public class Person {
    public String name;
    private List<String> chatLogs = new ArrayList<>();
    public ChatRoom room;

    public Person(String name) {
        this.name = name;
    }

    public void receive(String sender, String message) {
        String chatLog = sender + ": '" + message + "'";
        System.out.println("[" + name + "'s chat session]" + chatLog);
        chatLogs.add(chatLog);
    }
    public void say(String message) {
        room.broadcast(name, message);
    }
    public void privateMessage(String destination, String message) {
        room.message(name, destination, message);
    }
}

//Mediator
class ChatRoom {
    private List<Person> peopleOnChat = new ArrayList<>();

    public void join(Person person) {
        String joinMessage = person.name + " joins the room";
        broadcast("room", joinMessage);
        person.room = this;
        peopleOnChat.add(person);
    }

    public void broadcast(String source, String message) {
        peopleOnChat.forEach(person -> {
            if(!person.name.equals(source)) person.receive(source, message);
        });
    }

    public void message(String source, String destination, String message) {
        peopleOnChat.stream().filter(person -> person.name.equals(destination))
                .findFirst()
                .ifPresent(person -> person.receive(source, message));
    }
}

class Test {
    public static void main(String[] args) {
        ChatRoom room = new ChatRoom();
        Person john = new Person("John");
        Person jane = new Person("Jane");
        room.join(john);
        room.join(jane);

        jane.say("Hi room!");
        jane.say("Oh, hey John!");

        Person simon = new Person("Simon");
        room.join(simon);
        simon.say("Hi everyone!");

        jane.privateMessage("Simon", "Glad you could join us!");
    }
}
