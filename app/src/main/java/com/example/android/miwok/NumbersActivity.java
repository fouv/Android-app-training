package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

    private MediaPlayer mMediaPlayer;

    // set global variable for audioManager
    private AudioManager myAudioManager;

    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener =
            new AudioManager.OnAudioFocusChangeListener()    {

            public void onAudioFocusChange (int focusChange){
                 if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || //audio Focus lost
                    focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK){ //our app can play word at lower volume
                     mMediaPlayer.pause();
                     mMediaPlayer.seekTo(0);//0 because the word start at the beginning
                    } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) { //our app regained focus and can resume playback
                    mMediaPlayer.start();
                     } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                    releaseMediaPlayer();

        }
    }
    };

    //set a variable for OnCompletionListener
    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        // Initialize the audio service
        myAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        //create an Arraylist of Words objects
        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("One", "lutti", R.drawable.number_one, R.raw.number_one));
        words.add(new Word("Two", "ottiko", R.drawable.number_two, R.raw.number_two));
        words.add(new Word("three", "tolookosu", R.drawable.number_three, R.raw.number_three));
        words.add(new Word("four", "oyyisa", R.drawable.number_four, R.raw.number_four));
        words.add(new Word("five", "massokka", R.drawable.number_five, R.raw.number_five));
        words.add(new Word("six", "temmokka", R.drawable.number_six, R.raw.number_six));
        words.add(new Word("seven", "kenekaku", R.drawable.number_seven, R.raw.number_seven));
        words.add(new Word("eight", "kawinta", R.drawable.number_eight, R.raw.number_eight));
        words.add(new Word("nine", "wo’e", R.drawable.number_nine, R.raw.number_nine));
        words.add(new Word("ten", "na’aacha", R.drawable.number_ten, R.raw.number_ten));

        // Create an {@link WordAdapter}, whose data source is a list of {@link Word}s. The
        // adapter knows how to create list items for each item in the list.
        WordAdapter adapter = new WordAdapter(this, words, R.color.category_numbers);

        //  Get a reference to the ListView and attach the adapter to the listView (word_list.xml
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);

        //set an ItemClicklistener
        listView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Log.v("position", String.valueOf(position));
                //Toast.makeText(NumbersActivity.this, "list item clicked",Toast.LENGTH_SHORT).show();

                // set a variable of the word position
                Word word = words.get(position);

                // Release the mediaPlayer to initialize sonds and avoid errors if user click on the same time on different items.
                releaseMediaPlayer();

                // Request audio Focus
                int result = myAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                        AudioManager.STREAM_MUSIC,
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED){

                    // associate variable word with MiwokSong
                    mMediaPlayer = MediaPlayer.create(NumbersActivity.this, word.getMiwokSong());

                    //Start playing MiwokSong
                    mMediaPlayer.start();

                    // set OnCompletionListener on MediaPlayer
                    mMediaPlayer.setOnCompletionListener(mCompletionListener);
                }

            }
        });
    }

    /**
     * release MediaPlayer if user leaves app.
     */
    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();

    }

    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaPlayer.release();

           //Abandon audio focus when playback is complete
            myAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }
}


