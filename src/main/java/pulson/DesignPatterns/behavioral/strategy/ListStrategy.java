package pulson.DesignPatterns.behavioral.strategy;

import java.util.List;
import java.util.function.Supplier;

enum OutputFormat { MARKDOWN, HTML}

interface ListStrategy {
    default void start(StringBuilder sb) {}
    void addListItem(StringBuilder sb, String item);
    default void end(StringBuilder sb) {}
}

class MarkdownListStrategy implements ListStrategy {
    @Override
    public void addListItem(StringBuilder sb, String item) {
        sb.append(" * ").append(item).append(System.lineSeparator());

    }
}

class HtmlListStrategy implements ListStrategy {
    @Override
    public void start(StringBuilder sb) {
        sb. append("<ul>").append(System.lineSeparator());
    }

    @Override
    public void addListItem(StringBuilder sb, String item) {
        sb.append("   <li>").append(item).append("</li>").append(System.lineSeparator());
    }

    @Override
    public void end(StringBuilder sb) {
        sb.append("</ul>").append(System.lineSeparator());
    }
}
//class TextProcessor {
//    private StringBuilder sb = new StringBuilder();
//    private ListStrategy listStrategy;
//
//    public TextProcessor(OutputFormat outputFormat) {
//        setOutputFormat(outputFormat);
//    }
//
//    public void setOutputFormat(OutputFormat outputFormat) {
//        switch (outputFormat) {
//            case MARKDOWN:
//                listStrategy = new MarkdownListStrategy();
//                break;
//            case HTML:
//                listStrategy = new HtmlListStrategy();
//                break;
//        }
//    }
class TextProcessor <LS extends ListStrategy> {
    private StringBuilder sb = new StringBuilder();
    private final LS listStrategy;

    public TextProcessor(Supplier<? extends LS> listStrategySupplier) {
        listStrategy = listStrategySupplier.get();
    }

    public void appendList(List<String> items) {
        listStrategy.start(sb);
        items.forEach(item -> {
            listStrategy.addListItem(sb, item);
        });
        listStrategy.end(sb);
    }

    public void clear() {
        sb.setLength(0);
    }

    @Override
    public String toString() {
        return sb.toString();
    }
}



class Test {
    public static void main(String[] args) {
        //        TextProcessor textProcessor = new TextProcessor(OutputFormat.MARKDOWN);
        //        textProcessor.appendList(List.of("liberte", "egalite", "fraternite"));
        //        System.out.println(textProcessor);
        //        // * liberte
        //        // * egalite
        //        // * fraternite
        //
        //        textProcessor.clear();
        //        textProcessor.setOutputFormat(OutputFormat.HTML);
        //        textProcessor.appendList(List.of("abstraction", "encapsulation", "inheritance", "polymorphism"));
        //        System.out.println(textProcessor);
        //        //<ul>
        //        //   <li>abstraction</li>
        //        //   <li>encapsulation</li>
        //        //   <li>inheritance</li>
        //        //   <li>polymorphism</li>
        //        //</ul>


        TextProcessor<MarkdownListStrategy> markdown = new TextProcessor<>(MarkdownListStrategy::new);
        markdown.appendList(List.of("alpha", "gamma", "beta"));
        System.out.println(markdown);

        TextProcessor<HtmlListStrategy> html = new TextProcessor<>(HtmlListStrategy::new);
        html.appendList(List.of("alpha", "gamma", "beta"));
        System.out.println(html);
    }
}
