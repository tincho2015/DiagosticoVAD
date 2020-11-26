package android.martin.expertoeasyrules.adaptadores;

import android.content.Context;
import android.graphics.Color;
import android.martin.expertoeasyrules.R;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

public class adaptadorSpinner extends ArrayAdapter<String> {


    private Context context;
    ArrayList<String> distanciaInter;
    private int posicionSeleccionada;

    //constructor to get the list
    public adaptadorSpinner(Context context, ArrayList<String> distanciaInter) {
        super(context, R.layout.support_simple_spinner_dropdown_item,distanciaInter);
        this.distanciaInter= distanciaInter;
        this.context = context;
    }

    @Override
    public int getCount() {
        return distanciaInter.size();
    }


    @Override
    public String getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    //method returning list item
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater= LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.spinner_item, null);
        TextView txv= view.findViewById(R.id.txtSpinnerItem);
        txv.setText((distanciaInter.get(position)));
        txv.setTextColor(getContext().getResources().getColor(R.color.primaryText));

        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                posicionSeleccionada = position;
                return false;
            }
        });

       if(position==0) { //Primer elemento color Azul #39399F
           txv.setTextColor(getContext().getResources().getColor(R.color.secondaryText));
       }


        return view;
    }

   @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = this.getView(position,convertView,parent);
        if(posicionSeleccionada == position){
            v.setBackgroundResource(R.color.colorAccent);
            TextView tv= v.findViewById(R.id.txtSpinnerItem);
            tv.setTextColor(Color.WHITE);
        }
        return v;
    }
}


