package zanon.andl.p2.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Andre on 16/12/2017.
 */

public class AcaoSocialSQLHelper extends SQLiteOpenHelper {

    private static final String NOME_BD = "bd_Acoes";
    private static final int VERSAO_BANCO = 1;

    public static final String TABELA_ACOESSOCIAIS = "acoes";
    public static final String COLUNA_ID = "id";
    public static final String COLUNA_NOME = "nome";
    public static final String COLUNA_IMAGEM = "imagem";
    public static final String COLUNA_DESCRICAO = "descricao";
    public static final String COLUNA_SITE = "site";

    public AcaoSocialSQLHelper(Context context){
        super(context, NOME_BD, null, VERSAO_BANCO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABELA_ACOESSOCIAIS + " (" +
                COLUNA_ID + " INTEGER PRIMARY KEY NOT NULL," +
                COLUNA_NOME + " TEXT NOT NULL, " +
                COLUNA_IMAGEM + " TEXT NOT NULL, " +
                COLUNA_DESCRICAO + " TEXT NOT NULL, " +
                COLUNA_SITE + " TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " +TABELA_ACOESSOCIAIS);
        onCreate(db);
    }
}
