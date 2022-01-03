package pulson.DesignPatterns.creational.builder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class HtmlElement {
    public String name, text;
    public List<HtmlElement> elements = new ArrayList<>();
    private final int indentSize = 2;
    private final String newLine = System.lineSeparator();

    public HtmlElement(){}

    public HtmlElement(String name, String text) {
        this.name = name;
        this.text = text;
    }

    private String toStringImpl(int indent) {
        StringBuilder sb = new StringBuilder();
        String i = String.join("", Collections.nCopies(indent * indentSize, " "));
        sb.append(String.format("%s<%s>%s", i, name, newLine));
        if (text != null && !text.isEmpty())
        {
            sb.append(String.join("", Collections.nCopies(indentSize*(indent+1), " ")))
                    .append(text)
                    .append(newLine);
        }

        for (HtmlElement e : elements)
            sb.append(e.toStringImpl(indent + 1));

        sb.append(String.format("%s</%s>%s", i, name, newLine));
        return sb.toString();
    }

    @Override
    public String toString() {
        return toStringImpl(0);
    }
}



class HtmlBuilder {
    private String rootName;
    private HtmlElement root = new HtmlElement();

    public HtmlBuilder(String rootName) {
        root.name = rootName;
        this.rootName = rootName;
    }

    public HtmlBuilder addChild(String childName, String childText) {
        HtmlElement e = new HtmlElement(childName, childText);
        root.elements.add(e);
        return this;//fluent interface impl
    }

    public void clear() {
        root = new HtmlElement();
        root.name = rootName;
    }

    @Override
    public String toString() {
        return root.toString();
    }
}



public class Builder {
    public static void main(String[] args) {

        String hello = "hello";
        System.out.println("<p>" + hello + "</p>"); //<p>hello</p>


        String [] words = {"hello", "world"};
        StringBuilder sb = new StringBuilder();
        sb.append("<ul>\n");
        for (String word : words) {
            sb.append(String.format("  <li>%s</li>\n", word));
        }
        sb.append("</ul>");
        System.out.println(sb);
        //<ul>
        //  <li>hello</li>
        //  <li>world</li>
        //</ul>



        HtmlBuilder builder = new HtmlBuilder("ul");
        builder.addChild("li", "hello");
        builder.addChild("li", "hello");
        System.out.println(builder);
        //<ul>
        //  <li>
        //    hello
        //  </li>
        //  <li>
        //    hello
        //  </li>
        //</ul>



        StringBuilder sb2 = new StringBuilder();
        sb2.append("ok").append("let's").append("go");
        //public StringBuilder append(String str)
        //fluent interface - umożliwia tworzenie długich łańcuchów tworzących obiekty

        HtmlBuilder builder1 = new HtmlBuilder("ul");
        builder1.addChild("li", "hello").addChild("li", "hello");
    }
}
