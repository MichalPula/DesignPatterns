package pulson.DesignPatterns.behavioral.observer.another_observer;

import java.util.ArrayList;
import java.util.List;


interface Observer {
    void update();
}


interface Observable {
    void subscribe(Observer observer);
    void unsubscribe(Observer observer);
    void notifySubscribers();
}
class PublisherManager implements Observable {
    private final List<Observer> blogSubscribers = new ArrayList<>();

    @Override
    public void subscribe(Observer observer) {
        blogSubscribers.add(observer);
    }

    @Override
    public void unsubscribe(Observer observer) {
        blogSubscribers.remove(observer);
    }

    @Override
    public void notifySubscribers() {
        blogSubscribers.forEach(Observer::update);
    }
}


public class Blog extends PublisherManager {
    private static final String publishMessage = "New article! Take a look: %s ";
    private String name;
    private final List<String> articleTitles;

    public Blog(String name) {
        articleTitles = new ArrayList<>();
    }

    public void publishArticle(String articleTitle) {
        articleTitles.add(articleTitle);
        System.out.println(String.format(publishMessage, articleTitle));
        notifySubscribers();
    }
}


class User implements Observer {
    private static final String message = "Hi %s, you have %d new articles to read";

    private final String username;
    private int newArticles;

    public User(String username) {
        this.username = username;
    }

    @Override
    public void update() {
        newArticles++;
        System.out.println(String.format(message, username, newArticles));
    }
}

class Test {
    public static void main(String[] args) {
        Blog blog = new Blog("My super blog!");

        User joe = new User("Joe");
        blog.subscribe(joe);

        blog.publishArticle("Observer Design Pattern in Java");

        User fred = new User("Fred");
        blog.subscribe(fred);

        blog.publishArticle("How to get a job as Java Junior Developer");
        blog.unsubscribe(fred);

        blog.publishArticle("Java 8 Stream API walkthrough");
        //New article! Take a look: Observer Design Pattern in Java
        //Hi Joe, you have 1 new articles to read
        //New article! Take a look: How to get a job as Java Junior Developer
        //Hi Joe, you have 2 new articles to read
        //Hi Fred, you have 1 new articles to read
        //New article! Take a look: Java 8 Stream API walkthrough
        //Hi Joe, you have 3 new articles to read
    }
}


