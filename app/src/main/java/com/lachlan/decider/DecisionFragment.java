package com.lachlan.decider;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;
import java.util.Random;

public class DecisionFragment extends Fragment {

    private List<String> decisionList;
    private String decisionText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
        // Initialise
        decisionList = getArguments().getStringArrayList(getString(R.string.decision_list_key));
        decisionText = Decision();
    }

    private String Decision() {
        return decisionList.get(new Random().nextInt(decisionList.size()));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_decision, container, false);

        final TextView decision = (TextView) view.findViewById(R.id.decision);
        decision.setText(decisionText);

        Button tryAgain = (Button) view.findViewById(R.id.try_again);
        tryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                decisionText = Decision();
                decision.setText(decisionText);
            }
        });

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        super.onCreateOptionsMenu(menu, menuInflater);

        menu.clear();
    }
}
