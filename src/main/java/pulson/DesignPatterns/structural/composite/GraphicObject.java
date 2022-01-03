package pulson.DesignPatterns.structural.composite;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GraphicObject {
    protected String name = "Group";
    public String color;
    public List<GraphicObject> children = new ArrayList<>();

    public GraphicObject(String color) {
        this.color = color;
    }

    private void print(StringBuilder stringBuilder, int depth)
    {
        stringBuilder.append(String.join("", Collections.nCopies(depth, "*")))
                .append(depth > 0 ? " " : "")
                .append((color == null || color.isEmpty()) ? "" : color + " ")
                .append(getName())
                .append(System.lineSeparator());
        for (GraphicObject child : children)
            child.print(stringBuilder,  depth+1);
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        print(sb, 0);
        return sb.toString();
    }
}

class Circle extends GraphicObject {
    public Circle(String color) {
        this.name = "Circle";
        this.color = color;
    }
}
class Square extends GraphicObject {
    public Square(String color) {
        this.name = "Square";
        this.color = color;
    }
}


class Demo {
    public static void main(String[] args) {
        GraphicObject drawing = new GraphicObject();
        drawing.setName("My Drawing");
        drawing.children.add(new Square("Red"));
        drawing.children.add(new Circle("Yellow"));

        GraphicObject group = new GraphicObject();
        group.children.add(new Square("Blue"));
        group.children.add(new Circle("Blue"));

        drawing.children.add(group);

        System.out.println(drawing);
    }
}
