package com.example.andre.lenguasnativascolombia;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by andre on 4/06/2017.
 */

public class AcercaDe extends AppCompatActivity{
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acercade);

        final Button botonRegre = (Button)findViewById(R.id.btnRegresar);
        botonRegre.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(AcercaDe.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
