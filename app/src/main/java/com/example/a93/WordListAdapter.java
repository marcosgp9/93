package com.example.a93;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;



public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordViewHolder> {


    private LinkedList<String> mWordList;
    private LayoutInflater mInflater;


    public WordListAdapter(Context context, LinkedList<String> mWorldLinked) {
        mInflater = LayoutInflater.from(context);
        this.mWordList = mWorldLinked;
    }


    @Override
    public WordViewHolder onCreateViewHolder(ViewGroup parent, int viewType){ // Inflate an item view.
        View mItemView = mInflater.inflate(R.layout.row, parent, false);
        return new WordViewHolder(mItemView, this);

    }


    @Override
    public void onBindViewHolder(WordViewHolder holder, int position) {
        //Retrieve the data for that position
        String mCurrent = mWordList.get(position);
        // Add the data to the view
        holder.wordItemView.setText(mCurrent);
    }

    @Override
    public int getItemCount() {

        return mWordList.size();
    }

    class WordViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView wordItemView;
        private WordListAdapter mAdapter;

        public WordViewHolder(View itemView, WordListAdapter adapter) {
            super(itemView);
            wordItemView = (TextView) itemView.findViewById(R.id.word);
            this.mAdapter = adapter;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }
}