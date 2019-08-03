package com.coinmarket.info.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.coinmarket.R;

public class GraphFragment extends Fragment {

    public static GraphFragment newInstance() {
        GraphFragment fragment = new GraphFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_graph, container, false);
        TextView valueRank = view.findViewById(R.id.valueRank);
        TextView valueMarketCap = view.findViewById(R.id.valueMarketCap);
        TextView valueSupply = view.findViewById(R.id.valueSupply);
        TextView valueAmount = view.findViewById(R.id.valueAmount);

        Integer rank = bundle.getInt("rank");
        Double marketCap = bundle.getDouble("market_cap");
        Double supply = bundle.getDouble("supply");
        Double amount = bundle.getDouble("amount");

        valueRank.setText(rank.toString());
        valueMarketCap.setText(marketCap.toString());
        valueSupply.setText(supply.toString());
        valueAmount.setText(amount.toString());
        return view;
    }
}
