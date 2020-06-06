package com.example.notetakingapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    final List<Note> notes;
    LayoutInflater layoutInflator;

//    Public constructor for the Adapter
    Adapter(Context context, List<Note> notes){
        this.layoutInflator = LayoutInflater.from(context);
        this.notes = notes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        Inflate a View from the layout we have created for the list of notes
        View view = layoutInflator.inflate(R.layout.list_note_items, parent, false);
//        Create a ViewHolder that takes in a View
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        /*
        Use the ViewHolder individual views and bind the data from the Note to them
         */
//        Get NOTE details from a certain position in the list and populate variables with them
        String title = notes.get(position).getTitle();
        String dateCreated = notes.get(position).getDate();
        String timeCreated = notes.get(position).getTime();
//        Adding a variable to hold the id from the notes
        long ID = notes.get(position).getId();

//        Bind the data above to the ViewHolder
        holder.holderNoteTitle.setText(title);
        holder.holderNoteDate.setText(dateCreated);
        holder.holderNoteTime.setText(timeCreated);
//        Bind the data for the id
        holder.holderNoteID.setText(String.valueOf(ID));
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
//        Variables of the views
        TextView holderNoteTitle;
        TextView holderNoteDate;
        TextView holderNoteTime;
//        Extra variable for the ID
        TextView holderNoteID;
        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
//        References to the views
            holderNoteTitle = itemView.findViewById(R.id.tvNoteTitle);
            holderNoteDate = itemView.findViewById(R.id.tvNoteDateCreated);
            holderNoteTime = itemView.findViewById(R.id.tvNoteTimeCreated);
//            Extra reference for the id
            holderNoteID = itemView.findViewById(R.id.listId);

//            Set on click listener
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    Pass an intent with the ID as an extra
                    Intent intent = new Intent(view.getContext(), DetailActivity.class);
                    intent.putExtra("EXTRA_ID", notes.get(getAdapterPosition()).getId());
                    view.getContext().startActivity(intent);
                }
            });
        }
    }
}
