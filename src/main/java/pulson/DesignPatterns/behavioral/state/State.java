package pulson.DesignPatterns.behavioral.state;


import lombok.Setter;

public class State {
    void on(LightSwitch ls) {
        System.out.println("Light is already on");
    }
    void off(LightSwitch ls) {
        System.out.println("Light is already off");
    }
}
class OnState extends State {
    public OnState() {
        System.out.println("Light turned on");
    }

    @Override
    void off(LightSwitch ls) {
        System.out.println("Switching light off...");
        ls.setState(new OffState());
    }
}
class OffState extends State {
    public OffState() {
        System.out.println("Light turned off");
    }

    @Override
    void on(LightSwitch ls) {
        System.out.println("Switching light on...");
        ls.setState(new OnState());
    }
}

@Setter
class LightSwitch {
    private State state;//OnState/OffState
    public LightSwitch() {
        this.state = new OffState();
    }
    void on() {
        state.on(this);
    }
    void off() {
        state.off(this);
    }
}

class Test {
    public static void main(String[] args) {
        LightSwitch ls = new LightSwitch();
        ls.on();
        ls.off();
        ls.off();
    }
}
