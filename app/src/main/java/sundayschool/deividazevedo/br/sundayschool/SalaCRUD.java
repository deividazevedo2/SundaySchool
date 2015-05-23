package sundayschool.deividazevedo.br.sundayschool;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DVD on 09/05/2015.
 */
public class SalaCRUD {

    private Context context;

    public SalaCRUD(Context context) {
        SundaySchoolBD.getInstancia(context);
        this.context = context;
    }

    public void criar(Sala sala){

        ContentValues values = new ContentValues();
        values.put(SundaySchoolBD.CLASSE_COLS[1], sala.getSala());
        values.put(SundaySchoolBD.CLASSE_COLS[2], sala.getProfessor());

        SundaySchoolBD.getInstancia(context).getWritableDatabase()
                .insert(SundaySchoolBD.CLASSE_TB, null, values);


    }

    public void remover(Sala sala){

        int idSala = sala.getId();
        SundaySchoolBD.getInstancia(context).getWritableDatabase()
                .delete(SundaySchoolBD.CLASSE_TB,
                        SundaySchoolBD.CLASSE_COLS[0] + " = " + idSala, null);
    }

    public void atualizar(Sala sala){

        ContentValues values = new ContentValues();
        values.put(SundaySchoolBD.CLASSE_COLS[1], sala.getSala());
        values.put(SundaySchoolBD.CLASSE_COLS[2], sala.getProfessor());

        SundaySchoolBD.getInstancia(context).getWritableDatabase().update(
                SundaySchoolBD.CLASSE_TB,
                values, SundaySchoolBD.CLASSE_COLS[0] + " = " + sala.getId(), null);
    }

    public Sala buscaPorId(Integer id){

        Cursor cursor = SundaySchoolBD.getInstancia(context).getReadableDatabase().
                query(SundaySchoolBD.CLASSE_TB,
                        SundaySchoolBD.CLASSE_COLS,
                        SundaySchoolBD.CLASSE_COLS[0]+ " = "+ id,
                        null, null, null,null);

        ArrayList<Sala> salas = new ArrayList<>();

        cursor.moveToFirst();
        while (cursor.moveToNext()){
            salas.add(cursorParaSala(cursor));
        }

        return salas.get(0);

    }

    public List<Sala> buscarTodos(){
        Cursor cursor = SundaySchoolBD.getInstancia(context).getReadableDatabase().
                query(SundaySchoolBD.CLASSE_TB,
                        SundaySchoolBD.CLASSE_COLS,
                        null, null, null, null,null);

        ArrayList<Sala> salas = new ArrayList<>();

        if(cursor.moveToFirst()){
            salas.add(cursorParaSala(cursor));
        } else {
            return null;
        }
        while (cursor.moveToNext()){
            salas.add(cursorParaSala(cursor));
        }

        return salas;
    }

    public Sala cursorParaSala(Cursor cursor){
        Sala sala = new Sala();

        sala.setId(cursor.getInt(0));
        sala.setSala(cursor.getString(1));
        sala.setProfessor(cursor.getString(2));

        return sala;
    }

}
