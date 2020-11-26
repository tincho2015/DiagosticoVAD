package android.martin.expertoeasyrules.adaptadores;

import android.content.Context;
import android.graphics.Color;
import android.martin.expertoeasyrules.DatosListView;
import android.martin.expertoeasyrules.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import androidx.core.content.ContextCompat;

public class adaptadorMenu extends BaseAdapter {


    private Context context;
    private int layout;
    private List<DatosListView> opciones;


    public adaptadorMenu(Context context, int layout, List<DatosListView> opciones) {
        this.context = context;
        this.layout = layout;
        this.opciones = opciones;
    }



    @Override
    public int getCount() {
        return opciones.size();
    }

    @Override
    public Object getItem(int position) {
        return opciones.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        ViewHolderItem holder;

        if(convertView == null)
        {

            LayoutInflater layoutInflater = LayoutInflater.from(this.context);
            convertView = layoutInflater.inflate(R.layout.lista_item,null);

            holder = new ViewHolderItem();

            holder.textView =  convertView.findViewById(R.id.txtOpcion);
            holder.imageView = convertView.findViewById(R.id.imageView);
            convertView.setTag(holder);
        }else{

            holder = (ViewHolderItem)convertView.getTag();
        }
        if(opciones.get(position).isBloqueado()) {
            holder.textView.setTextColor(ContextCompat.getColor(context, R.color.secondaryText));
            holder.textView.setText(opciones.get(position).getTitulo());
            holder.imageView.setImageResource(opciones.get(position).getImagen());
        }else {
            holder.textView.setTextColor(ContextCompat.getColor(context, R.color.primaryText));
            holder.textView.setText(opciones.get(position).getTitulo());
            holder.imageView.setImageResource(opciones.get(position).getImagen());
        }
        if(opciones.get(position).isRealizado()){

            convertView.setBackgroundResource(R.color.realizado);
        }



        return convertView;





//        //Copiamos la vista
//        View v = convertView;
//
//        // Inflamos la vista que nos ha llegado con nuestro layout personalizado
//        LayoutInflater layoutInflater = LayoutInflater.from(this.context);
//        v = layoutInflater.inflate(R.layout.lista_item,null);
//
//        //Rellenamos el elemento a modificar y lo rellenamos
//
//        TextView textView = v.findViewById(R.id.txtOpcion);
//        ImageView imgView = v.findViewById(R.id.imageView);
//        textView.setText(opciones.get(position).getTitulo().toString());
//        imgView.setImageResource(opciones.get(position).getImagen());
//
//        //Devolvemos la vista inflada y modificada.
//        return v;

    }
    public static class ViewHolderItem {

        private TextView textView;
        private ImageView imageView;
    }
}
