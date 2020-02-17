package com.todoapp.sevenbits11.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.todoapp.sevenbits11.myapplication.Data.ExpenseManage;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<ExpenseManage> implements View.OnClickListener{

    private ArrayList<ExpenseManage> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView txtExpense;
        TextView txtCategory;
        TextView txtAmount;
        TextView txtNote;
//        ImageView info;
    }

    public CustomAdapter(ArrayList<ExpenseManage> data, Context context) {
        super(context, R.layout.raw_layout, data);
        this.dataSet = data;
        this.mContext=context;

    }

    @Override
    public void onClick(View v) {

        int position=(Integer) v.getTag();
        Object object= getItem(position);
        ExpenseManage dataModel=(ExpenseManage)object;


//        switch (v.getId())
//        {
//            case R.id.item_info:
//                Snackbar.make(v, "Release date " +dataModel.getFeature(), Snackbar.LENGTH_LONG)
//                        .setAction("No action", null).show();
//                break;
//        }
    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        ExpenseManage dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

//        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.raw_layout, parent, false);
            viewHolder.txtExpense =  convertView.findViewById(R.id.tvexpensename);
            viewHolder.txtCategory =  convertView.findViewById(R.id.tv_category);
            viewHolder.txtAmount =  convertView.findViewById(R.id.tvamount);
        viewHolder.txtNote =  convertView.findViewById(R.id.tvNote);



            result=convertView;

            convertView.setTag(viewHolder);
//        } else {
//            viewHolder = (ViewHolder) convertView.getTag();
//            result=convertView;
//        }

        lastPosition = position;

        viewHolder.txtExpense.setText(dataModel.getExpensename());
        viewHolder.txtCategory.setText(dataModel.getCategory());
        viewHolder.txtAmount.setText(" $ "+ dataModel.getAmount());
        viewHolder.txtNote.setText(dataModel.getNotes());
        // Return the completed view to render on screen
        return convertView;
    }
}
