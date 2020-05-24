package p.gonzalez.linterna1;

import android.content.Context;
import android.hardware.Camera;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    SensorManager sensorMgr ;
    Sensor acelerometro;
    Camera camera;
    int contador=0;
    private static final int SHAKE_THRESHOLD = 800;
    private float x=-1.0f,y=-1.0f,z=-1.0f,last_x=0f,last_y=0f,last_z=0f;
    private long lastUpdate=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sensorMgr = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        acelerometro=sensorMgr.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorMgr.registerListener(this,
                acelerometro,
                SensorManager.SENSOR_DELAY_GAME);
    }



    @Override
    public void onSensorChanged(SensorEvent event) {
        Sensor sensor=event.sensor;
            long curTime = System.currentTimeMillis();
            // only allow one update every 100ms.
            if ((curTime - lastUpdate) > 100) {
                long diffTime = (curTime - lastUpdate);
                lastUpdate = curTime;
                x = event.values[SensorManager.DATA_X];
                y = event.values[SensorManager.DATA_Y];
                z = event.values[SensorManager.DATA_Z];
                float speed = Math.abs(x+y+z - last_x - last_y - last_z) / diffTime * 10000;
                if (speed > SHAKE_THRESHOLD) {
                    Log.d("sensor", "shake detected w/ speed: " + speed);
                    Toast.makeText(this, "shake detected w/ speed: " + speed, Toast.LENGTH_SHORT).show();
                    if(contador==0){
                        try{
                            camera=Camera.open();
                            Camera.Parameters parameters=camera.getParameters();
                            parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                            camera.setParameters(parameters);
                            camera.startPreview();
                            contador=1;
                        }catch (Exception ex){

                        }


                    }else{
                        try{
                            camera=Camera.open();
                            Camera.Parameters parameters=camera.getParameters();
                            parameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
                            camera.setParameters(parameters);
                            camera.stopPreview();
                            camera.release();
                            contador=0;
                        }catch (Exception e){

                        }



                    }

                }
                last_x = x;
                last_y = y;
                last_z = z;
            }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
