package sundayschool.deividazevedo.br.sundayschool;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DVD on 09/05/2015.
 */
public class RelatorioAdapter extends BaseAdapter {

    List<Relatorio> relatorios;
    ContagemCRUD contagemCrud;
    RelatorioCRUD relatorioCrud;
    LayoutInflater inflater;
    Context context;
    boolean apagado = false;
    String contagem = "";

    public RelatorioAdapter(Context context, List<Relatorio> relatorios) {

        this.relatorios = relatorios;
        this.context = context;
        inflater = LayoutInflater.from(this.context);
    }

    @Override
    public int getCount() {
        return relatorios.size();
    }

    @Override
    public Object getItem(int position) {
        return relatorios.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {

        View v;
        ViewHolder holder;

        if (convertView == null){
            v = inflater.inflate(R.layout.list_item_relatorio, parent, false);
            holder = new ViewHolder();
            holder.tvDataRelatorio = (TextView) v.findViewById(R.id.tv_lv_data_relatorio);
            holder.tvTotalPresentes = (TextView) v.findViewById(R.id.tv_total_alunos);
            holder.tvTotalBiblias = (TextView) v.findViewById(R.id.tv_total_biblias);
            holder.btDetalhes = (Button) v.findViewById(R.id.bt_detalhes_relatorio);
            v.setTag(holder);

        } else {
            v = convertView;
            holder = (ViewHolder) v.getTag();
        }

        final Relatorio relatorio = relatorios.get(position);

        String dataRelatorio = relatorio.getDia().toString() + "/" + relatorio.getMes().toString()
                + "/" + relatorio.getAno().toString();

        holder.tvDataRelatorio.setText(dataRelatorio);
        holder.tvTotalPresentes.setText(relatorio.getTotalPresentes().toString());
        holder.tvTotalBiblias.setText(relatorio.getTotalBiblias().toString());

        holder.btDetalhes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                relatorioCrud = new RelatorioCRUD(v.getContext());
                contagemCrud = new ContagemCRUD(v.getContext());
                contagem = "";
                ArrayList<Contagem> contagens = (ArrayList)
                        contagemCrud.buscarPorIdDoRelatorio(relatorio.getId());

                for(Contagem c : contagens){
                   contagem += "Sala: " + c.getNomeSala().toUpperCase() + System.getProperty("line.separator") +
                            "Alunos Presentes: " + c.getPresentes()+ ";" +
                           System.getProperty("line.separator") + "Visitantes: " +
                    c.getVisitantes() + ";" + System.getProperty("line.separator");
                }

                AlertDialog.Builder dialog = new AlertDialog.Builder(parent.getContext());
                dialog.setTitle("Detalhes");
                dialog.setMessage(contagem);
                dialog.setPositiveButton("APAGAR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        apagar(parent, relatorio);
                        updateItens(relatorios);
                    }
                });
                dialog.setNegativeButton("FECHAR", null);

                dialog.show();
            }
        });
        if(apagado){
            v = inflater.inflate(R.layout.list_item_sala, parent, false);
        }

        return v;

    }

    public void updateItens(List<Relatorio> relatorios) {
        this.relatorios = relatorios;
        notifyDataSetChanged();
    }

    private void apagar(final ViewGroup parent, final Relatorio relatorio){
        AlertDialog.Builder dialog = new AlertDialog.Builder(parent.getContext());
        dialog.setTitle("Detalhes");
        dialog.setMessage("Tem certeza que deseja apagar este registro?");
        dialog.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                relatorioCrud.remover(relatorio);
                List<Contagem> cont = contagemCrud.buscarPorIdDoRelatorio(relatorio.getId());
                for(Contagem c : cont){
                    contagemCrud.remover(c);
                }
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
        public TextView tvDataRelatorio;
        public TextView tvTotalPresentes;
        public TextView tvTotalBiblias;
        public Button btDetalhes;
    }

}
