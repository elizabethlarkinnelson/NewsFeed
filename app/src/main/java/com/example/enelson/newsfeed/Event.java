package com.example.enelson.newsfeed;

/**
 * {@Event} represents a news story for the Guardian API.  It holds the details
 * of the event.
 */

public class Event {

    /** Title of the news story */
    public final String title;

    /**
     * Constructs a new {@link Event}.
     *
     * @param storyTitle is the title of the story
     */

    public Event(String storyTitle){
        title = storyTitle;
    }
}
