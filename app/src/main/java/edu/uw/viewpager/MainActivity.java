package edu.uw.viewpager;

import android.os.Bundle;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.telecom.Call;
import android.view.View;
import android.widget.EditText;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

public class MainActivity extends AppCompatActivity implements MovieListFragment.OnMovieSelectedListener, SearchFragment.OnSearchListener {

    private static final String TAG = "MainActivity";
    public static final String MOVIE_LIST_FRAGMENT_TAG = "MoviesListFragment";
    public static final String MOVIE_DETAIL_FRAGMENT_TAG = "DetailFragment";

    private DetailFragment detailFragment;
    private MovieListFragment movieListFragment;
    private SearchFragment searchFragment;
    private ViewPager pager;
    private MoviePagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchFragment = SearchFragment.newInstance();
        pager = (ViewPager) findViewById(R.id.pager);
        adapter = new MoviePagerAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);

    }

    @Override
    public void onSearchSubmitted(String searchTerm) {
        movieListFragment = MovieListFragment.newInstance(searchTerm);
        adapter.notifyDataSetChanged();
        pager.setCurrentItem(1);
    }

    @Override
    public void onMovieSelected(Movie movie) {
        detailFragment = DetailFragment.newInstance(movie);
        adapter.notifyDataSetChanged();
        pager.setCurrentItem(2);
    }



    public class MoviePagerAdapter extends FragmentStatePagerAdapter {

        public MoviePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                return searchFragment;
            }
            if (position == 1) {
                return movieListFragment;
            }
            if (position == 2){
                return detailFragment;
            }

            return null;
        }

        @Override
        public int getCount() {
            if (detailFragment != null) {
                return 3;
            } else if (movieListFragment != null) {
                return 2;
            } else {
                return 1;
            }
        }

        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }
    }
}
