package francisco.p.cuentapasos;

import android.app.Activity;
import android.content.Context;
import android.hardware.*;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;

public class MainActivity extends Activity  implements SensorEventListener{
    private SensorManager sensorManager;
    private TextView count;
    private TextView valores;
    boolean activityRunning;
    Sensor countSensor;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        countSensor=sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        List<Sensor> sensorList = sensorManager.getSensorList(Sensor.TYPE_ALL);

        StringBuilder sensorTexto = new StringBuilder();

        for (Sensor sensorActual : sensorList) {
            sensorTexto.append(sensorActual.getName()).append(
                    System.getProperty("line.separator"));
        }

        textView = (TextView) findViewById(R.id.lista_sensores);
        valores=(TextView)findViewById(R.id.valores);
        //textView.setText(sensorTexto);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //activityRunning = true;
        countSensor=sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if (countSensor != null) {
            sensorManager.registerListener(this, countSensor, SensorManager.SENSOR_DELAY_FASTEST);
        } else {
            Toast.makeText(this, "Count sensor not available!", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this,countSensor);
        // if you unregister the last listener, the hardware will stop detecting step events
        // sensorManager.unregisterListener(this);
    }

    long steps=0;

    @Override
    public void onSensorChanged(SensorEvent event) {
        /*if (activityRunning) {
            count.setText(String.valueOf(event.values[0]));
        }*/
        Sensor sensor = event.sensor;
        float[] values = event.values;
        int value = -1;

        if (values.length > 0) {
            value = (int) values[0];
        }

        if (sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            steps++;
        }
       textView.setText("Distancia recorrida: "+Math.round(getDistanceRun(steps))+"m");
        valores.setText("X: "+event.values[0]+", Y: "+event.values[1]+", Z: "+event.values[2]);
    }

    public float getDistanceRun(long steps){
        float distance = (float)(steps*1250)/(float)100000;
        return distance;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
