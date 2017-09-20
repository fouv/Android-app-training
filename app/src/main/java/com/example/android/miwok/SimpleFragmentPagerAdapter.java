package com.example.android.miwok;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * SimpleFragmentPagerAdapter
 *provide the layout for each lists
 */

public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter{

    private Context mcontext;

    /** Create a new SimpleFragmentPageAdapater object.
     *
     * @param context
     * @param fm
     */


    public SimpleFragmentPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mcontext = context;
    }
/** return the Fragment that should be displayed */

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new NumbersFragment();
        } else if (position == 1){
            return new ColorsFragment();
        } else if (position == 2){
            return new FamilyFragment();
        } else {
            return new PhrasesFragment();
        }
    }
    //return the title of the Tab for each position

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        if (position == 0) {
            return mcontext.getString(R.string.category_numbers);
        } else if (position == 1) {
            return mcontext.getString(R.string.category_colors);
        } else if (position == 2) {
            return mcontext.getString(R.string.category_family);
        } else {
            return mcontext.getString(R.string.category_phrases);
        }
    }

    @Override
    public int getCount() {
        return 4;
    }
}
