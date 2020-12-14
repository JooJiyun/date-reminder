package com.example.datealarm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class myDBAdapter extends ArrayAdapter implements View.OnClickListener {

    private ListBtnClickListener listBtnClickListener = null ;
    public interface ListBtnClickListener {
        void onListBtnClick(int position, int resourceid) ;
    }




    public myDBAdapter(@NonNull Context context, int resource, @NonNull List objects, ListBtnClickListener listBtnClickListener  ) {
        super(context, resource, objects);
        this.listBtnClickListener = listBtnClickListener;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        int pos = position;
        if(convertView==null){
            LayoutInflater inflater = (LayoutInflater)parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_item, parent, false);
        }
        TextView list_name = convertView.findViewById(R.id.list_name);
        TextView list_last = convertView.findViewById(R.id.list_last);
        TextView list_date = convertView.findViewById(R.id.list_date);

        myDB list_db = (myDB)getItem(position);
        list_name.setText(list_db.getName());
        list_date.setText(list_db.getDate());

        Date now = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy.MM.dd");
        String today = dateFormat.format(now);
        String target_date = list_db.getDate();

        try {
            Date firstDate = dateFormat.parse(today);
            Date secondDate = dateFormat.parse(target_date);

            long calDate = secondDate.getTime()-firstDate.getTime();
            long calDays = calDate/(24*60*60*1000);

            if(calDays<0){
                list_last.setText("D+"+Math.abs(calDays));
            }
            else{
                list_last.setText("D-"+calDays);
            }

        }catch(ParseException e){}


        Button btn = convertView.findViewById(R.id.list_delete);
        btn.setTag(position);
        btn.setOnClickListener(this);


        return convertView;
    }

    @Override
    public void onClick(View view){
        if (this.listBtnClickListener != null) {
            this.listBtnClickListener.onListBtnClick((int)view.getTag(), view.getId()) ;
        }
    }

}
