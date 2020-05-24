package p.gonzalez.brujula;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    ImageView fondo,flecha;
    TextView grados;

    private static SensorManager sensorService;
    private Sensor sensor;

    private float gradoActual=0f;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fondo=(ImageView)findViewById(R.id.iv_fondo);
        //flecha=(ImageView)findViewById(R.id.iv_flecha);
        grados=(TextView)findViewById(R.id.grados);

        sensorService=(SensorManager)getSystemService(Context.SENSOR_SERVICE);
        sensor=sensorService.getDefaultSensor(Sensor.TYPE_ORIENTATION);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if(sensor!=null){
            sensorService.registerListener(this,sensor,SensorManager.SENSOR_DELAY_FASTEST);
        }else{
            Toast.makeText(this, "No es soportado", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorService.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        int grado=Math.round(event.values[0]);

        grados.setText(Integer.toString(grado)+(char)0x00B0);
        RotateAnimation ra=new RotateAnimation(gradoActual,-grado, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        ra.setDuration(1000);
        ra.setFillAfter(true);
        fondo.startAnimation(ra);
        gradoActual=-grado;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
