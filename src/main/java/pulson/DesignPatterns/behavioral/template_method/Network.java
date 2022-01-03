package pulson.DesignPatterns.behavioral.template_method;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.nio.charset.StandardCharsets;

@NoArgsConstructor
public abstract class Network {
    protected String username;
    protected String password;
    //Metoda szablonowa, która ustala kolejność wywołania etapów algorytmu
    public void post(String message) {
        if(logIn(this.username, this.password)) {
            boolean result = sendData(message.getBytes(StandardCharsets.UTF_8));
            logOut();
        }
    }
    abstract boolean logIn(String username, String password);
    abstract boolean sendData(byte[] data);
    abstract void logOut();
}

@AllArgsConstructor
class Facebook extends Network {
    public Facebook(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    boolean logIn(String username, String password) {
        System.out.println(username + " you are logged on Facebook!");
        return true;
    }
    @Override
    boolean sendData(byte[] data) {
        System.out.println("Message: '" + new String(data) + "' was posted on Facebook");
        return true;
    }
    @Override
    void logOut() {
        System.out.println(username + " you have been logged out from Facebook!");
    }
}

@AllArgsConstructor
class Twitter extends Network {
    @Override
    boolean logIn(String username, String password) {
        System.out.println(username + " you are logged on Twitter!");
        return true;
    }
    @Override
    boolean sendData(byte[] data) {
        System.out.println("Message: '" + new String(data) + "' was posted on Twitter");
        return true;
    }
    @Override
    void logOut() {
        System.out.println(username + " you have been logged out from Twitter!");
    }
}


class Test {
    public static void main(String[] args) {
        Network facebook = new Facebook("pulson", "ok");
        facebook.post("I'm having so much fun rn!!!");
        //pulson you are logged on Facebook!
        //Message: 'I'm having so much fun rn!!!' was posted on Facebook
        //pulson you have been logged out from Facebook!
    }
}
