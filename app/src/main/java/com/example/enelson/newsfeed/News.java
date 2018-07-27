package com.example.enelson.newsfeed;

import java.util.Date;


/**
 * {@Event} represents a news story for the Guardian API.  It holds the details
 * of the event.
 */

public class News {

    /** Title of the news story */
    private String mTitle;

    /** Section of the news story */
    private String mSection;

    /** Date news story published */
    private String mArticleDate;

    /** Website URL of the story */
    private String mURL;

    /**
     * Constructs a new {@link News}.
     *
     * @param storyTitle is the title of the story
     * @param storySection is the section of the story
     * @param storyDate is the date of the story
     * @param url is the url of the story
     */

    public News(String storyTitle, String storySection, String storyDate, String url){

        mTitle = storyTitle;
        mSection = storySection;
        mArticleDate = storyDate;
        mURL = url;
    }

    /**
     * Returns title of news story
     */

    public String getTitle() {
        return mTitle;
    }
    /**
     * Returns section of news story
     */

    public String getSection() {
        return mSection;
    }
    /**
     * Returns author of news story
     */

    public String getArticleDate() {
        return mArticleDate;
    }

    /**
     * Returns url of news story
     */

    public String getUrl() {
        return mURL;
    }
}
