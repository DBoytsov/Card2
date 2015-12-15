package com.example.dmitry.myapplication_fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Person> persons;
    private RecyclerView rv;
    DataBase db;
    final String LOG_TAG = "myLogs";
    Person person;
    RVAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        rv = (RecyclerView)findViewById(R.id.rv);
        rv.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        db = new DataBase(this);
        persons = new ArrayList<>();
        getFromDB();


        if ((savedInstanceState != null) && (savedInstanceState.getSerializable("card") != null)) {
            persons.clear();
            persons.addAll((List<Person>) savedInstanceState.getSerializable("card"));
            adapter.notifyDataSetChanged();
                Log.d(LOG_TAG, "restore card with persons" + persons);
                }



        adapter.setOnItemClickListener(new RVAdapter.MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Toast.makeText(MainActivity.this, "push on item" + position, Toast.LENGTH_SHORT).show();
                person=(Person)adapter.getItem(position);
                Intent intent2 = new Intent(MainActivity.this, ProductCard.class);
                //MainActivity.this, ProductCard.class
                intent2.putExtra("person",person);
                startActivity(intent2);
                Log.d(LOG_TAG, "go to ProductCard class" +" "+ person.getId());
            }

        });
        adapter.setOnItemLongClickListener(new RVAdapter.MyLongClickListener() {
            @Override
            public void onItemLongClick(int position, View v) {
                Toast.makeText(MainActivity.this, "push on item long" + position, Toast.LENGTH_SHORT).show();
                //person=(Person)adapter.getItem(position);
                //persons.remove(person);

//                persons.add(person);
//                person=persons.get(position);
//                persons=db.getAllPersons();
//                person.getId();
                //db.deleteProduct(person);
                //persons=db.getAllPersons();
                //adapter = new RVAdapter(persons);
                //rv.setAdapter(adapter);
               // adapter.notifyDataSetChanged();
//              getFromDB();
                Log.d(LOG_TAG, "Removed " + position + " " + person.getId());

                //db.deleteProduct((Person) adapter.getItem(position));
                //getFromDB();
            }
        });


        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ProductCard.class);
                startActivity(intent);
               //person= new Person("Emma Wilson", "23 years old", R.drawable.im_beach);
               //persons.add(person);
               //db.addPerson(person);
               Log.d(LOG_TAG, "push add" + persons);
               getFromDB();
            }
        });

    }

    public void getFromDB(){
        persons=db.getAllPersons();
        adapter = new RVAdapter(persons);
        rv.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }
    @Override
    protected void onResume() {
        super.onResume();

//        adapter = new RVAdapter(db.getAllPersons());
//        rv.setAdapter(adapter);
//        Log.d(LOG_TAG, "onresume" + persons);
    }
       @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putSerializable("card", (Serializable) persons);
        Log.d(LOG_TAG, "save cards" + persons);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
