package francisco.p.appenviosrecibos;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.provider.Contacts;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
public static ArrayList<String>telefonos,emails,nombres;
public static ArrayList<Contacto>contactos;
    private Button   btnReadGmail;
    private TextView txtGmailContent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Agregado para correos.
        this.txtGmailContent = (TextView) this.findViewById(R.id.txtEmails);
        this.btnReadGmail = (Button) this.findViewById(R.id.cmdReadMails);
        //Agregado (ya sirve).
        Bundle extras=getIntent().getExtras();
        if(extras!=null){
            String nombre=extras.getString("MessageNombre");
            String message=extras.getString("Message");
            TextView mensaje=(TextView)(findViewById(R.id.txt_recibido));
            mensaje.setText("De: "+nombre+"\n"+"Mensaje:\n"+message);
            Toast.makeText(this, nombre, Toast.LENGTH_SHORT).show();
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }

        //Bien
        telefonos=new ArrayList<>();
        emails=new ArrayList<>();
        nombres=new ArrayList<>();
        contactos=new ArrayList<>();
        ContentResolver resolver=getContentResolver();
        Cursor cursor =resolver.query(ContactsContract.Contacts.CONTENT_URI,null,null,null,null);
        while(cursor.moveToNext()){
            String id=cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
            String nombre=cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
            this.nombres.add(nombre);
            Cursor telefonos=resolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,
                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID+" = ?",new String[]{id},null);
            String telefono=null;
            while(telefonos.moveToNext()){
                telefono=telefonos.getString(telefonos.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                this.telefonos.add(telefono);
            }

            Cursor emails=resolver.query(ContactsContract.CommonDataKinds.Email.CONTENT_URI,null,
                    ContactsContract.CommonDataKinds.Email.CONTACT_ID+" = ?",new String[]{id},null);
            String email=null;
            while(emails.moveToNext()){
                email=emails.getString(emails.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));
                this.emails.add(email);
            }
            this.contactos.add(new Contacto(nombre,telefono,email));
        }

        Button btnEmail=(Button)findViewById(R.id.btnEmail);
        btnEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salirEmail();
            }
        });
    }

    public void salirEmail(){
        Intent irEmail=new Intent(this,EnviarEmail.class);
        startActivity(irEmail);
        //Toast.makeText(this, this.emails.get(0), Toast.LENGTH_SHORT).show();
    }

    //Agregado

}
