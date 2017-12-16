package zanon.andl.p2.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import zanon.andl.p2.entity.AcaoSocialEntity;

/**
 * Calsse responsavel por inserir, remover e acessar o banco de dados
 * Created by Andre on 16/12/2017.
 */

public class AcaoSocialRepositorio {

    //entidade helper para criar o banco de dados
    private AcaoSocialSQLHelper helper;

    /**
     * Construtor padrao
     * @param context necessario para as operacoes no BD
     */
    public AcaoSocialRepositorio(Context context){
        helper = new AcaoSocialSQLHelper(context);
    }

    /**
     *
     * @param acaoSocialEntity objeto a ser inserido
     * @return long id que e a chave primaria do objeto
     */
    public long inserir(AcaoSocialEntity acaoSocialEntity){
        //comandos iguais ao explicado na documentacao do Android
        SQLiteDatabase bd = helper.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(AcaoSocialSQLHelper.COLUNA_NOME, acaoSocialEntity.getName());
        cv.put(AcaoSocialSQLHelper.COLUNA_IMAGEM, acaoSocialEntity.getImage());
        cv.put(AcaoSocialSQLHelper.COLUNA_DESCRICAO, acaoSocialEntity.getDescription());
        cv.put(AcaoSocialSQLHelper.COLUNA_SITE, acaoSocialEntity.getSite());

        long id = bd.insert(AcaoSocialSQLHelper.TABELA_ACOESSOCIAIS, null, cv);
        bd.close();

        return id;
    }

    /**
     * Insere uma lista de valores no banco de dados
     * @param list a ser inserida
     */
    public void inserirLista(List<AcaoSocialEntity> list){
        for(AcaoSocialEntity acaoSocialEntity : list){
            inserir(acaoSocialEntity);
        }
    }

    /**
     * exclui todos os valores do banco de dados
     * vai executar essa operacao sempre que tiver conectividade com a internet
     */
    public void excluiBD(){
        //comando sql parecido com PL/SQL
        SQLiteDatabase db = helper.getWritableDatabase();
        String DELETE = "DELETE FROM " + AcaoSocialSQLHelper.TABELA_ACOESSOCIAIS + " ;";
        db.execSQL(DELETE);
    }

    /**
     *
     * @param list que recebera todos os objetos do BD
     */
    public void listaAcoes(List<AcaoSocialEntity> list){
        //acessa o banco de dados para modifica-lo
        SQLiteDatabase bd = helper.getReadableDatabase();

        //limpa a lista
        list.clear();

        try{
            //operacao sql, parecida com PL/SQL
            String sql = "SELECT * FROM " + AcaoSocialSQLHelper.TABELA_ACOESSOCIAIS;
            sql += " ORDER BY " + AcaoSocialSQLHelper.COLUNA_NOME;

            //cria cursor para passar por todas as linhas da tabela
            Cursor cursor = bd.rawQuery(sql, null);
            if(cursor != null && cursor.moveToFirst()){
                do{
                    //acessa todos os campos da tabela
                    int id = cursor.getInt(cursor.getColumnIndex(AcaoSocialSQLHelper.COLUNA_ID));
                    String nome = cursor.getString(cursor.getColumnIndex(AcaoSocialSQLHelper.COLUNA_NOME));
                    String imagem = cursor.getString(cursor.getColumnIndex(AcaoSocialSQLHelper.COLUNA_IMAGEM));
                    String descricao = cursor.getString(cursor.getColumnIndex(AcaoSocialSQLHelper.COLUNA_DESCRICAO));
                    String site = cursor.getString(cursor.getColumnIndex(AcaoSocialSQLHelper.COLUNA_SITE));

                    //cria novo objeto
                    AcaoSocialEntity novaAcao = new AcaoSocialEntity(id, nome, imagem, descricao, site);
                    list.add(novaAcao);

                }while (cursor.moveToNext());
            }

            cursor.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        bd.close();
    }
}
