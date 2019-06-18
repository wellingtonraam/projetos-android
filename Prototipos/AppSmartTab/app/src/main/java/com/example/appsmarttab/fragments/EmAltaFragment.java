package com.example.appsmarttab.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.appsmarttab.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class EmAltaFragment extends Fragment {


    public EmAltaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_em_alta, container, false);
    }

}
