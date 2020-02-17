package com.todoapp.sevenbits11.myapplication;


import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.todoapp.sevenbits11.myapplication.Data.ExpenseManage;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AppPreferences {

    private static final String EXPENSES = "expenses";
    private static final String EXPENSE_NAME = "expense name";

    private SharedPreferences appSharedPrefs;

    Context context;

    public AppPreferences(Context context) {
        this.context = context;
        appSharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public void setStringPreference(String key, String value) {
        Editor editor = appSharedPrefs.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public String getStringPreference(String key) {
        return appSharedPrefs.getString(key, "");
    }

    public String getExpenseName() {
        return getStringPreference(EXPENSE_NAME);
    }

    public void setExpenseName(String expenseName) {
        setStringPreference(EXPENSE_NAME, expenseName);
    }

    public void setDataFromSharedPreferences(ExpenseManage curProduct){
        Gson gson = new Gson();
        String jsonCurProduct = gson.toJson(curProduct);

        SharedPreferences sharedPref = context.getApplicationContext().getSharedPreferences(EXPENSES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        editor.putString(curProduct.getExpensename(), jsonCurProduct);
        editor.commit();
    }

    public List<ExpenseManage> getDataFromSharedPreferences(){
        Gson gson = new Gson();
        List<ExpenseManage> productFromShared = new ArrayList<>();
        SharedPreferences sharedPref = context.getApplicationContext().getSharedPreferences(EXPENSES, Context.MODE_PRIVATE);
        String jsonPreferences = sharedPref.getString(EXPENSE_NAME, "");

        Type type = new TypeToken<List<ExpenseManage>>() {}.getType();
        productFromShared = gson.fromJson(jsonPreferences, type);

        return productFromShared;
    }

    private void addInJSONArray(ExpenseManage expensetoAdd){

        Gson gson = new Gson();
        SharedPreferences sharedPref = context.getApplicationContext().getSharedPreferences(EXPENSES, Context.MODE_PRIVATE);

        String jsonSaved = sharedPref.getString(EXPENSE_NAME, "");
        String jsonNewproductToAdd = gson.toJson(expensetoAdd);

        JSONArray jsonArrayProduct= new JSONArray();

        try {
            if(jsonSaved.length()!=0){
                jsonArrayProduct = new JSONArray(jsonSaved);
            }
            jsonArrayProduct.put(new JSONObject(jsonNewproductToAdd));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //SAVE NEW ARRAY
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(EXPENSE_NAME, gson.toJson(jsonArrayProduct));
        editor.commit();
    }
    public SharedPreferences getAppSharedPrefs() {
        return appSharedPrefs;
    }


    public ArrayList<ExpenseManage> getFavorites(Context context) {
        SharedPreferences settings;
        List<ExpenseManage> favorites;

        settings = context.getSharedPreferences(EXPENSES,
                Context.MODE_PRIVATE);

        if (settings.contains(EXPENSE_NAME)) {
            String jsonFavorites = settings.getString(EXPENSE_NAME, null);
            Log.i("data",settings.getString(EXPENSE_NAME,null));
            Gson gson = new Gson();
            if(!jsonFavorites.equals("")) {
                ExpenseManage[] favoriteItems = gson.fromJson(jsonFavorites,
                        ExpenseManage[].class);

                favorites = Arrays.asList(favoriteItems);
                favorites = new ArrayList<ExpenseManage>(favorites);
            }
            else {
                favorites = new ArrayList<ExpenseManage>();
            }

        } else
            return null;

        return (ArrayList<ExpenseManage>) favorites;
    }

    // This four methods are used for maintaining favorites.
    public void saveFavorites(Context context, List<ExpenseManage> favorites) {
        SharedPreferences settings;
        Editor editor;

        settings = context.getSharedPreferences(EXPENSES,
                Context.MODE_PRIVATE);
        editor = settings.edit();

        Gson gson = new Gson();
        String jsonFavorites = gson.toJson(favorites);

        editor.putString(EXPENSE_NAME, jsonFavorites);

        editor.commit();
    }

    public void addFavorite(Context context, ExpenseManage product) {
        List<ExpenseManage> favorites = getFavorites(context);
        if (favorites == null)
            favorites = new ArrayList<ExpenseManage>();
        favorites.add(product);
        saveFavorites(context, favorites);
    }
}
