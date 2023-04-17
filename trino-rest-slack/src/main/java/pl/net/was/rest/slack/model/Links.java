package pl.net.was.rest.slack.model;

public class Links {

    private final String next;
    private final String previous;
    private final String current;

    public Links(String next, String previous, String current) {
        this.next = next;
        this.previous = previous;
        this.current = current;
    }

    public String getNext() {
        return next;
    }

    public String getPrevious() {
        return previous;
    }

    public String getCurrent() {
        return current;
    }
}
