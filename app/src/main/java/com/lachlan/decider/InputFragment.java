package com.lachlan.decider;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class InputFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_input, container, false);

        RecyclerView decidingList = (RecyclerView) rootView.findViewById(R.id.deciding_list);

        // use a linear layout manager
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        decidingList.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        String[] myDataset = new String[]{"test 1", "test 2", "test 3"};
        RecyclerViewAdapter mAdapter = new RecyclerViewAdapter(myDataset);
        decidingList.setAdapter(mAdapter);

        return rootView;
    }
}
