package edu.uw.viewpager;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

// import edu.uw.fragmentdemo.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {
    private OnSearchListener callback;

    interface OnSearchListener {
        void onSearchSubmitted(String searchTerm);
    }

    public static SearchFragment newInstance() {
        
        Bundle args = new Bundle();
        
        SearchFragment fragment = new SearchFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_search, container, false);

        Button button = rootView.findViewById(R.id.btn_search);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText text = (EditText) rootView.findViewById(R.id.txt_search);
                String searchTerm = text.getText().toString();
                callback.onSearchSubmitted(searchTerm);
            }
        });
        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            callback = (OnSearchListener) context;
        } catch (ClassCastException cce) {
            throw new ClassCastException(context.toString() + " must implement OnSearchListener");
        }
    }
}
