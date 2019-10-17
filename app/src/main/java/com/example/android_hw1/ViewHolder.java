package com.example.android_hw1;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.TextView;

import com.example.tp_android_dz1.R;

class ViewHolder extends RecyclerView.ViewHolder {

    private TextView textView;
    private IEventListener listener;

    TextView getTextView() {
        return textView; // getter for the TextView
    }

    ViewHolder(@NonNull View itemView, IEventListener clickListener) {
        super(itemView);
        listener = clickListener;
        textView = itemView.findViewById(R.id.item_list);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int number = getCurrentNumber(textView);
                listener.onNumberClick(number); // To set an listener for an event to the new number
            }
        });
    }



    private int getCurrentNumber(TextView textView) {
        try {
            String textFromTextView = textView.getText().toString();
            return Integer.parseInt(textFromTextView);
        } catch (Exception e){
            return 0;
        }
    }
}
