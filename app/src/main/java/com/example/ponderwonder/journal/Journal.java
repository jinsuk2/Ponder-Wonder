package com.example.ponderwonder.journal;


import android.net.Uri;

import java.util.Date;

/**
 * This class represents the daily journal in the trip.
 */
public class Journal  {

    /** Represents the thumbnail of the journal. */
    private String journalImgUrl;

    /** Represents the title of the journal. */
    private String journalTitle;

    //TODO: Implement calendar date picker
    /** Represents the date of the journal. */
    private Date JournalDate;

    /** Represents the content of the journal.*/
    private String journalContent;

    /**
     * Constructor.
     * @param imgUrl image url of the journal thumbnail.
     * @param title title of the journal.
     * @param content content of the journal.
     */
    public Journal(String imgUrl, String title, String content) {
        this.journalImgUrl = imgUrl;
        this.journalTitle = title;
        this.journalContent = content;
    }


    public String getJournalImgUrl() {
        return journalImgUrl;
    }

    public void setJournalImgUrl(String journalImgUrl) {
        this.journalImgUrl = journalImgUrl;
    }

    /**
     * Getter for journal title.
     * @return journal title
     */
    public String getJournalTitle(){
        return this.journalTitle;
    }

    /**
     * Setter for journal title.
     * @param title journal title
     */
    public void setJournalTitle(String title){
        this.journalTitle = title;
    }

    /**
     * Getter for journal date.
     * @return journal date
     */
    public Date getJournalDate() {
        return JournalDate;
    }

    /**
     * Setter for journal date.
     * @param journalDate journal date
     */
    public void setJournalDate(Date journalDate) {
        JournalDate = journalDate;
    }

    /**
     * Getter for journal content.
     * @return journal content
     */
    public String getJournalContent(){
        return this.journalContent;
    }

    /**
     * Setter for journal content.
     * @param content journal content
     */
    public void setJournalContent(String content){
        this.journalContent = content;
    }





}
