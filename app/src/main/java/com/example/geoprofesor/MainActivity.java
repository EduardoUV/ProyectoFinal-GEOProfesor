package com.example.geoprofesor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    String[] opciones = {"Selecciona una opcion...", "Profesor", "Alumno"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinner = (Spinner) findViewById(R.id.spinner1);
        Button boton2 = (Button) findViewById(R.id.boton2);
        final TextView c = (TextView) findViewById(R.id.CAJA);

        /* CONFIGURACION DEL SPINNER*/
        ArrayAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, opciones);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {
                    case 1:
                        ActividadSig(view, position);
                        //c.setText(" " + opciones[position]); profesor

                        break;
                    case 2:
                        ActividadSig(view, position);
                        // c.setText(" " + opciones[position]);  alumno

                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


        /*opciones.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String val = adapter.getItem(position).toString();
                CAJA.setText(val);
            }
        });
    */


    /* BOTON DE ACTIVIDAD SIGUIENTE*/
    public void ActividadSig(View view, int position) {
        Intent intent = null;
        if (position == 1) {
            intent = new Intent(this, MapsActivity.class);
        }
        if (position == 2) {
            intent = new Intent(this, SecondActivity.class);
        }
        startActivity(intent);
    }


    /* BOTON DE SALIR*/
    public void CerraApp(View v) {
        finish();
    }
}

