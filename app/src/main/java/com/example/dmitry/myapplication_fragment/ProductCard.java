package com.example.dmitry.myapplication_fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URI;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Dmitry on 01.11.2015.
 */
public class ProductCard extends AppCompatActivity implements AdapterView.OnItemClickListener {
    final String LOG_TAG = "myLogs";
    Button moneyOk_readylist;
    ImageButton btnclock,btnNavigation;
    EditText header_readylist,editMoney_readylist;
    DataBase db;
    TextView txt1;
    Person person;
    String header,edittext,mmuri;
    ListView listView;
    Uri myUri;
    Items items;
    ListAdap myListAdapter;
    ArrayList<Items> arrItems;
//    Spinner spinner;
    int DIALOG_DATE = 1;
    int myYear = 2015;
    int myMonth = 0;
    int myDay = 01;
    //String[] data = {"one", "two", "three", "four", "five"};
    private static int LOAD_IMAGE_RESULTS = 1;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_card);
        arrItems= new ArrayList<Items>();
        moneyOk_readylist=(Button)findViewById(R.id.moneyOk_readylist);
        header_readylist=(EditText)findViewById(R.id.header_readylist);
        editMoney_readylist=(EditText)findViewById(R.id.editMoney_readylist);
        btnNavigation=(ImageButton)findViewById(R.id.btnNavigation);
        listView=(ListView)findViewById(R.id.listview_readylist);
        myListAdapter=new ListAdap(this,arrItems);
        listView.setAdapter(myListAdapter);

        db = new DataBase(this);
        txt1=(TextView)findViewById(R.id.textView1);


        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data);
        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner=(Spinner)findViewById(R.id.spinner);
//        spinner.setAdapter(adapter);
//        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                //Toast.makeText(getBaseContext(), "Position = " + position, Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
        btnNavigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value = editMoney_readylist.getText().toString().trim();
                items=new Items(editMoney_readylist.getText().toString());
                arrItems.add(items);
                myListAdapter.notifyDataSetChanged();
                editMoney_readylist.setText(" ");
            }
        });
        btnclock=(ImageButton)findViewById(R.id.btnclock);
        btnclock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DIALOG_DATE);

            }
        });
        btnclock.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                return false;
            }
        });
        txt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnclock.setImageResource(R.drawable.my_calendar_off);
                txt1.setText("");
            }
        });
        Intent intent=getIntent();
        if (intent.hasExtra("person")){
        person=(Person)intent.getSerializableExtra("person");

        header_readylist.setText(person.getName_person());
        editMoney_readylist.setText(person.getAge_person());}

//        addPhoto.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                return false;
//            }
//        });

        moneyOk_readylist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                header = header_readylist.getText().toString();
//                edittext = editMoney_readylist.getText().toString();
                person = new Person(header, edittext, mmuri);
                db.addPerson(person);
                Intent intent = new Intent(ProductCard.this, MainActivity.class);
                startActivity(intent);
                Log.d(LOG_TAG, "go to Main class" + " " + person.getId());
            }
        });


    }
    protected Dialog onCreateDialog(int id) {
        if (id == DIALOG_DATE) {
            Calendar cal = Calendar.getInstance();
            myYear=cal.get(Calendar.YEAR);
            myMonth=cal.get(Calendar.MONTH);
            myDay=cal.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog tpd = new DatePickerDialog(this, myCallBack, myYear, myMonth, myDay);
            return tpd;
        }
        return super.onCreateDialog(id);
    }

    DatePickerDialog.OnDateSetListener myCallBack = new DatePickerDialog.OnDateSetListener() {

        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            myYear = year;
            myMonth = monthOfYear;
            myDay = dayOfMonth;
            txt1.setText("Дата покупки: " + myDay + "/" + myMonth + "/" + myYear);

            btnclock.setImageResource(R.drawable.my_calendar_on);

//            else {
//                btnclock.setImageResource(R.drawable.my_calendar_off);
//                calendar=false;
//                txt1.setText("");
//            }
        }
    };

    @Override

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
// Here we need to check if the activity that was triggers was the Image Gallery.
        // If it is the requestCode will match the LOAD_IMAGE_RESULTS value.
        // If the resultCode is RESULT_OK and there is some data we know that an image was picked.
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            // Let's read picked image data - its URI
           myUri=data.getData();
           mmuri=myUri.toString();
           //imageView.setImageURI(data.getData());
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id) {

            case R.id.action_delete_card:
                db.deleteProduct(person);
                Toast.makeText(ProductCard.this, "Карточка удалена", Toast.LENGTH_SHORT).show();
                Intent intent2=new Intent(ProductCard.this,MainActivity.class);
                startActivity(intent2);
                break;
            case R.id.action_cover_card:
                Intent i = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                i.setType("image/*");
                //i.setAction(Intent.ACTION_PICK);
                startActivityForResult(Intent.createChooser(i, "Select picture"), 1);
                break;

        }
        return true;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}

