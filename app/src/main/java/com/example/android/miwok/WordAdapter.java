package com.example.android.miwok;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * {@link WordAdapter} is an {@link ArrayAdapter} that can provide the layout for each list based on a data source, which is a list of {@link Word} objects.
 */

public class WordAdapter extends ArrayAdapter<Word> {
    /**
     * Creation of a private variable background color
     */
    private int mColorResourceId;

    private static final String LOG_TAG = WordAdapter.class.getSimpleName();

    /**
     * This is our own custom constructor
     * The context is used to inflate the layout file, and the list is the data we want to populate into the lists.
     *
     * @param context the current context. used to inflate the layout file.
     * @param words   A list of Word objects to display in the list.
     * @param colorResourceId  Background color of the Word objects.
     */

    public WordAdapter(Activity context, ArrayList<Word> words, int colorResourceId) {
        /** Here we initialize the Array Adapter's internal storage for the context and the list.
         * The second argument is used when the ArrayAdapter is populating a single TextView
         * Because this is a custom adapter for two TextViews, the adapter is not going to use
         * this second argument, so it can be any value. Here, we used 0.
         */
        super(context, 0, words);
        mColorResourceId = colorResourceId;
    }

    /**
     * Provides a view for an Adapter View(ListView, GridView, etc.)
     *
     * @param position    The position in the list of data that should be displayed in the list item view
     * @param convertView The recycled view to populate
     * @param parent      The parent ViewGroup that is used for inflation.
     * @return The View for the position in the AdapterView.
     */
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // check if existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }
        // Get the {@link Word} object located at this position in the list
        Word currentWord = getItem(position);

        // Find the TextView in the list_item.xml layout with ID default_text_view
        TextView defaultTextView = (TextView) listItemView.findViewById(R.id.default_text_view);
        // Get the default_text_view from the current Word object and set this text on the default TextView
        defaultTextView.setText(currentWord.getDefaultTranslation());

        //Find the TextView in the list_item.xml layout with ID Mixok_text_view
        TextView miwokTextView = (TextView) listItemView.findViewById(R.id.miwok_text_view);
        // Get the Miwok_text_view from the current Word object and set this text on the miwok TextView
        miwokTextView.setText(currentWord.getMiwokTranslation());

        // Find the ImageView in the list_item.xml layout with the ID list_item_image
        ImageView imageView = (ImageView) listItemView.findViewById(R.id.image);

        // Check if the image resource ID from the current Word object is present
        //if true return the imageView with VISIBLE and if false setVISIBILITY Gone.
        if (currentWord.hasImage()) {

            // return the whole list item layout (containing 2 TextView) shown in the ListView
            imageView.setImageResource(currentWord.getImageResourceId());
            imageView.setVisibility((View.VISIBLE));
        } else {
            //GONE hide the ImageView completely
            imageView.setVisibility(View.GONE);
        }


        //set the background color for the list item
        View textContainer = listItemView.findViewById(R.id.text_container);

        // find the color
        int color = ContextCompat.getColor(getContext(), mColorResourceId);

        //set the background color
        textContainer.setBackgroundColor(color);

        // return the complete listItemView
        return listItemView;
    }

}
