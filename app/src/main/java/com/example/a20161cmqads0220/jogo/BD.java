package com.example.a20161cmqads0220.jogo;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class BD  extends SQLiteOpenHelper{

    private final String TABELA_JOGA = "tabela_jogador";
    private final String TABELA_BIXO = "tabela_bixos";
    private final String TABELA_INTE = "tabela_Integracao";

    private final String COLUNA_ID = "id";
    private final String COLUNA_NOME = "nome";
    private final String COLUNA_APELIDO = "apelido";
    private final String COLUNA_EMAIL = "email";
    private final String COLUNA_SENHA = "senha";
    private final String COLUNA_PILAS = "pilas";
    private final String COLUNA_VIDA = "vida";
    private final String COLUNA_ATAQUE = "ataque";
    private final String COLUNA_DEFESA = "defesa";
    private final String COLUNA_VELOCIDADE = "velocidade";
    private final String COLUNA_ID_JOGA = "idjoga";
    private final String COLUNA_ID_BIXO = "idbixo";



    public BD(Context context,String nome, int versao){
        super(context,nome,null,versao);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABELA_JOGA + " ("
                + COLUNA_ID         + "INTEGER PRIMARY KEY autoincrement, "
                + COLUNA_NOME       + " varchar(50) NOT NULL, "
                + COLUNA_APELIDO    + " varchar(20) NOT NULL, "
                + COLUNA_EMAIL      + " varchar(20) NOT NULL, "
                + COLUNA_SENHA      + " varchar(20) NOT NULL, "
                + COLUNA_PILAS      + " INTEGER );");
        sqLiteDatabase.execSQL("CREATE TABLE " + TABELA_BIXO + " ("
                + COLUNA_ID         + "INTEGER PRIMARY KEY autoincrement, "
                + COLUNA_NOME       + " varchar(50) NOT NULL, "
                + COLUNA_VIDA       + " INTEGER, "
                + COLUNA_ATAQUE     + " INTEGER, "
                + COLUNA_DEFESA     + " INTEGER, "
                + COLUNA_VELOCIDADE + " INTEGER, "
                + COLUNA_PILAS      + " INTEGER);");
        sqLiteDatabase.execSQL("CREATE TABLE " + TABELA_INTE + " ("
                + COLUNA_ID         + " INTEGER PRIMARY KEY autoincrement, "
                + COLUNA_ID_JOGA    + " INTEGER, "
                + COLUNA_ID_BIXO    + " INTEGER, "
                + "FOREIGN KEY(" + COLUNA_ID_JOGA + ") REFERENCES "
                    + TABELA_JOGA + "(" + COLUNA_ID + "),"
                + "FOREIGN KEY(" + COLUNA_ID_BIXO + ") REFERENCES "
                    + TABELA_BIXO + "(" + COLUNA_ID + "));");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int versaoAntiga, int versaoNova) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABELA_JOGA);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABELA_BIXO);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABELA_INTE);
        onCreate(sqLiteDatabase);
    }

    public ArrayList<Jogador> lerJogadores(){
        Cursor cursor = this.getWritableDatabase().query(TABELA_JOGA,
                new String[]{COLUNA_ID, COLUNA_NOME, COLUNA_APELIDO, COLUNA_EMAIL, COLUNA_SENHA},
                null, null, null, null, null);

        ArrayList<Jogador> jogadores = new ArrayList<>();

        cursor.close();
        return jogadores;
    }


}
