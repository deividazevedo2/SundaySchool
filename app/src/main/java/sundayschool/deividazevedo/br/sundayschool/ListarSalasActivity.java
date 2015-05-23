package sundayschool.deividazevedo.br.sundayschool;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class ListarSalasActivity extends ListActivity {

    SalaAdapter adapter;
    SalaCRUD salaCrud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        salaCrud = new SalaCRUD(this);
        ArrayList<Sala> salas = (ArrayList) salaCrud.buscarTodos();

        if(salas != null) {

            Toast toast = Toast.makeText(getApplicationContext(),
                    "Salas Cadastradas!", Toast.LENGTH_LONG);
            toast.show();

            adapter = new SalaAdapter(getApplicationContext(), salas);
            setListAdapter(adapter);
        } else{
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Nada aqui!! Cadastre uma Sala.", Toast.LENGTH_LONG);
            toast.show();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_listar_salas, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), CadastraSalaActivity.class);
        startActivity(intent);
        finish();
    }

}
