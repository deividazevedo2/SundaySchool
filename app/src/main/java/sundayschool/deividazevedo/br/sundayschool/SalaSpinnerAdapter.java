package sundayschool.deividazevedo.br.sundayschool;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by DVD on 14/05/2015.
 */
public class SalaSpinnerAdapter extends BaseAdapter {

    List<Sala> salas;
    LayoutInflater inflater;
    Context context;

    public SalaSpinnerAdapter(Context context, List<Sala> salas) {

        this.salas = salas;
        this.context = context;
        inflater = LayoutInflater.from(this.context);

    }

    @Override
    public int getCount() {
        if(salas != null) {
            return salas.size();
        } else {
            return 0;
        }
    }

    @Override
    public Object getItem(int position) {
        return salas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v;
        ViewHolder holder;

        if (convertView == null){
            v = inflater.inflate(R.layout.list_item_sala_adapter, parent, false);
            holder = new ViewHolder();
            holder.tvSala = (TextView) v.findViewById(R.id.tv_lv_nome_sala);
            v.setTag(holder);

        } else {
            v = convertView;
            holder = (ViewHolder) v.getTag();
        }

        Sala sala = salas.get(position);
        holder.tvSala.setText(sala.getSala());

        return v;

    }

    private class ViewHolder{
        public TextView tvSala;

    }

}
