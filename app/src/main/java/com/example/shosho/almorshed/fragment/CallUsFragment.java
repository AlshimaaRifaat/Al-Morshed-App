package com.example.shosho.almorshed.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shosho.almorshed.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CallUsFragment extends Fragment {


    public CallUsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate( R.layout.fragment_call_us, container, false );
    }

}
