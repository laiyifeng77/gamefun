package com.pizidea.coolplay.activity;

import java.util.Locale;


import android.app.ActionBar;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.pizidea.coolplay.R;
import com.pizidea.coolplay.fragment.TimelineFragment;
import com.pizidea.coolplay.fragment.TimelineFragment2;
import com.pizidea.coolplay.fragment.TimelineFragment3;
import com.pizidea.coolplay.widget.drawer.DrawerLayoutInstaller;
import com.pizidea.coolplay.widget.tabbarview.TabBarView;

public class CoolActivity extends AppCompatActivity {

    private TabBarView tabBarView;
    private ListView mDrawerList;
    private Toolbar mToolbar;


    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a {@link FragmentPagerAdapter}
     * derivative, which will keep every loaded fragment in memory. If this
     * becomes too memory intensive, it may be best to switch to a
     * {@link android.support.v13.app.FragmentStatePagerAdapter}.
     */
    SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link android.support.v4.view.ViewPager} that will host the section contents.
     */
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cool);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("趣活动");

        LayoutInflater inflator = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View v = inflator.inflate(R.layout.custom_ab, null);
        tabBarView = (TabBarView) findViewById(R.id.tab_bar);

        setSupportActionBar(mToolbar);

//        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
//        getSupportActionBar().setCustomView(v);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        tabBarView.setViewPager(mViewPager);

        ViewGroup.LayoutParams p = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.MATCH_PARENT);

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    //此处相当于布局文件中的Android:layout_gravity属性
        lp.gravity = Gravity.RIGHT;

        mToolbar.addView(v, lp);



        DrawerLayoutInstaller.from(this)
                .drawerRoot(0)
                .drawerLeftView(makeLeftView())
                .drawerLeftWidth(300)
                .withNavigationIconToggler(mToolbar)
                .drawerListener(new DrawerLayout.SimpleDrawerListener() {
                })
                .build();


    }

//    private Toolbar getToolbar(){
//        mToolbar = (Toolbar) findViewById(R.id.toolbar);
//        return mToolbar;
//    }

    private View makeLeftView(){

        NavigationView navigationView = new NavigationView(this);
        navigationView.setClickable(false);
        navigationView.inflateHeaderView(R.layout.drawer_header);
        navigationView.inflateMenu(R.menu.drawer_menu);

        return navigationView;



        /*mDrawerList = new ListView(this);

        mDrawerList.setBackgroundColor(getResources().getColor(R.color.text_btn_nor));

        final String[] menuDatas = new String[]{"123123","234234"};

        // Set the adapter for the list view
        mDrawerList.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, menuDatas));
        // Set the list's click listener
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id)
            {
                // Highlight the selected item, update the title, and close the
                // drawer
                mDrawerList.setItemChecked(position, true);
                //setTitle(menuDatas[position]);
                Toast.makeText(CoolActivity.this,menuDatas[position],Toast.LENGTH_SHORT).show();

                //mDrawerLayout.closeDrawer(mDrawerList);
            }
        });

        return mDrawerList;*/
    }

    private void initListView()
    {
        //mDrawerList = (ListView) findViewById(R.id.left_drawer);

        final String[] menuDatas = new String[]{"123123","234234"};

        // Set the adapter for the list view
        mDrawerList.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, menuDatas));
        // Set the list's click listener
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id)
            {
                // Highlight the selected item, update the title, and close the
                // drawer
                mDrawerList.setItemChecked(position, true);
                setTitle(menuDatas[position]);
                //mDrawerLayout.closeDrawer(mDrawerList);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_cool, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter implements TabBarView.IconTabProvider {

        private int[] tab_icons={R.drawable.ic_tab1,
                R.drawable.ic_tab2,
                R.drawable.ic_tab3,
        };


        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class
            // below).
            if(position == 0){
                return new TimelineFragment3();
            }
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return tab_icons.length;
        }

        @Override
        public int getPageIconResId(int position) {
            return tab_icons[position];
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return getString(R.string.title_section1).toUpperCase(l);
                case 1:
                    return getString(R.string.title_section2).toUpperCase(l);
                case 2:
                    return getString(R.string.title_section3).toUpperCase(l);
            }
            return null;
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container,
                    false);
            TextView textView = (TextView) rootView
                    .findViewById(R.id.section_label);
            textView.setText(Integer.toString(getArguments().getInt(
                    ARG_SECTION_NUMBER)));
            return rootView;
        }
    }

}
