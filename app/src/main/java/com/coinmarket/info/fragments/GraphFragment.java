package com.coinmarket.info.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.coinmarket.R;

public class GraphFragment extends Fragment {


    public static SignalFragment newInstance(int i) {
        SignalFragment fragment = new SignalFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_graph, container, false);

        TextView rank = view.findViewById(R.id.rank);
        rank.setText("radgsfafvfd");
        rank.setBackgroundColor(Color.BLUE);
        return view;

    }
}
