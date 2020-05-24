package francisco.p.acelerometro;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private SensorManager sensorManager;
    Sensor acelerometro;
    //Sensor contar;
    ImageView fondo;
    TextView texto;
    boolean correr=false;
    private float Xactual=0f;
    private float Yactual=0f;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fondo=(ImageView)findViewById(R.id.iv_fondo);
        texto=(TextView)findViewById(R.id.grados);
        sensorManager=(SensorManager) getSystemService(Context.SENSOR_SERVICE);
        acelerometro=sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this,acelerometro,SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        correr=false;
        sensorManager.unregisterListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        correr=true;
        Sensor contar=sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        if(contar!=null){
            sensorManager.registerListener(this,contar,SensorManager.SENSOR_DELAY_UI);
        }else{
            Toast.makeText(this, "Sensor no disponible", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float X=event.values[0];
        float Y=event.values[1];
        float Z=event.values[2];
        //if(correr){
            texto.setText(String.valueOf(Math.round(event.values[0])));
        //}
    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
