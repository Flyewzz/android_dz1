package com.example.android_hw1;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;
import android.view.ViewGroup;
import android.view.View;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tp_android_dz1.R;

public class DataAdapter extends RecyclerView.Adapter<ViewHolder> {


    private IEventListener listener;
    private List<Integer> data;

    DataAdapter(int listSize, IEventListener clickListener) {
        listener = clickListener;

        data = new ArrayList<>(listSize); // To create a new ArrayList to store numbers

        for(int i = 1; i < listSize; ++i) {
            // Add an iteration
            data.add(i);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_list, parent, false);
        return new ViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.getTextView().setText(String.format("%s", data.get(position)));
        holder.getTextView().setTextColor(getColor(position + 1));
    }

    @Override
    public void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        listener = null;
    }

    private int getColor(int number) {

        // A color for odd and for even is not the same
        if (number % 2 == 0) {
            return Color.RED;
        }
        return Color.BLUE;
    }

    void addNumber() {
        data.add(getItemCount() + 1);
        notifyItemInserted(getItemCount());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
