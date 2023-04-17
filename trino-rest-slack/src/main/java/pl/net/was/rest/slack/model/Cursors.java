package pl.net.was.rest.slack.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Cursors {
    private final String previous;
    private final String next;
    private final String current;


    public Cursors(@JsonProperty("previous") String previous,
                   @JsonProperty("current") String current,
                   @JsonProperty("next") String next)
    {
        this.previous = previous;
        this.next = next;
        this.current = current;
    }

    public String getNextCursor() {
        return next;
    }
    public String getPreviousCursor() {
        return previous;
    }

    public String getCurrentCursor() {
        return current;
    }
}
