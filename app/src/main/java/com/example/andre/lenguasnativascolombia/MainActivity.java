package com.example.andre.lenguasnativascolombia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.andre.lenguasnativascolombia.lenguasnativas.ApiService;
import com.example.andre.lenguasnativascolombia.models.Lenguas;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    //Variables para retrofit y para el TAG del mensaje
    private Retrofit retrofit;
    public final static String TAG="OpenData";
    private EditText etDatos;
    private TextView tvLengua;
    private TextView tvDepar;
    private TextView tvNoHab;
    private TextView tvDescrip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etDatos = (EditText) findViewById(R.id.etDato);
        tvLengua = (TextView) findViewById(R.id.tvNombreLengua);
        tvDepar = (TextView) findViewById(R.id.tvDepartamento);
        tvNoHab = (TextView) findViewById(R.id.tvNumHabit);
        tvDescrip = (TextView) findViewById(R.id.tvDescrip);
        final Button botonInfo = (Button)findViewById(R.id.btnAcerca);
        botonInfo.setOnClickListener(new View.OnClickListener() {
                                         public void onClick(View v) {
                                             Intent intent = new Intent(MainActivity.this, AcercaDe.class);
                                             startActivity(intent);
                                         }
                                     });
        retrofit = new Retrofit.Builder()
                .baseUrl("https://www.datos.gov.co/resource/")//URL BASE DE DONDE VAMOS A OBTENER EL LISTADO DE POEMONS
                .addConverterFactory(GsonConverterFactory.create())//SE FORMATEAN LAS RESUESTAS DE COMO LLEGAN EN JASON
                .build();
            }


    private void obtenerDatos() {
        ApiService service = retrofit.create(ApiService.class);
        Call<List<Lenguas>> departamentoRespuestaCall = service.obtenerListaDepartamentos();

        departamentoRespuestaCall.enqueue(new Callback<List<Lenguas>>() {
            @Override
            public void onResponse(Call<List<Lenguas>> call, Response<List<Lenguas>> response) {
                if (response.isSuccessful()) {
                    List lista = response.body();

                    String dato = etDatos.getText().toString();
                    for (int i = 0; i < lista.size(); i++) {
                        Lenguas p = (Lenguas) lista.get(i);
                        if (dato.equalsIgnoreCase(p.getDepartamento())){
                            tvDepar.setText("Departamento: " + p.getDepartamento() + ".");
                            tvNoHab.setText("Número de Habitantes: " + p.getNMeroDeHabitantes()+ ".");
                            tvLengua.setText("Lengua de la Regíon: " + p.getNombreDeLengua()+ ".");
                            tvDescrip.setText("Descripción: " + p.getDescripciNDeLengua()+ ".");
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Lenguas>> call, Throwable t) {
                Log.e(TAG, " onFailure: " + t.getMessage());
            }
        });
    }

    public void hola(View view){
        obtenerDatos();
    }
}
