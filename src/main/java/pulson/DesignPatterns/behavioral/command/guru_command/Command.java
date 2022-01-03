package pulson.DesignPatterns.behavioral.command.guru_command;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

public abstract class Command {
    public Editorr editorr;
    private String backup;

    Command(Editorr editorr) {
        this.editorr = editorr;
    }

    void backup() {
        backup = editorr.textField.getText();
    }

    public void undo() {
        editorr.textField.setText(backup);
    }

    public abstract boolean execute();
}

class CopyCommand extends Command {
    public CopyCommand(Editorr editorr) {
        super(editorr);
    }

    @Override
    public boolean execute() {
        editorr.clipboard = editorr.textField.getSelectedText();
        return false;
    }
}
class PasteCommand extends Command {

    public PasteCommand(Editorr editorr) {
        super(editorr);
    }

    @Override
    public boolean execute() {
        if (editorr.clipboard == null || editorr.clipboard.isEmpty()) return false;

        backup();
        editorr.textField.insert(editorr.clipboard, editorr.textField.getCaretPosition());
        return true;
    }
}
class CutCommand extends Command {

    public CutCommand(Editorr editorr) {
        super(editorr);
    }

    @Override
    public boolean execute() {
        if (editorr.textField.getSelectedText().isEmpty()) return false;

        backup();
        String source = editorr.textField.getText();
        editorr.clipboard = editorr.textField.getSelectedText();
        editorr.textField.setText(cutString(source));
        return true;
    }

    private String cutString(String source) {
        String start = source.substring(0, editorr.textField.getSelectionStart());
        String end = source.substring(editorr.textField.getSelectionEnd());
        return start + end;
    }
}
class CommandHistory {
    private Stack<Command> history = new Stack<>();

    public void push(Command c) {
        history.push(c);
    }

    public Command pop() {
        return history.pop();
    }

    public boolean isEmpty() { return history.isEmpty(); }
}


class Editorr {
    public JTextArea textField;
    public String clipboard;
    private CommandHistory history = new CommandHistory();

    public void init() {
        JFrame frame = new JFrame("Text editor (type & use buttons, Luke!)");
        JPanel content = new JPanel();
        frame.setContentPane(content);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        textField = new JTextArea();
        textField.setLineWrap(true);
        content.add(textField);
        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton ctrlC = new JButton("Ctrl+C");
        JButton ctrlX = new JButton("Ctrl+X");
        JButton ctrlV = new JButton("Ctrl+V");
        JButton ctrlZ = new JButton("Ctrl+Z");
        Editorr editorr = this;
        ctrlC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                executeCommand(new CopyCommand(editorr));
            }
        });
        ctrlX.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                executeCommand(new CutCommand(editorr));
            }
        });
        ctrlV.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                executeCommand(new PasteCommand(editorr));
            }
        });
        ctrlZ.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                undo();
            }
        });
        buttons.add(ctrlC);
        buttons.add(ctrlX);
        buttons.add(ctrlV);
        buttons.add(ctrlZ);
        content.add(buttons);
        frame.setSize(450, 200);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void executeCommand(Command command) {
        if (command.execute()) {
            history.push(command);
        }
    }

    private void undo() {
        if (history.isEmpty()) return;

        Command command = history.pop();
        if (command != null) {
            command.undo();
        }
    }
}

class Demo {
    public static void main(String[] args) {
        Editorr editorr = new Editorr();
        editorr.init();
    }
}
