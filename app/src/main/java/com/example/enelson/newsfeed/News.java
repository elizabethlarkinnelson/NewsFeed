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

    /** Author name of the news story */
    private String mAuthorName;

    /** Date news story published */
    private Date mArticleDate;

    /** Website URL of the story */
    private String mURL;

    /**
     * Constructs a new {@link News}.
     *
     * @param storyTitle is the title of the story
     * @param storySection is the section of the story
     * @param storyAuthor is the author of the story
     * @param storyDate is the date of the story
     * @param url is the url of the story
     */

    public News(String storyTitle, String storySection, String storyAuthor, Date storyDate, String url){

        mTitle = storyTitle;
        mSection = storySection;
        mAuthorName = storyAuthor;
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

    public String getAuthorName() {
        return mAuthorName;
    }
    /**
     * Returns date of news story
     */

    public Date getArticleDate() {
        return mArticleDate;
    }

    /**
     * Returns url of news story
     */

    public String getURL() {
        return mURL;
    }
}
