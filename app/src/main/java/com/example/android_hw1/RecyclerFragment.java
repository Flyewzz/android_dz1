package com.example.android_hw1;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tp_android_dz1.R;

import java.util.Objects;

public class RecyclerFragment extends Fragment {
    private IEventListener listener;
    private int listSize = 100;

    private static final String LIST_SIZE = "LIST_SIZE";


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (IEventListener) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recycler, container, false);
    }

    private int getSpanCount() {
        return getResources().getInteger((R.integer.columns));
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (savedInstanceState != null) {
            listSize = savedInstanceState.getInt(LIST_SIZE);
        }

        final DataAdapter adapter = new DataAdapter(listSize, listener);

        RecyclerView recyclerView = Objects.requireNonNull(getView()).findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(getView().getContext(), getSpanCount()));
        recyclerView.setAdapter(adapter);

        Button button;
        button = getView().findViewById(R.id.addButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.addNumber();
                ++listSize;
            }
        });
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(LIST_SIZE, listSize);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null; // Do not forget to make 'null' listener if detaching...
    }


}
