package com.lachlan.decider;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import java.util.Random;

public class DecisionFragment extends Fragment {

    private List<String> decisionList;
    private String decisionText;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Initialise
        decisionList = getArguments().getStringArrayList("decisionList");
        decisionText = decisionList.get(new Random().nextInt(decisionList.size()));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_input, container, false);

        TextView decision = (TextView) view.findViewById(R.id.decision);
        decision.setText(decisionText);

        return view;
    }
}
