package sundayschool.deividazevedo.br.sundayschool;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import java.util.List;

public class CadastraSalaActivity extends ActionBarActivity {

    EditText etSala, etProfessor;
    Sala sala;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastra_sala);

        etSala = (EditText) findViewById(R.id.et_sala);
        etProfessor = (EditText) findViewById(R.id.et_professor);
        sala = new Sala();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_cadastra_sala, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_ver_salas) {
            Intent intent = new Intent(this, ListarSalasActivity.class);
            startActivity(intent);
            finish();
            return true;
        }

        if (id == R.id.menu_salvar_sala) {
            return salvar(sala);
        }

        return super.onOptionsItemSelected(item);
    }

    private boolean salvar(Sala sala) {

        SalaCRUD crud = new SalaCRUD(this);
        sala.setSala(etSala.getText().toString());
        sala.setProfessor(etProfessor.getText().toString());

        if ((sala.getSala().equals("") || sala.getSala() == null) ||
                (sala.getProfessor().equals("") || sala.getProfessor() == null)) {
            mensagem("ERRO!", "Verifique os campos e tente novamente!", false);
            return false;
        } else {
            List<Sala> salas = crud.buscarTodos();
            if (salas != null) {
                for (Sala s : salas) {
                    if (s.getSala().toUpperCase().equals(sala.getSala().toUpperCase())
                            & s.getProfessor().toUpperCase().equals(sala.getProfessor().toUpperCase())) {
                        mensagem("OPS!", "Já existe uma sala com estes dados! Verifique e tente novamente!", false);
                        return false;
                    }
                }
            }
            crud.criar(sala);
            mensagem("SUCESSO!", "Cadastro realizado!", true);

            return true;
        }
    }

    private void mensagem(String titulo, String msg, boolean sucesso) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle(titulo);
        alert.setMessage(msg);
        if(sucesso){
            alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    sala = new Sala();
                    etSala.setText("");
                    etProfessor.setText("");
                }
            });
        } else if(!sucesso) {
            alert.setNegativeButton("OK", null);
        }
        alert.show();
    }

    @Override
    public void onBackPressed(){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setMessage("Você deseja realmente cancelar o cadastro?");
        alert.setTitle("ATENÇÃO!");
        alert.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        alert.setNegativeButton("Não", null);
        alert.show();
    }
}
