package com.lachlan.decider;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.afollestad.materialdialogs.MaterialDialog;
import com.melnykov.fab.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class InputFragment extends Fragment implements View.OnClickListener {

    private RecyclerViewAdapter mAdapter;
    private List<String> myDataset;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
    }

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

        FloatingActionButton add = (FloatingActionButton) rootView.findViewById(R.id.add_button);
        add.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        super.onCreateOptionsMenu(menu, menuInflater);
        // Inflate the menu items for use in the action bar
        menuInflater.inflate(R.menu.decider_menu_actions, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_decide:
                Bundle args = new Bundle();
                args.putStringArrayList(getString(R.string.decision_list_key), mAdapter.getDataSet());
                DecisionFragment decisionFragment = new DecisionFragment();
                decisionFragment.setArguments(args);
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .setCustomAnimations(R.anim.abc_slide_in_bottom, R.anim.abc_slide_out_bottom,
                                R.anim.abc_slide_in_bottom, R.anim.abc_slide_out_bottom)
                        .add(R.id.container, decisionFragment)
                        .addToBackStack(null)
                        .commit();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add_button: {
                new MaterialDialog.Builder(getActivity())
                        .title("New Choice")
                        .customView(R.layout.add_dialog, true)
                        .positiveText("Add")
                        .negativeText("Cancel")
                        .autoDismiss(false)
                        .callback(new MaterialDialog.ButtonCallback() {
                            @Override
                            public void onPositive(MaterialDialog dialog) {
                                super.onPositive(dialog);
                                EditText itemToAdd = (EditText) dialog.findViewById(R.id.item_to_add);
                                String valueToAdd = itemToAdd.getText().toString();
                                if (!valueToAdd.equals("")) {
                                    dialog.dismiss();
                                    mAdapter.add(valueToAdd);
                                } else {
                                    itemToAdd.setError("Please enter a value");
                                }
                            }

                            @Override
                            public void onNegative(MaterialDialog dialog) {
                                super.onNegative(dialog);
                                dialog.dismiss();
                            }
                        })
                        .show();
                break;
            }
        }
    }
}
