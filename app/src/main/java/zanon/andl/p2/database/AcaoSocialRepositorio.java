package zanon.andl.p2.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import zanon.andl.p2.entity.AcaoSocialEntity;
import zanon.andl.p2.entity.AcaoSocialListEntity;

/**
 * Created by Andre on 16/12/2017.
 */

public class AcaoSocialRepositorio {

    private AcaoSocialSQLHelper helper;

    public AcaoSocialRepositorio(Context context){
        helper = new AcaoSocialSQLHelper(context);
    }

    public long inserir(AcaoSocialEntity acaoSocialEntity){
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

    public void inserirLista(List<AcaoSocialEntity> list){
        for(AcaoSocialEntity acaoSocialEntity : list){
            inserir(acaoSocialEntity);
        }
    }

    public void excluiBD(){
        SQLiteDatabase db = helper.getWritableDatabase();
        String DELETE = "DELETE FROM " + AcaoSocialSQLHelper.TABELA_ACOESSOCIAIS + " ;";
        db.execSQL(DELETE);
    }

    public void listaAcoes(List<AcaoSocialEntity> list){
        SQLiteDatabase bd = helper.getReadableDatabase();

        list.clear();

        try{
            String sql = "SELECT * FROM " + AcaoSocialSQLHelper.TABELA_ACOESSOCIAIS;
            sql += " ORDER BY " + AcaoSocialSQLHelper.COLUNA_NOME;

            Cursor cursor = bd.rawQuery(sql, null);
            if(cursor != null && cursor.moveToFirst()){
                do{
                    int id = cursor.getInt(cursor.getColumnIndex(AcaoSocialSQLHelper.COLUNA_ID));
                    String nome = cursor.getString(cursor.getColumnIndex(AcaoSocialSQLHelper.COLUNA_NOME));
                    String imagem = cursor.getString(cursor.getColumnIndex(AcaoSocialSQLHelper.COLUNA_IMAGEM));
                    String descricao = cursor.getString(cursor.getColumnIndex(AcaoSocialSQLHelper.COLUNA_DESCRICAO));
                    String site = cursor.getString(cursor.getColumnIndex(AcaoSocialSQLHelper.COLUNA_SITE));

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
