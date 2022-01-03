package pulson.DesignPatterns.behavioral.command;

import com.google.common.collect.Lists;
import lombok.ToString;

import java.util.Arrays;
import java.util.List;

@ToString
public class BankAccount {
    private int balance;
    private final int overdraftLimit = -500;

    public void deposit(int amount) {
        balance += amount;
        System.out.println("Deposited " + amount + " , balance is now " + balance);
    }

    public boolean withdraw(int amount) {
        if(balance - amount >= overdraftLimit) {
            balance -= amount;
            System.out.println("Withdrew " + amount + " , balance is now " + balance);
            return true;
        }
        return false;
    }
}

interface Command {
    void call();
    void undo();
}

class BankAccountCommand implements Command {
    private BankAccount account;
    private boolean succeeded;
    public enum Action {
        DEPOSIT, WITHDRAW
    }
    private Action action;
    private int amount;

    public BankAccountCommand(BankAccount account, Action action, int amount) {
        this.account = account;
        this.action = action;
        this.amount = amount;
    }

    @Override
    public void call() {
        switch (action) {
            case DEPOSIT:
                succeeded = true;
                account.deposit(amount);
                break;
            case WITHDRAW:
                succeeded = account.withdraw(amount);
                break;
        }
    }

    @Override
    public void undo() {
        if(!succeeded) return;
        switch (action) {
            case DEPOSIT:
                account.withdraw(amount);
                break;
            case WITHDRAW:
                account.deposit(amount);
                break;
        }
    }
}

class Test {
    public static void main(String[] args) {
        BankAccount bankAccount = new BankAccount();
        List<BankAccountCommand> commands = Arrays.asList(new BankAccountCommand(bankAccount, BankAccountCommand.Action.DEPOSIT, 100),
                new BankAccountCommand(bankAccount, BankAccountCommand.Action.WITHDRAW, 1000)
                );
        commands.forEach(command -> {
            command.call();
            System.out.println(bankAccount);
        });

        Lists.reverse(commands).forEach(command -> {
            command.undo();
            System.out.println(bankAccount);
        });
    }
}
