package pulson.DesignPatterns.behavioral.state.state_machine;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

enum State {
    OFF_HOOK, //starting state
    ON_HOOK, //zawieszona - terminal state - zakończenie rozmowy i powrót do OFF_HOOK
    CONNECTING,
    CONNECTED,
    ON_HOLD
}

enum Trigger {
    CALL_DIALED,
    HUNG_UP,
    CALL_CONNECTED,
    PLACED_ON_HOLD,
    TAKEN_OFF_HOLD,
    LEFT_MESSAGE,
    STOP_USING_PHONE
}

class Demo {
    private static Map<State, List<Pair<Trigger, State>>> rules = new LinkedHashMap<>();
    static {
        rules.put(State.OFF_HOOK, List.of(
           new ImmutablePair<>(Trigger.CALL_DIALED, State.CONNECTING),
           new ImmutablePair<>(Trigger.STOP_USING_PHONE, State.ON_HOOK)
        ));
        rules.put(State.CONNECTING, List.of(
            new ImmutablePair<>(Trigger.HUNG_UP, State.OFF_HOOK),
            new ImmutablePair<>(Trigger.CALL_CONNECTED, State.CONNECTED)
        ));
        rules.put(State.CONNECTED, List.of(
            new ImmutablePair<>(Trigger.LEFT_MESSAGE, State.OFF_HOOK),
            new ImmutablePair<>(Trigger.HUNG_UP, State.OFF_HOOK),
            new ImmutablePair<>(Trigger.PLACED_ON_HOLD, State.ON_HOLD)
        ));
        rules.put(State.ON_HOLD, List.of(
            new ImmutablePair<>(Trigger.TAKEN_OFF_HOLD, State.CONNECTING),
            new ImmutablePair<>(Trigger.HUNG_UP, State.OFF_HOOK)
        ));
    }

    private static State currentState = State.OFF_HOOK;
    private static State exitState = State.ON_HOOK;

    public static void main(String[] args) {
        BufferedReader console =  new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.println("The phone is currently " + currentState);
            System.out.println("Select a trigger:");
            for (int i = 0; i < rules.get(currentState).size(); i++) {
                Trigger trigger = rules.get(currentState).get(i).getLeft();
                System.out.println("" + i + ". " + trigger);
            }
            boolean parsedSuccessfully = true;
            int choice = 0;

            do {
                try {
                    System.out.println("Please enter your choice:");
                    choice = Integer.parseInt(console.readLine());
                    parsedSuccessfully = choice >= 0 && choice < rules.get(currentState).size();
                } catch (IOException e) {
                    e.printStackTrace();
                    parsedSuccessfully = false;
                }
            } while (!parsedSuccessfully);
            currentState = rules.get(currentState).get(choice).getRight();
            if (currentState == exitState) break;
        }
        System.out.println("And we are done!");
    }
}
