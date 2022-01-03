package pulson.DesignPatterns.structural.flyweight.flyweight_guru;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@AllArgsConstructor
public class Tree {
    private int x;
    private int y;
    private TreeType type;
}
@Getter
@AllArgsConstructor
class TreeType {//Zawiera dane współdzielone przez wiele drzew
    //Ich nazwa i kolor będą się często powtarzać w lesie złożonym z miliona drzew
    private String name;
    private Color color;
    private String otherTreeData;
    static Map<String, TreeType> treeNameToTreeTypes = new HashMap<>();
    //Cache przechowująca obiekt TreeType i nazwę drzewa. Bez niej tworzylibyśmy tyle obiektów TreeType ile obiektów Tree
    public static TreeType getTreeType(String name, Color color, String otherTreeData) {
        TreeType treeType = treeNameToTreeTypes.get(name);
        if (treeType == null) {
            treeType = new TreeType(name, color, otherTreeData);
            treeNameToTreeTypes.put(name, treeType);
        }
        return treeType;
    }

}

class Forest extends JFrame {
    private List<Tree> trees = new ArrayList<>();

    public void plantTree(int x, int y, String name, Color color, String otherTreeData) {
        TreeType type = TreeType.getTreeType(name, color, otherTreeData);
        Tree tree = new Tree(x, y, type);
        trees.add(tree);
    }

    @Override
    public void paint(Graphics graphics) {
        for (Tree tree : trees) {
            //graphics.setColor(Color.BLACK);
            //graphics.fillRect(tree.getX() - 1, tree.getY(), 3, 5);
            graphics.setColor(tree.getType().getColor());
            graphics.fillOval(tree.getX() - 5, tree.getY() - 10, 10, 10);
        }
    }
}

class Test {
    static int CANVAS_SIZE_PX = 700;
    static int TREES_TO_DRAW = 5_000_000;
    static int TREE_TYPES = 3;

    public static void main(String[] args) {
        Forest forest = new Forest();
        for (int i = 0; i < Math.floor(TREES_TO_DRAW / TREE_TYPES); i++) {
            forest.plantTree(random(0, CANVAS_SIZE_PX), random(0, CANVAS_SIZE_PX),
                    "Summer Oak", Color.GREEN, "Oak texture stub");
            forest.plantTree(random(0, CANVAS_SIZE_PX), random(0, CANVAS_SIZE_PX),
                    "Autumn Oak", Color.ORANGE, "Autumn Oak texture stub");
            forest.plantTree(random(0, CANVAS_SIZE_PX), random(0, CANVAS_SIZE_PX),
                    "Red tree", Color.RED, "Red tree texture stub");
        }
        forest.setSize(CANVAS_SIZE_PX, CANVAS_SIZE_PX);
        forest.setVisible(true);

        System.out.println(TREES_TO_DRAW + " trees drawn");
        System.out.println("---------------------");
        System.out.println("Memory usage:");
        System.out.println("Tree size (8 bytes) * " + TREES_TO_DRAW);
        System.out.println("+ TreeTypes size (~30 bytes) * " + TREE_TYPES + "");
        System.out.println("---------------------");
        System.out.println("Total: " + ((TREES_TO_DRAW * 8 + TREE_TYPES * 30) / 1024 / 1024) +
                "MB (instead of " + ((TREES_TO_DRAW * 38) / 1024 / 1024) + "MB)");
        //1000000 trees drawn
        //---------------------
        //Memory usage:
        //Tree size (8 bytes) * 1000000
        //+ TreeTypes size (~30 bytes) * 3
        //---------------------
        //Total: 7MB (instead of 36MB)
    }
    private static int random(int min, int max) {
        return min + (int) (Math.random() * ((max - min) + 1));
    }
}
