package com.example.android.miwok;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        //create an Arraylist of Words objects
        ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("red", "weṭeṭṭi", R.drawable.color_black));
        words.add(new Word("green", "chokokki", R.drawable.color_brown));
        words.add(new Word("brown", "ṭakaakki", R.drawable.color_dusty_yellow));
        words.add(new Word("gray", "ṭopoppi", R.drawable.color_gray));
        words.add(new Word("black", "kululli", R.drawable.color_green));
        words.add(new Word("white", "kelelli", R.drawable.color_mustard_yellow));
        words.add(new Word("dusty yellow", "ṭopiisә", R.drawable.color_white));
        words.add(new Word("mustard yellow", "chiwiiṭә", R.drawable.color_red));

        // Create an {@link WordAdapter}, whose data source is a list of {@link Word}s. The
        // adapter knows how to create list items for each item in the list.
        WordAdapter adapter = new WordAdapter(this, words, R.color.category_colors);

        //  Get a reference to the ListView and attach the adapter to the listView (word_list.xml)
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);


    }
}

