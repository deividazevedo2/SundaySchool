package sundayschool.deividazevedo.br.sundayschool;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;


public class CadastraRelatorioActivity extends ActionBarActivity {

    EditText etPresentes, etVisitantes, etBiblias;
    Spinner spinner;
    Relatorio relatorio;
    Sala sala;
    Contagem contagem;
    Button btAdicionarAoRelatorio, btFinalizarRelatorio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        relatorio = (Relatorio) intent.getSerializableExtra("data");

        setContentView(R.layout.activity_cadastra_relatorio);

        spinner = (Spinner) findViewById(R.id.sp_sala);

        SalaCRUD salaCrud = new SalaCRUD(this);

        ArrayList<Sala> salas = (ArrayList) salaCrud.buscarTodos();

        SalaSpinnerAdapter adapter = new SalaSpinnerAdapter(getApplicationContext(), salas);
        spinner.setAdapter(adapter);

        etPresentes = (EditText) findViewById(R.id.et_presentes);
        etVisitantes = (EditText) findViewById(R.id.et_visitantes);
        etBiblias = (EditText) findViewById(R.id.et_biblias);
        btAdicionarAoRelatorio = (Button) findViewById(R.id.bt_adicionar_ao_relatorio);
        btFinalizarRelatorio = (Button) findViewById(R.id.bt_finalizar_relatorio);

        btAdicionarAoRelatorio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    registrarContagem();
            }
        });

        btFinalizarRelatorio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ListarRelatoriosActivity.class);
                startActivity(intent);
            }
        });

    }

    private void registrarContagem(){
        contagem = new Contagem();

        String presentesTemp, visitantesTemp, bibliasTemp;
        presentesTemp = etPresentes.getText().toString();
        visitantesTemp = etVisitantes.getText().toString();
        bibliasTemp = etBiblias.getText().toString();

        if(presentesTemp.equals("") || visitantesTemp.equals("") || bibliasTemp.equals("")) {
            mensagem("ERRO!", "Verifique os campos e tente novamente!", false);
        }
        else {
            salvar(contagem);
        }
    }

    private boolean salvar(Contagem contagem){

        ContagemCRUD contagemCrud = new ContagemCRUD(this);

        contagem.setPresentes(Integer.parseInt(etPresentes.getText().toString()));
        contagem.setVisitantes(Integer.parseInt(etVisitantes.getText().toString()));
        contagem.setBiblias(Integer.parseInt(etBiblias.getText().toString()));
        contagem.setIdRelatorio(relatorio.getId());

        sala = (Sala) spinner.getSelectedItem();

        contagem.setNomeSala(sala.getSala());

        contagemCrud.criar(contagem);

        adicionaPresentes(contagem, relatorio);

        mensagem("SUCESSO!", "Registro adicionado ao Relatório!", true);

        return true;
    }

    private void mensagem(String titulo, String msg, boolean sucesso) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle(titulo);
        alert.setMessage(msg);
        if(sucesso){
            alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    contagem = new Contagem();
                    etPresentes.setText("");
                    etVisitantes.setText("");
                    etBiblias.setText("");
                }
            });
        } else if(!sucesso) {
            alert.setNegativeButton("OK", null);
        }
        alert.show();

    }

    private void adicionaPresentes(Contagem contagem, Relatorio relatorio){

        RelatorioCRUD relatorioCrud = new RelatorioCRUD(this);

        Integer pessoas = contagem.getVisitantes() + contagem.getPresentes();

        relatorio.setTotalPresentes(relatorio.getTotalPresentes() + pessoas);
        relatorio.setTotalBiblias(relatorio.getTotalBiblias() + contagem.getBiblias());

        relatorioCrud.atualizar(relatorio);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_cadastra_relatorio, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

}
