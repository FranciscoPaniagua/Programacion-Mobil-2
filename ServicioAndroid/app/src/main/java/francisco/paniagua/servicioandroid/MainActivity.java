package francisco.paniagua.servicioandroid;

import android.content.Intent;
import android.media.MediaPlayer;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button iniciar,parar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iniciar=(Button)findViewById(R.id.btnIniciar);
        parar=(Button)findViewById(R.id.btnParar);

        iniciar.setOnClickListener(this);
        parar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v==iniciar){
            startService(new Intent(this,Servicio.class));
        }else if(v==parar){
            stopService(new Intent(this,Servicio.class));
        }
    }
}
