package com.ditec.migranparte6;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class InsertarCliente extends AppCompatActivity {

    private EditText apellido;
    private Spinner sexo;
    private EditText nombre;
    private EditText telefono;
    private EditText direccion;
    private int isexo;
    HttpURLConnection conexion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar_cliente);
        nombre = (EditText) findViewById(R.id.nombre);
        direccion = (EditText) findViewById(R.id.direccion);
        telefono = (EditText) findViewById(R.id.telefono);
        apellido = (EditText) findViewById(R.id.apellido);
        sexo = (Spinner) findViewById(R.id.sexo);
    }

    public void Insertar(View view){
        isexo=(sexo.getSelectedItem().toString().equals("Masculino"))?0:1;
        try {
            String miurl= this.getString(R.string.dominio)+this.getString(R.string.insertarcliente)
                    + "nombre="+ URLEncoder.encode(nombre.getText().toString(),"UTF-8") + "&apellido="
                    + URLEncoder.encode(apellido.getText().toString(), "UTF-8") + "&sexo="
                    + isexo+"&telefono="+ URLEncoder.encode(telefono.getText().toString(), "UTF-8")
                    + "&direccion="+ URLEncoder.encode(direccion.getText().toString(), "UTF-8");
            URL url=new URL(miurl);
            Log.e("mierror",miurl);
            conexion = (HttpURLConnection) url
                    .openConnection();
            if (conexion.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
                String linea = reader.readLine();
                if (!linea.equals("OK\\n")) {
                    Log.e("mierror","Error en servicio Web nueva");
                }
                else
                {
                    Log.e("mierror","No hay error");
                    Toast.makeText(this, "Inserción exitosa", Toast.LENGTH_SHORT).show();
                    finish();
                }
            } else {
                Log.e("mierror", conexion.getResponseMessage());
            }
        } catch (Exception e) { Log.e("mierror", e.getMessage(), e);
        } finally {  if (conexion!=null) conexion.disconnect();
        }
    }
}