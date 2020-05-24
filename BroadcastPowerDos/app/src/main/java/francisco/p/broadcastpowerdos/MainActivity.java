package francisco.p.broadcastpowerdos;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView tvTexto;
    private IntentFilter intentFilter;
    private CargarBroadcastReceiver cargaBroadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvTexto=(TextView)findViewById(R.id.tvTexto);
        intentFilter=new IntentFilter();
        cargaBroadcastReceiver=new CargarBroadcastReceiver();
        intentFilter.addAction(Intent.ACTION_POWER_CONNECTED);
        intentFilter.addAction(Intent.ACTION_POWER_DISCONNECTED);
    }

    private class CargarBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action=intent.getAction();
            boolean cargando=(action.equals(Intent.ACTION_POWER_CONNECTED));
            showCargando(cargando);
        }
    }
    private void showCargando(boolean cargando){
        if(cargando){
            tvTexto.setText("Cargando");
        }else{
            tvTexto.setText("Desconectado");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(cargaBroadcastReceiver,intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(cargaBroadcastReceiver);
    }
}
