package sundayschool.deividazevedo.br.sundayschool;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.internal.widget.AdapterViewCompat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class ListarRelatoriosActivity extends ListActivity {

    RelatorioAdapter adapter;
    RelatorioCRUD relatorioCrud;
    ArrayList<Relatorio> relatorios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        relatorioCrud = new RelatorioCRUD(this);

        relatorios = (ArrayList) relatorioCrud.buscarTodos();

        if(relatorios != null) {

            Toast toast = Toast.makeText(getApplicationContext(),
                    "Listagem dos Relatórios Criados!", Toast.LENGTH_LONG);
            toast.show();

            adapter = new RelatorioAdapter(getApplicationContext(), relatorios);
            setListAdapter(adapter);
        } else{
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Nada aqui!! Cadastre um relatório.", Toast.LENGTH_LONG);
            toast.show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_listar_relatorios, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), RelatorioDominicalActivity.class);
        startActivity(intent);
        finish();
    }
}
