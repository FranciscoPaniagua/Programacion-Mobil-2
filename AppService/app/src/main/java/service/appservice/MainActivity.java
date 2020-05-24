package service.appservice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void iniciarServicio(View view){
Intent servicio=new Intent(this,Servicio.class);
startService(servicio);

    }
    public void detenerServicio(View view){
        Intent servicio=new Intent(this,Servicio.class);
        stopService(servicio);
    }
}
