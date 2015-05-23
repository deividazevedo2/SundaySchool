package sundayschool.deividazevedo.br.sundayschool;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DVD on 09/05/2015.
 */
public class RelatorioCRUD {

    private Context context;

    public RelatorioCRUD(Context context) {
        SundaySchoolBD.getInstancia(context);
        this.context = context;
    }

    public void criar(Relatorio relatorio){

        ContentValues values = new ContentValues();
        values.put(SundaySchoolBD.RELATORIO_COLS[1], relatorio.getDia());
        values.put(SundaySchoolBD.RELATORIO_COLS[2], relatorio.getMes());
        values.put(SundaySchoolBD.RELATORIO_COLS[3], relatorio.getAno());
        values.put(SundaySchoolBD.RELATORIO_COLS[4], relatorio.getTotalPresentes());
        values.put(SundaySchoolBD.RELATORIO_COLS[5], relatorio.getTotalBiblias());

        SundaySchoolBD.getInstancia(context).getWritableDatabase()
                .insert(SundaySchoolBD.RELATORIO_TB, null, values);

    }

    public void remover(Relatorio relatorio){

        int idRelatorio = relatorio.getId();
        SundaySchoolBD.getInstancia(context).getWritableDatabase()
                .delete(SundaySchoolBD.RELATORIO_TB,
                        SundaySchoolBD.RELATORIO_COLS[0]+ " = "+ idRelatorio, null);

    }

    public void atualizar(Relatorio relatorio){

        ContentValues values = new ContentValues();
        values.put(SundaySchoolBD.RELATORIO_COLS[1], relatorio.getDia());
        values.put(SundaySchoolBD.RELATORIO_COLS[2], relatorio.getMes());
        values.put(SundaySchoolBD.RELATORIO_COLS[3], relatorio.getAno());
        values.put(SundaySchoolBD.RELATORIO_COLS[4], relatorio.getTotalPresentes());
        values.put(SundaySchoolBD.RELATORIO_COLS[5], relatorio.getTotalBiblias());

        SundaySchoolBD.getInstancia(context).getWritableDatabase().update(
                SundaySchoolBD.RELATORIO_TB,
                values, SundaySchoolBD.RELATORIO_COLS[0] + " = " + relatorio.getId(), null);
    }

    public Relatorio buscaPorData(Integer dia, Integer mes, Integer ano){

        Cursor cursor = SundaySchoolBD.getInstancia(context).getReadableDatabase().
                query(SundaySchoolBD.RELATORIO_TB,
                        SundaySchoolBD.RELATORIO_COLS,
                        SundaySchoolBD.RELATORIO_COLS[1]+ " = "+ dia +
                        " AND " + SundaySchoolBD.RELATORIO_COLS[2]+ " = "+ mes +
                        " AND " + SundaySchoolBD.RELATORIO_COLS[3]+ " = "+ ano,
                        null, null, null, null);

        ArrayList<Relatorio> relatorios = new ArrayList<>();

        cursor.moveToFirst();
        relatorios.add(cursorParaRelatorio(cursor));
        while (cursor.moveToNext()){
            relatorios.add(cursorParaRelatorio(cursor));
        }

        return relatorios.get(0);

    }

    public List<Relatorio> buscarTodos(){
        Cursor cursor = SundaySchoolBD.getInstancia(context).getReadableDatabase().
                query(SundaySchoolBD.RELATORIO_TB,
                        SundaySchoolBD.RELATORIO_COLS,
                        null, null, null, null,null);

        ArrayList<Relatorio> relatorios = new ArrayList<>();



        if(cursor.moveToFirst()){
            relatorios.add(cursorParaRelatorio(cursor));
        } else {
            return null;
        }
        while (cursor.moveToNext()){
            relatorios.add(cursorParaRelatorio(cursor));
        }

        return relatorios;
    }

    public Relatorio cursorParaRelatorio(Cursor cursor){
        Relatorio relatorio = new Relatorio();

        relatorio.setId(cursor.getInt(0));
        relatorio.setDia(cursor.getInt(1));
        relatorio.setMes(cursor.getInt(2));
        relatorio.setAno(cursor.getInt(3));
        relatorio.setTotalPresentes(cursor.getInt(4));
        relatorio.setTotalBiblias(cursor.getInt(5));

        return relatorio;
    }

}