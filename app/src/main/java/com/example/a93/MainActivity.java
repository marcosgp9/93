package com.example.a93;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    ArrayList<Planet> listaPlanetas;
    String[] nombrePlanetas;
    private RecyclerView.LayoutManager layoutManager;
    private LinkedList<String> mWorldLinked = new LinkedList<String>();
    Button dialog;
    Dialog thisDialog;
    String nom;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        insertarPlanetesABaseDeDades();
        conseguirPlanetas();
        nombrePlanetas = conseguirNombrePlanetas(listaPlanetas);

        final RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        for(int i = 0; i < listaPlanetas.size(); i++){
            this.mWorldLinked.add(listaPlanetas.get(i).getName());
        }

        RecyclerView.Adapter mAdapter = new WordListAdapter(this, mWorldLinked);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        dialog = (Button)findViewById(R.id.guardarNuevoPlaneta);

        dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                thisDialog = new Dialog(MainActivity.this);
                thisDialog.setContentView(R.layout.dialog_signin);
                final EditText nam = (EditText)thisDialog.findViewById(R.id.name);
                Button savePlanet = (Button)thisDialog.findViewById(R.id.guardar);

                nam.setEnabled(true);
                savePlanet.setEnabled(true);
                thisDialog.show();

                savePlanet.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        nom = nam.getText().toString();
                        thisDialog.cancel();

                        guardarNuevoPlaneta(nom);
                        conseguirPlanetas();
                        cargar(mRecyclerView);
                    }
                });

            }
        });



    }

    public void insertarPlanetesABaseDeDades(){
        AdminSQLite admin = new AdminSQLite(this, "administracion", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        admin.insertarPlanetas(BaseDeDatos);
    }

    public void conseguirPlanetas(){
        AdminSQLite admin = new AdminSQLite(this, "administracion", null, 1);
        listaPlanetas = admin.conseguirTodosLosPlanetas();
    }

    public String[] conseguirNombrePlanetas(ArrayList<Planet> planetas){

        String[] planeta = new String[planetas.size()];

        for(int i = 0; i < planetas.size(); i++){
            planeta[i] = planetas.get(i).getName();
        }

        return planeta;

    }

    public void guardarNuevoPlaneta(String nombre){
        AdminSQLite admin = new AdminSQLite(this, "administracion", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        admin.guardarPlaneta(BaseDeDatos, nombre);
    }

    public void cargar(RecyclerView mRecyclerView){

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);

            this.mWorldLinked.add(listaPlanetas.get(listaPlanetas.size()-1).getName());

        RecyclerView.Adapter mAdapter = new WordListAdapter(this, mWorldLinked);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }



}