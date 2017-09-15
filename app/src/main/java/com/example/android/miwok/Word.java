package com.example.android.miwok;

/**
 * {@link Word} represents a vocabulary word that the user wants to learn.
 * It contains a default translation and a Miwok translation for that word.
 */

public class Word {

    // Name of the default word
    private String mDefaultTranslation;

    // Name of the miwok word
    private String mMiwokTranslation;

    // name of the new image - initialize first with the constant NO_IMAGE_PROVIDED
    private int mImageResourceId = NO_IMAGE_PROVIDED;

    /**
     * creation of a constant NO_IMAGE_PROVIDED if no image in Word
     */
    private static final int NO_IMAGE_PROVIDED = -1;

    /**
     * name of the miwok song
     */
    private int mMiwokSong ;



    // Create a new Word object.

    //construct of a new word for Phrase category

    /**
     *
     * @param defaultTranslation
     * @param miwokTranslation
     * @param miwokSong
     */
    public Word(String defaultTranslation, String miwokTranslation, int miwokSong){
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        mMiwokSong = miwokSong;
    }

    //construct of a new word for orther categories

    /**
     *
     * @param defaultTranslation
     * @param miwokTranslation
     * @param imageResourdeId
     * @param miwokSong
     */
    public Word(String defaultTranslation, String miwokTranslation, int imageResourdeId, int miwokSong){
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        mImageResourceId = imageResourdeId;
        mMiwokSong = miwokSong;
    }

       // get the default word
    public String getDefaultTranslation(){

        return mDefaultTranslation;
    }

    // get the miwok word
    public String getMiwokTranslation(){
        return mMiwokTranslation;
    }


    //get the imageResourceId
    public int getImageResourceId(){
        return mImageResourceId;
        }

    /**
     *
     * @return or not there is an image for this word
     */
    public boolean hasImage(){
        return mImageResourceId!=NO_IMAGE_PROVIDED;
    }

    /**
     * get the mMiwoksong
     */
     public int getMiwokSong(){ return mMiwokSong;}

    @Override
    public String toString() {
        return "Word{" +
                "mDefaultTranslation='" + mDefaultTranslation + '\'' +
                ", mMiwokTranslation='" + mMiwokTranslation + '\'' +
                ", mImageResourceId=" + mImageResourceId +
                ", mMiwokSong=" + mMiwokSong +
                '}';
    }
}
