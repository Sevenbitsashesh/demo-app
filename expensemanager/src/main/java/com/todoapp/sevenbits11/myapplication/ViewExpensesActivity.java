package com.todoapp.sevenbits11.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toolbar;

import com.todoapp.sevenbits11.myapplication.Data.ExpenseManage;

import java.util.ArrayList;

public class ViewExpensesActivity extends AppCompatActivity {

    ArrayList<ExpenseManage> dataModels;
    ListView listView;
    private static CustomAdapter adapter;

    AppPreferences appPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_expenses);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        listView=(ListView)findViewById(R.id.lvExpenses);

        dataModels= new ArrayList<ExpenseManage>();

        appPreferences = new AppPreferences(getApplicationContext());

         dataModels = appPreferences.getFavorites(getApplicationContext());


//        dataModels.add(new DataModel("Apple Pie", "Android 1.0", "1","September 23, 2008"));
//        dataModels.add(new DataModel("Banana Bread", "Android 1.1", "2","February 9, 2009"));
//        dataModels.add(new DataModel("Cupcake", "Android 1.5", "3","April 27, 2009"));
//        dataModels.add(new DataModel("Donut","Android 1.6","4","September 15, 2009"));
//        dataModels.add(new DataModel("Eclair", "Android 2.0", "5","October 26, 2009"));
//        dataModels.add(new DataModel("Froyo", "Android 2.2", "8","May 20, 2010"));
//        dataModels.add(new DataModel("Gingerbread", "Android 2.3", "9","December 6, 2010"));
//        dataModels.add(new DataModel("Honeycomb","Android 3.0","11","February 22, 2011"));
//        dataModels.add(new DataModel("Ice Cream Sandwich", "Android 4.0", "14","October 18, 2011"));
//        dataModels.add(new DataModel("Jelly Bean", "Android 4.2", "16","July 9, 2012"));
//        dataModels.add(new DataModel("Kitkat", "Android 4.4", "19","October 31, 2013"));
//        dataModels.add(new DataModel("Lollipop","Android 5.0","21","November 12, 2014"));
//        dataModels.add(new DataModel("Marshmallow", "Android 6.0", "23","October 5, 2015"));

        adapter= new CustomAdapter(dataModels,getApplicationContext());

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                ExpenseManage dataModel= dataModels.get(position);

//                Snackbar.make(view, dataModel.getName()+"\n"+dataModel.getType()+" API: "+dataModel.getVersion_number(), Snackbar.LENGTH_LONG)
//                        .setAction("No action", null).show();
            }
        });
    }
}
