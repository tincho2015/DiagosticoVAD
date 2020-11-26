package android.martin.expertoeasyrules.adaptadores;

import android.content.Context;
import android.martin.expertoeasyrules.R;
import android.martin.expertoeasyrules.hechos.paciente;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class adaptadorPaciente extends BaseAdapter {



    private Context context;
    private int layout;
    private List<paciente> pacientes;


    public adaptadorPaciente(Context context, int layout, List<paciente> pacientes) {
        this.context = context;
        this.layout = layout;
        this.pacientes = pacientes;
    }



    @Override
    public int getCount() {
        return pacientes.size();
    }

    @Override
    public Object getItem(int position) {
        return pacientes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

       ViewHolderItem holder;

        if(convertView == null){


        // Inflamos la vista que nos ha llegado con nuestro layout personalizado
        LayoutInflater layoutInflater = LayoutInflater.from(this.context);
        convertView = layoutInflater.inflate(R.layout.lista_item_paciente,null);

        holder = new ViewHolderItem();

        //Referenciamos el elemento a modificar y lo rellenamos
        holder.textViewPaciente = convertView.findViewById(R.id.txtNombrePaciente);
        holder.imageViewPaciente = convertView.findViewById(R.id.imgPaciente);
        holder.textViewEdad = convertView.findViewById(R.id.txtEdadPaciente);
        holder.textViewSexo = convertView.findViewById(R.id.txtSexoPaciente);
        convertView.setTag(holder);

      }else{

            holder = (ViewHolderItem)convertView.getTag();
        }

        String currentName = pacientes.get(position).getNombre();
        String currentEdad = String.valueOf(pacientes.get(position).getEdad());
        String currentSexo = pacientes.get(position).getSexo();

        holder.textViewPaciente.setText(currentName);
        holder.textViewEdad.setText(currentEdad);
        holder.textViewSexo.setText(currentSexo);

        if(pacientes.get(position).getSexo().equals("Masculino")){
            holder.imageViewPaciente.setImageResource(R.mipmap.ic_avatar_hombre);}
        else {
            holder.imageViewPaciente.setImageResource(R.mipmap.ic_avatar_mujer);
        }


        //Devolvemos la vista inflada y modificada.
        return convertView;

    }
   public static class ViewHolderItem {

        private TextView textViewPaciente;
        private TextView textViewEdad;
        private TextView textViewSexo;
        private ImageView imageViewPaciente;
    }
}
