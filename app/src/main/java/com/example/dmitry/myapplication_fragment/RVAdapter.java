package com.example.dmitry.myapplication_fragment;

import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dmitry on 01.11.2015.
 */
public class RVAdapter extends RecyclerView.Adapter<RVAdapter.PersonViewHolder>{
    List<Person> persons;
    private static String LOG_TAG = "MyRecyclerViewAdapter";
    private static MyClickListener myClickListener;
    private static MyLongClickListener myLongClickListener;



    RVAdapter(List<Person> persons){
        this.persons = persons;
    }

    public Object getItem(int position) {
        return persons.get(position);
    }
    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card, viewGroup, false);
        PersonViewHolder pvh = new PersonViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(PersonViewHolder personViewHolder, int i) {
        personViewHolder.personName.setText(persons.get(i).name);
        personViewHolder.personAge.setText(persons.get(i).age);
//        personViewHolder.personPhoto.setImageResource(R.drawable.im_beach);
        personViewHolder.personPhoto.setImageURI(Uri.parse(persons.get(i).photoId));
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return persons.size();
    }

    public static class PersonViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener {
        CardView cv;
        TextView personName;
        TextView personAge;
        ImageView personPhoto;
        ImageView imageOk;
        PersonViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            personName = (TextView)itemView.findViewById(R.id.person_name);
            personAge = (TextView)itemView.findViewById(R.id.person_age);
            personPhoto = (ImageView)itemView.findViewById(R.id.person_photo);
            imageOk=(ImageView)itemView.findViewById(R.id.imageOK);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            myClickListener.onItemClick(getAdapterPosition(), v);
        }
        @Override
        public boolean onLongClick(View v) {
            myLongClickListener.onItemLongClick(getAdapterPosition(), v);
            imageOk.setVisibility(View.VISIBLE);
            return true;
        }



}

    public void setOnItemClickListener(MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }
    public void setOnItemLongClickListener(MyLongClickListener myLongClickListener) {
        this.myLongClickListener = myLongClickListener;
    }

    public interface MyClickListener {
        public void onItemClick(int position, View v);
    }
    public interface MyLongClickListener{
        public boolean onItemLongClick(int position, View v);


    }

}
