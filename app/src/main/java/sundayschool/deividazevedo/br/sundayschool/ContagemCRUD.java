package sundayschool.deividazevedo.br.sundayschool;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DVD on 14/05/2015.
 */
public class ContagemCRUD {

    private Context context;

    public ContagemCRUD(Context context) {
        SundaySchoolBD.getInstancia(context);
        this.context = context;
    }

    public void criar(Contagem contagem){

        ContentValues values = new ContentValues();
        values.put(SundaySchoolBD.CONTAGEM_COLS[1], contagem.getPresentes());
        values.put(SundaySchoolBD.CONTAGEM_COLS[2], contagem.getVisitantes());
        values.put(SundaySchoolBD.CONTAGEM_COLS[3], contagem.getBiblias());
        values.put(SundaySchoolBD.CONTAGEM_COLS[4], contagem.getNomeSala());
        values.put(SundaySchoolBD.CONTAGEM_COLS[5], contagem.getIdRelatorio());

        SundaySchoolBD.getInstancia(context).getWritableDatabase()
                .insert(SundaySchoolBD.CONTAGEM_TB, null, values);


    }

    public void remover(Contagem contagem){

        int idContagem = contagem.getId();
        SundaySchoolBD.getInstancia(context).getWritableDatabase()
                .delete(SundaySchoolBD.CONTAGEM_TB,
                        SundaySchoolBD.CONTAGEM_COLS[0]+ " = "+ idContagem, null);

    }

    public List<Contagem> buscarPorIdDoRelatorio(int idRelatorio){
        Cursor cursor = SundaySchoolBD.getInstancia(context).getReadableDatabase().
                query(SundaySchoolBD.CONTAGEM_TB,
                        SundaySchoolBD.CONTAGEM_COLS,
                        SundaySchoolBD.CONTAGEM_COLS[5]+ " = "+ idRelatorio,
                        null, null, null, null,null);

        ArrayList<Contagem> contagens = new ArrayList<>();

        if(cursor.moveToFirst()){
            contagens.add(cursorParaContagem(cursor));
        } else {
            return null;
        }
        while (cursor.moveToNext()){
            contagens.add(cursorParaContagem(cursor));
        }

        return contagens;
    }


    public Contagem cursorParaContagem(Cursor cursor){
        Contagem contagens = new Contagem();

        contagens.setId(cursor.getInt(0));
        contagens.setPresentes(Integer.parseInt(cursor.getString(1)));
        contagens.setVisitantes(Integer.parseInt(cursor.getString(2)));
        contagens.setBiblias(Integer.parseInt(cursor.getString(3)));
        contagens.setNomeSala(cursor.getString(4));
        contagens.setIdRelatorio(Integer.parseInt(cursor.getString(5)));

        return contagens;
    }

}
