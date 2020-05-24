package com.example.micontentprovider;

import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditNombreAcitivity extends AppCompatActivity {

    private static final String LOG_TAG = EditNombreAcitivity.class.getSimpleName();

    public static final String EXTRA_RESTPUESTA = "com.example.micontentprovider.RESPUESTA";

    int id = 0;
    int action;
    EditText etEditNombre;
    Button btnAction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_nombre_acitivity);
        etEditNombre = (EditText) findViewById(R.id.tv_edit_nombre);
        btnAction = (Button) findViewById(R.id.btn_actualizar);
        Intent intent = getIntent();

        action = intent.getIntExtra("ACTION", 0);
        if(action == 0) {
            etEditNombre.setText(
                    intent.getStringExtra(AsignaturaAdapter.EXTRA_NOMBRE));

            id = intent.getIntExtra(AsignaturaAdapter.EXTRA_ID, 0);
        } else {
            btnAction.setText("Agregar");
        }
    }

    public void onClickActualizar(View view) {
        String nombre = etEditNombre.getText().toString();
        if(action == 0) {
            Intent intent = new Intent();
            intent.putExtra(EXTRA_RESTPUESTA, nombre);
            intent.putExtra(AsignaturaAdapter.EXTRA_ID, id);

            setResult(RESULT_OK, intent);
            finish();
        }else {
            ContentValues values = new ContentValues();
            values.put(Contract.Asignaturas.NOMBRE, nombre);

            Uri insertar = getContentResolver().
                    insert(Contract.CONTENT_URI,values);

            Intent intent = new Intent();
            intent.putExtra(EXTRA_RESTPUESTA, nombre);
            setResult(RESULT_OK, intent);

            finish();
        }
    }
}
