package com.example.a93;

import android.content.ContentValues;
import android.content.Context;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import java.util.ArrayList;
import java.util.List;


public class AdminSQLite extends SQLiteOpenHelper {


    public AdminSQLite(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase BaseDeDatos) {
        BaseDeDatos.execSQL("CREATE TABLE planets(name text primary key)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public void insertarPlanetas(SQLiteDatabase baseDeDatos){

        String query = "SELECT * FROM planets";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.getCount() == 0){
            Planet planeta;
            ArrayList<Planet> listaPlanetas = new ArrayList<Planet>();

            planeta = new Planet();
            planeta.setName("Mercurio");
            listaPlanetas.add(planeta);

            planeta = new Planet();
            planeta.setName("Venus");
            listaPlanetas.add(planeta);

            planeta = new Planet();
            planeta.setName("Tierra");
            listaPlanetas.add(planeta);

            planeta = new Planet();
            planeta.setName("Marte");
            listaPlanetas.add(planeta);

            planeta = new Planet();
            planeta.setName("Jupiter");
            listaPlanetas.add(planeta);

            planeta = new Planet();
            planeta.setName("Saturno");
            listaPlanetas.add(planeta);

            planeta = new Planet();
            planeta.setName("Urano");
            listaPlanetas.add(planeta);

            planeta = new Planet();
            planeta.setName("Neptuno");
            listaPlanetas.add(planeta);


            String nombre;

            for(int i = 0; i < listaPlanetas.size(); i++){
                ContentValues registro = new ContentValues();
                nombre = listaPlanetas.get(i).getName();

                registro.put("name", nombre);

                baseDeDatos.insert("planets", null, registro);

            }

            baseDeDatos.close();
        }



    }


    public ArrayList<Planet> conseguirTodosLosPlanetas() {
        ArrayList<Planet> listaPlaneta = new ArrayList<Planet>();
        String query = "SELECT * FROM planets";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                Planet planet = new Planet();
                planet.setName(cursor.getString(0));
                listaPlaneta.add(planet);
            } while (cursor.moveToNext());
        }
        return listaPlaneta;
    }

    public void guardarPlaneta(SQLiteDatabase baseDeDatos, String nombre){

        ContentValues registro = new ContentValues();

        registro.put("name", nombre);

        baseDeDatos.insert("planets", null, registro);
        baseDeDatos.close();
    }

}