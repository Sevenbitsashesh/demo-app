package com.todoapp.sevenbits11.myapplication;

import
        androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.print.PrintAttributes;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.todoapp.sevenbits11.myapplication.Data.ExpenseManage;

import com.todoapp.sevenbits11.myapplication.AppPreferences;

import java.util.Map;


public class NewExpenseActivity extends AppCompatActivity {

    EditText etExpenseName;
    EditText etAmount;
    EditText etNote;

    TextView tvAddnote;

    Spinner categorySpinner;
    Button btnAddExpense;

    ConstraintLayout layout;

    AppPreferences appPreferences;

    Integer idOfNote;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_expense);
        bindView();
        initialize();
    }
    public void bindView() {

        etExpenseName = findViewById(R.id.etExpensename);
        etAmount = findViewById(R.id.etAmount);

        tvAddnote = findViewById(R.id.tvAddNote);

        categorySpinner = findViewById(R.id.category_spinner);

        btnAddExpense = findViewById(R.id.btnAddExpense);

        appPreferences = new AppPreferences(getApplicationContext());

    }


    public void initialize() {
        final Spinner spinner = (Spinner) findViewById(R.id.category_spinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.expense_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);



        tvAddnote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Log.i("selected",spinner.getSelectedItem().toString());
                if(etNote == null) {

                    layout = (ConstraintLayout)findViewById(R.id.conItem);



                    ConstraintLayout.LayoutParams buttonLayoutParams = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
                    buttonLayoutParams.setMargins(50, 20, 0, 20);
                    EditText editNote = new EditText(getApplicationContext());

                    idOfNote = View.generateViewId();

                    editNote.setId(idOfNote);

                    editNote.setHint("Add Note (Optional)");


                    layout.addView(editNote);
                    editNote.setLayoutParams(buttonLayoutParams);
                    ConstraintSet set = new ConstraintSet();

                    set.clone(layout);
                    set.connect(editNote.getId(), ConstraintSet.RIGHT, ConstraintSet.PARENT_ID, ConstraintSet.LEFT);
                    set.connect(editNote.getId(), ConstraintSet.LEFT, ConstraintSet.PARENT_ID, ConstraintSet.RIGHT);
                    set.connect(editNote.getId(), ConstraintSet.TOP, R.id.tvAddNote, ConstraintSet.BOTTOM);
                    set.connect(editNote.getId(), ConstraintSet.BOTTOM, R.id.btnAddExpense, ConstraintSet.TOP);

                    set.applyTo(layout);

                    etNote = findViewById(editNote.getId());


                }



            }
        });

        btnAddExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("selected",spinner.getSelectedItem().toString());

                String expenseName = etExpenseName.getText().toString().trim();
                String amount = etAmount.getText().toString().trim();
                String notes;
                String category = categorySpinner.getSelectedItem().toString().trim();


                ExpenseManage expenseManageModel = new ExpenseManage();

                expenseManageModel.setExpensename(expenseName);
                expenseManageModel.setAmount(amount);
                expenseManageModel.setCategory(category);

                if(etNote != null) {

                    notes = etNote.getText().toString().trim();
                    expenseManageModel.setNotes(notes);

                }





//                appPreferences.setDataFromSharedPreferences(expenseManageModel);

                if(!expenseName.equals("") && !amount.equals("")  && !category.equals("") ) {

                    appPreferences.addFavorite(getApplicationContext(),expenseManageModel);

                    expenseManageModel.setExpensename("");
                    expenseManageModel.setAmount("");
                    expenseManageModel.setCategory("");

                    etExpenseName.setText("");
                    etAmount.setText("");
                    categorySpinner.setSelection(0);
                    layout.removeView(etNote);
                    etNote = null;

                    Intent intent = new Intent(NewExpenseActivity.this, ViewExpensesActivity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(NewExpenseActivity.this,"Please Add data",Toast.LENGTH_SHORT).show();
                }

                Map<String, ?> prefsMap = appPreferences.getAppSharedPrefs().getAll();
                for (Map.Entry<String, ?> entry: prefsMap.entrySet()) {
                    Log.v("SharedPreferences", entry.getKey() + ":" +
                            entry.getValue().toString());
                }

                Log.i("added","yes");
            }
        });

    }

    @Override
    public void onBackPressed() {
//        if (!shouldAllowBack()) {
//            doSomething();
//        } else {
//            super.onBackPressed();
//        }
    }

}
