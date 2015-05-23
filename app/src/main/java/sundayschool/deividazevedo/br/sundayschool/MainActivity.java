package sundayschool.deividazevedo.br.sundayschool;

import android.animation.Animator;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    Button btCadastrarSala, btRelatorio, btSair;

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        transicaoDeTela();

        btCadastrarSala = (Button) findViewById(R.id.bt_cadastrar_sala);
        btRelatorio = (Button) findViewById(R.id.bt_relatorio_dominical);
        btSair = (Button) findViewById(R.id.bt_sair);

        final SalaCRUD salaCRUD = new SalaCRUD(this);

        btCadastrarSala.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CadastraSalaActivity.class);
                startActivity(intent);
            }
        });

        btRelatorio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Sala> salas = salaCRUD.buscarTodos();
                if(salas != null) {
                    Intent intent = new Intent(getApplicationContext(), RelatorioDominicalActivity.class);
                    startActivity(intent);
                } else {
                    mensagem("ATENÇÃO", "Cadastre pelo menos uma sala para iniciar um relatório!");
                }
            }
        });

        btSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onStop();
                chamaTelaSair();
            }
        });

    }

    private void transicaoDeTela(){
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.fade_out);
    }

    private void chamaTelaSair() {
        Toast.makeText(MainActivity.this, "Saindo...", Toast.LENGTH_SHORT)
                .show();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        transicaoDeTela();
    }

    private void mensagem(String titulo, String msg) {
        AlertDialog.Builder alert = new AlertDialog.Builder(
                this);
        alert.setTitle(titulo);
        alert.setMessage(msg);

        alert.setPositiveButton("ENTENDI", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(MainActivity.this, CadastraSalaActivity.class);
                startActivity(intent);
                finish();
            }
        });

        alert.show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if(id == R.id.menu_sobre){
            Intent intent = new Intent(getApplicationContext(), SobreActivity.class);
            startActivity(intent);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

}
