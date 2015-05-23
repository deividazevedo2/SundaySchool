package sundayschool.deividazevedo.br.sundayschool;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by DVD on 23/04/2015.
 */
public class SundaySchoolBD extends SQLiteOpenHelper {

    //Nome e versão do Banco
    private final static int VERSAO = 1;
    private final static String NOME_BD = "sundaySchool.db";

    //Nomes das Tabelas
    public final static String CLASSE_TB = "classe_tb";
    public final static String RELATORIO_TB = "relatorio_tb";
    public final static String CONTAGEM_TB = "contagem_tb";

    //Colunas das Tabelas
    public final static String[] CLASSE_COLS = {"id", "nome_classe","nome_professor"};
    public final static String[] RELATORIO_COLS = {"id", "dia", "mes", "ano",
            "total_presentes", "total_biblias"};
    public final static String[] CONTAGEM_COLS = {"id", "presentes", "visitantes",
            "biblias", "nome_sala", "id_relatorio"};

    //DDL das Tabelas
    public final static String CRIA_TABELA_CONTAGEM = "CREATE TABLE " + CONTAGEM_TB + "(" +
            CONTAGEM_COLS[0] + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            CONTAGEM_COLS[1] + " INTEGER NOT NULL," +
            CONTAGEM_COLS[2] + " INTEGER NOT NULL," +
            CONTAGEM_COLS[3] + " INTEGER NOT NULL," +
            CONTAGEM_COLS[4] + " TEXT NOT NULL," +
            CONTAGEM_COLS[5] + " INTEGER NOT NULL," +
            "FOREIGN KEY (" + CONTAGEM_COLS[5] + ") REFERENCES " +
            RELATORIO_TB + " (" + RELATORIO_COLS[0] + "));";

    //DDL das Tabelas
    public final static String CRIA_TABELA_RELATORIO = "CREATE TABLE " + RELATORIO_TB + "(" +
            RELATORIO_COLS[0] + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            RELATORIO_COLS[1] + " INTEGER NOT NULL," +
            RELATORIO_COLS[2] + " INTEGER NOT NULL," +
            RELATORIO_COLS[3] + " INTEGER NOT NULL," +
            RELATORIO_COLS[4] + " INTEGER NOT NULL," +
            RELATORIO_COLS[5] + " INTEGER NOT NULL" + ");";

    public final static String CRIA_TABELA_CLASSE = "CREATE TABLE " + CLASSE_TB + "(" +
            CLASSE_COLS[0] + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            CLASSE_COLS[1] + " TEXT NOT NULL," +
            CLASSE_COLS[2] + " TEXT NOT NULL" + ");";

    //Variavel de Instancia
    private static SundaySchoolBD instancia;

    private SundaySchoolBD(Context context) {
        super(context, NOME_BD, null, VERSAO);
        Log.i("SUNDAY SCHOOL BD", "Banco criado ou acessado com sucesso");

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("PRAGMA foreign_keys = ON");
        Log.i("SUNDAY SCHOOL BD", "Chaves estrangeiras liberadas");

        db.execSQL(CRIA_TABELA_CLASSE);
        Log.i("SUNDAY SCHOOL BD", "Tabela classe");

        db.execSQL(CRIA_TABELA_RELATORIO);
        Log.i("SUNDAY SCHOOL BD", "Tabela relatorio");

        db.execSQL(CRIA_TABELA_CONTAGEM);
        Log.i("SUNDAY SCHOOL BD", "Tabela contagem");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            //Nesse método vc faz operações ao atualizar o esquema do banco
    }

    public static SundaySchoolBD getInstancia(Context context){

        if(instancia == null){
            instancia = new SundaySchoolBD(context);
        }
        return instancia;
    }
}
