package br.estacio.viagens;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class ViagemDAO extends SQLiteOpenHelper {
    private static final String BANCO = "Biblioteca";
    private static final int VERSAO = 2;

    public ViagemDAO(Context context){
        super(context, BANCO, null, VERSAO);
    }


    @Override
    public void onCreate(SQLiteDatabase db){
        String sql = "CREATE TABLE VIAGENS ( ID INTEGER PRIMARY KEY, " +
                "DESTINO TEXT NOT NULL, " +
                "DIARIO TEXT NOT NULL," +
                "NOTA REAL NOT NULL)";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        String sql = "DROP TABLE IF EXISTS VIAGENS;";
        db.execSQL(sql);
        onCreate(db);
    }
    public void gravaViagem(Viagem viagem){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = new ContentValues();
        dados.put("DESTINO", viagem.getDestino());
        dados.put("DIARIO", viagem.getDiario());
        dados.put("NOTA", viagem.getNota());

        db.insert("viagens", null, dados);
        db.close();
    }

    public List<Viagem> buscaViagem(){
        String sql = "SELECT * FROM VIAGENS";
        SQLiteDatabase db = getReadableDatabase();
        List<Viagem> viagens = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, null);


        while(cursor.moveToNext()){
            Viagem viagem = new Viagem();
            viagem.setId(cursor.getLong(cursor.getColumnIndex("ID")));
            viagem.setDestino(cursor.getString(cursor.getColumnIndex("DESTINO")));
            viagem.setDiario(cursor.getString(cursor.getColumnIndex("DIARIO")));
            viagem.setNota(cursor.getDouble(cursor.getColumnIndex("NOTA")));

            viagens.add(viagem);
        }
        cursor.close();
        db.close();
        return viagens;
    }

    public void alteraViagem(Viagem viagem){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = new ContentValues();
        dados.put("DESTINO", viagem.getDestino());
        dados.put("DIARIO", viagem.getDestino());
        dados.put("NOTA", viagem.getNota());

        String filtro = "ID = ?";
        String[] parametro = {viagem.getId().toString()};
        db.update("viagens", dados, filtro, parametro);
        db.close();
    }

    public void apagaViagem(Viagem viagem){
        SQLiteDatabase db = getWritableDatabase();
        String filtro = "ID + ? ";
        String[] paramtro = {viagem.getId().toString()};
        db.delete("viagens", filtro, paramtro);
        db.close();
    }


}
