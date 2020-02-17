package com.todoapp.sevenbits11.myapplication.Data;

import com.google.gson.annotations.SerializedName;

public class ExpenseManage {

    @SerializedName("Expensename")
    String Expensename;
    @SerializedName("Amount")
    String Amount;
    @SerializedName("Category")
    String Category;
    @SerializedName("Notes")
    String Notes;

    public String getExpensename() {
        return Expensename;
    }
    public void setExpensename(String expenseName) {
        this.Expensename= expenseName;
    }

    public String getAmount() {
        return Amount;
    }
    public void setAmount(String amount) {
        this.Amount = amount;
    }

    public String getCategory() {
        return Category;
    }
    public void setCategory(String category) {
        this.Category= category;
    }

    public String getNotes() {
        return Notes;
    }
    public void setNotes(String notes) {
        this.Notes = notes;
    }

}
