package com.lachlan.decider;

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

public class InputFragment extends Fragment implements View.OnClickListener {

    private RecyclerViewAdapter mAdapter;
    private List<String> myDataset;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_input, container, false);

        RecyclerView decidingList = (RecyclerView) rootView.findViewById(R.id.deciding_list);

        // use a linear layout manager
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        decidingList.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        myDataset = new ArrayList<>();
        myDataset.add("test 1");
        myDataset.add("test 2");
        myDataset.add("test 3");
        myDataset.add("test 4");
        mAdapter = new RecyclerViewAdapter(myDataset);
        decidingList.setAdapter(mAdapter);
        decidingList.setItemAnimator(new DefaultItemAnimator());

        SwipeDismissRecyclerViewTouchListener touchListener = new SwipeDismissRecyclerViewTouchListener(decidingList,
                new SwipeDismissRecyclerViewTouchListener.DismissCallbacks() {
                    @Override
                    public boolean canDismiss(int position) {
                        return true;
                    }

                    @Override
                    public void onDismiss(RecyclerView recyclerView, int[] reverseSortedPositions) {
                        for (int position : reverseSortedPositions) {
                            mAdapter.remove(position);
                        }
                    }
                });

        decidingList.setOnTouchListener(touchListener);

        decidingList.setOnScrollListener(touchListener.makeScrollListener());

        Button add = (Button) rootView.findViewById(R.id.add_button);
        add.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add_button: {
                mAdapter.add("new item");
                break;
            }
        }
    }
}
