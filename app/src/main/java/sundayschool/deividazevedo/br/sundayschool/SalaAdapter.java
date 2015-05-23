package sundayschool.deividazevedo.br.sundayschool;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by DVD on 09/05/2015.
 */
public class SalaAdapter extends BaseAdapter {

    List<Sala> salas;
    LayoutInflater inflater;
    Context context;
    boolean apagado = false;
    SalaCRUD salaCRUD;

    public SalaAdapter(Context context, List<Sala> salas) {

        this.salas = salas;
        this.context = context;
        inflater = LayoutInflater.from(this.context);

    }

    @Override
    public int getCount() {
        return salas.size();
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
    public View getView(int position, final View convertView, final ViewGroup parent) {

        View v;
        ViewHolder holder;

        if (convertView == null){
            v = inflater.inflate(R.layout.list_item_sala, parent, false);
            holder = new ViewHolder();
            holder.tvSala = (TextView) v.findViewById(R.id.tv_lv_nome_sala);
            holder.tvProfessor = (TextView) v.findViewById(R.id.tv_lv_nome_professor);
            holder.btDetalhes = (Button) v.findViewById(R.id.bt_detalhes_classe);


        } else {
            v = convertView;
            holder = (ViewHolder) v.getTag();
        }

        final Sala sala = salas.get(position);
        holder.tvSala.setText(sala.getSala());
        holder.tvProfessor.setText(sala.getProfessor());

        holder.btDetalhes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                salaCRUD = new SalaCRUD(v.getContext());
                AlertDialog.Builder dialog = new AlertDialog.Builder(parent.getContext());
                dialog.setTitle("Detalhes");
                dialog.setMessage("Sala: " + sala.getSala() + System.getProperty("line.separator")
                        + "Professor: " + sala.getProfessor());
                dialog.setPositiveButton("APAGAR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        apagar(parent, sala);
                        updateItens(salas);

                    }
                });
                dialog.setNegativeButton("ALTERAR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AlertDialog.Builder dialogAlterar = new AlertDialog.Builder(parent.getContext());

                        Context context = v.getContext();
                        LinearLayout layout = new LinearLayout(context);
                        layout.setOrientation(LinearLayout.VERTICAL);

                        final TextView tvNomeSala = new TextView(context);
                        tvNomeSala.setText("Sala: ");
                        layout.addView(tvNomeSala);

                        final EditText nomeClasse = new EditText(context);
                        nomeClasse.setText(sala.getSala());
                        nomeClasse.setTextColor(Color.parseColor("#000000"));
                        layout.addView(nomeClasse);

                        final TextView tvNomeProfessor = new TextView(context);
                        tvNomeProfessor.setText("Professor: ");
                        layout.addView(tvNomeProfessor);

                        final EditText professor = new EditText(context);
                        professor.setText(sala.getProfessor());
                        professor.setTextColor(Color.parseColor("#000000"));
                        layout.addView(professor);

                        dialogAlterar.setTitle("Alterar");
                        dialogAlterar.setView(layout);

                        dialogAlterar.setPositiveButton("Alterar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                sala.setSala(nomeClasse.getText().toString());
                                sala.setProfessor(professor.getText().toString());

                                if(!(sala.getSala().isEmpty())) {
                                    salaCRUD.atualizar(sala);
                                    updateItens(salas);
                                } else {
                                    Toast toast = Toast.makeText(v.getContext(),
                                            "Verifique os campos vazios e tente novamente!", Toast.LENGTH_LONG);
                                    toast.show();
                                }

                            }
                        });
                        dialogAlterar.setNegativeButton("Cancelar", null);
                        //((ViewGroup)caixaAlterar.getParent()).removeView(parent);
                        dialogAlterar.show();
                    }
                });
                dialog.show();
            }
        });
        if(apagado){
            v = inflater.inflate(R.layout.list_item_sala, parent, false);
        }
        v.setTag(holder);
        return v;
    }

    public void updateItens(List<Sala> salas) {
        this.salas = salas;
        notifyDataSetChanged();
    }

    private void apagar(final ViewGroup parent, final Sala sala){
        AlertDialog.Builder dialog = new AlertDialog.Builder(parent.getContext());
        dialog.setTitle("Detalhes");
        dialog.setMessage("Tem certeza que deseja apagar este registro?");
        dialog.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                salaCRUD.remover(sala);
                Toast toast = Toast.makeText(parent.getContext(),
                        "Registro removido!", Toast.LENGTH_LONG);
                toast.show();
                apagado = true;
            }
        });
        dialog.setNegativeButton("NÃO", null);
        dialog.show();

    }

    private class ViewHolder{
        public TextView tvSala;
        public TextView tvProfessor;
        public Button btDetalhes;
    }

}
