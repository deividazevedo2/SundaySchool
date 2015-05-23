package sundayschool.deividazevedo.br.sundayschool;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import java.util.List;


public class RelatorioDominicalActivity extends ActionBarActivity {

    DatePicker dpData;
    Button btFazerRelatorio;
    Relatorio relatorio;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relatorio_dominical);

        dpData = (DatePicker) findViewById(R.id.dp_data_aula);
        btFazerRelatorio = (Button) findViewById(R.id.bt_fazer_relatorio);

        btFazerRelatorio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                relatorio = new Relatorio();

                relatorio.setDia(dpData.getDayOfMonth());
                relatorio.setMes(dpData.getMonth()+1);
                relatorio.setAno(dpData.getYear());

                salvar(relatorio);

                Intent intent = new Intent(getApplicationContext(), CadastraRelatorioActivity.class);
                intent.putExtra("data", relatorio);
                startActivity(intent);

            }
        });

    }

    private boolean salvar(Relatorio relatorio){

        RelatorioCRUD relatorioCrud = new RelatorioCRUD(this);

        List<Relatorio> relatorios = relatorioCrud.buscarTodos();

        if(relatorios != null) {
            for (Relatorio r : relatorios) {
                if (r.getDia().toString().equals(relatorio.getDia().toString())) {
                    if (r.getMes().toString().equals(relatorio.getMes().toString())) {
                        if (r.getAno().toString().equals(relatorio.getAno().toString())) {
                            Toast toast = Toast.makeText(getApplicationContext(),
                                    "Relatório do dia já existente!", Toast.LENGTH_LONG);
                            toast.show();
                            this.relatorio = r;
                            return false;
                        }
                    }
                }
            }
        }
        relatorio.setTotalPresentes(0);
        relatorio.setTotalBiblias(0);

        Toast toast = Toast.makeText(getApplicationContext(),
                "Relatório do dia criado!", Toast.LENGTH_LONG);
        toast.show();

        relatorioCrud.criar(relatorio);

        this.relatorio = relatorioCrud.buscaPorData(relatorio.getDia(),
                relatorio.getMes(), relatorio.getAno());

        return true;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_relatorio_dominical, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.menu_ver_relatorios) {
            Intent intent = new Intent(this, ListarRelatoriosActivity.class);
            startActivity(intent);
            finish();
            return true;

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }

}
