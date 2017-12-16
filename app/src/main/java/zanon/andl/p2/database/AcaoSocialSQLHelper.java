package zanon.andl.p2.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Classe responsável por criar o banco de dados
 * Created by Andre on 16/12/2017.
 */

public class AcaoSocialSQLHelper extends SQLiteOpenHelper {

    //define nome do banco de dados e versao
    private static final String NOME_BD = "bd_Acoes";
    private static final int VERSAO_BANCO = 1;

    //define nome da tabela e nome das colunas conforme a classe AcaoSocialEntity
    public static final String TABELA_ACOESSOCIAIS = "acoes";
    public static final String COLUNA_ID = "id";
    public static final String COLUNA_NOME = "nome";
    public static final String COLUNA_IMAGEM = "imagem";
    public static final String COLUNA_DESCRICAO = "descricao";
    public static final String COLUNA_SITE = "site";

    /**
     *
     * @param context da atividade, necessario para inserir no banco de dados
     */
    public AcaoSocialSQLHelper(Context context){
        super(context, NOME_BD, null, VERSAO_BANCO);
    }

    /**
     * cria a tabela
     * @param db banco de dados
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABELA_ACOESSOCIAIS + " (" +
                COLUNA_ID + " INTEGER PRIMARY KEY NOT NULL," +
                COLUNA_NOME + " TEXT NOT NULL, " +
                COLUNA_IMAGEM + " TEXT NOT NULL, " +
                COLUNA_DESCRICAO + " TEXT NOT NULL, " +
                COLUNA_SITE + " TEXT NOT NULL)");
    }

    /**
     * Para caso de atualizacoes no banco de dados
     * @param db banco de dados a ser atualizado
     * @param oldVersion numero da versao antiga
     * @param newVersion numero da versao nova
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " +TABELA_ACOESSOCIAIS);
        onCreate(db);
    }
}
