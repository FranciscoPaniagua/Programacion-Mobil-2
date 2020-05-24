package francisco.p.broadcastpower;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class EjemmploBroadcastReciver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if(Intent.ACTION_POWER_CONNECTED.equals(intent.getAction())){
            Toast.makeText(context, "Conectado: Cargando...", Toast.LENGTH_SHORT).show();
        }
        if(Intent.ACTION_POWER_DISCONNECTED.equals(intent.getAction())){
            Toast.makeText(context, "Desconectado: Sin cargar...", Toast.LENGTH_SHORT).show();
        }
    }
}
