package com.lachlan.decider;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class InputFragment extends Fragment implements View.OnClickListener{

    RecyclerView decidingList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_input, container, false);

        decidingList = (RecyclerView) rootView.findViewById(R.id.deciding_list);

        // use a linear layout manager
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        decidingList.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        List<String> myDataset = new ArrayList<>();
        myDataset.add("test 1");
        myDataset.add("test 2");
        myDataset.add("test 3");
        myDataset.add("test 4");
        RecyclerViewAdapter mAdapter = new RecyclerViewAdapter(myDataset);
        decidingList.setAdapter(mAdapter);
        decidingList.setItemAnimator(new DefaultItemAnimator());

        Button add = (Button) rootView.findViewById(R.id.add_button);
        add.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.add_button:
            {
                ((RecyclerViewAdapter)decidingList.getAdapter()).add("new item");
                break;
            }
        }

    }
}
