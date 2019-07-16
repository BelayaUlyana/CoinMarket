package com.coinmarket.info.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.coinmarket.R;

public class SignalFragment extends Fragment {

    public interface SignalListener {
        void add();
    }

    private SignalListener listener;
    private Button button;

    public static SignalFragment newInstance() {
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
        View view = inflater.inflate(R.layout.fragment_signal, container, false);
        button = view.findViewById(R.id.btnAdd);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.add();
            }
        });
        return view;
    }

    public void changeText(String s) {
        button.setText(s);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof SignalListener) {
            listener = (SignalListener) context;
        } else {
            throw new RuntimeException("text");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}
