package francisco.p.appenviosrecibos;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.net.sip.SipAudioCall;
import android.os.AsyncTask;
import android.provider.Telephony;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.Security;
import java.util.Properties;



import android.util.Log;

import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;

public class EnviarEmail extends AppCompatActivity {
    Button btnEnviar;
    ListView lvnombres;
    public String telefono, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enviar_email);
        btnEnviar = (Button) findViewById(R.id.btnEmail);
        lvnombres = (ListView) findViewById(R.id.lvnombres);
        ArrayAdapter<String> nombresAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, MainActivity.nombres);
        ArrayAdapter<Contacto> contactoAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, MainActivity.contactos);
        lvnombres.setAdapter(contactoAdapter);
        lvnombres.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Contacto c = MainActivity.contactos.get(position);
                telefono = c.getTelefono();
                email = c.getEmail();
                EnviarMensaje.telefono = c.getTelefono();
                EnviarMensaje.nombre = c.getNombre();
                Toast.makeText(EnviarEmail.this, "Contacto seleccionado: " + c.getNombre(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void enviar(View view) {
      /*  String mensaje="Hola :v";
        String enviador="perry.john62@gmail.com";
        Intent intent=new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL,new String[]{email});
        intent.putExtra(Intent.EXTRA_SUBJECT,enviador);
        intent.putExtra(Intent.EXTRA_TEXT,mensaje);
        intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent,"Escoge un email Client"));
        */
        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", email, null));
        intent.putExtra(Intent.EXTRA_SUBJECT, "App");
        startActivity(Intent.createChooser(intent, "Selecciona un Email Client"));
    }


    public void llamar(View view) {
        String numero = telefono;
        String uri = "tel:" + numero.trim();
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse(uri));
        startActivity(intent);
    }

    public void sms(View view) {
        Intent intent = new Intent(this, EnviarMensaje.class);
        startActivity(intent);
    }

    public void obteneremail(View view) {
       // try {
            /*//Set property values
            Properties propvals = new Properties();
            propvals.put("mail.pop3.host", "pop.gmail.com");
            propvals.put("mail.pop3.port", "995");
            propvals.put("mail.pop3.starttls.enable", "true");
            Session emailSessionObj = Session.getDefaultInstance(propvals);
            //Create POP3 store object and connect with the server
            Store storeObj = emailSessionObj.getStore("pop3s");
            storeObj.connect("pop.gmail.com", "perry.john62@gmail.com", "100mwaterresist");
            //Create folder object and open it in read-only mode
            Folder emailFolderObj = storeObj.getFolder("INBOX");
            emailFolderObj.open(Folder.READ_ONLY);
            //Fetch messages from the folder and print in a loop
            Message[] messageobjs = emailFolderObj.getMessages();


                Message indvidualmsg = messageobjs[0];
                System.out.println("Printing individual messages");
                System.out.println("No# " + (0 + 1));
                System.out.println("Email Subject: " + indvidualmsg.getSubject());
                System.out.println("Sender: " + indvidualmsg.getFrom()[0]);
                System.out.println("Content: " + indvidualmsg.getContent().toString());
            Toast.makeText(this, indvidualmsg.getContent().toString(), Toast.LENGTH_LONG).show();


            //Now close all the objects
            emailFolderObj.close(false);
            storeObj.close();*/

            new MyAsynk().execute();
        /*} catch (NoSuchProviderException exp) {
            Toast.makeText(this,exp.toString() , Toast.LENGTH_SHORT).show();
            exp.printStackTrace();
        } catch (MessagingException exp) {
            Toast.makeText(this,exp.toString() , Toast.LENGTH_SHORT).show();
            exp.printStackTrace();
        } catch (Exception exp) {
            Toast.makeText(this,exp.toString() , Toast.LENGTH_SHORT).show();
            exp.printStackTrace();
        }*/

    }

    public class MyAsynk extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {

            Properties props = new Properties();
            props.setProperty("mail.store.protocol", "imaps");
            try {
                Session session = Session.getInstance(props, null);
                Store store = session.getStore();
                store.connect("imap.gmail.com", "perry.john62@gmail.com", "100mwaterresist");
                Folder inbox = store.getFolder("INBOX");
                inbox.open(Folder.READ_ONLY);
                javax.mail.Message msg = inbox.getMessage(inbox.getMessageCount());
                javax.mail.Address[] in = msg.getFrom();
                for (javax.mail.Address address : in) {
                  //  System.out.println("FROM:" + address.toString());
                }
                Multipart mp = (Multipart) msg.getContent();
                BodyPart bp = mp.getBodyPart(0);
                //System.out.println("SENT DATE:" + msg.getSentDate());
                //System.out.println("SUBJECT:" + msg.getSubject());
                //System.out.println("CONTENT:" + bp.getContent());
                Toast.makeText(EnviarEmail.this, bp.getContent().toString(), Toast.LENGTH_LONG).show();
            } catch (Exception mex) {
                mex.printStackTrace();
            }
            return null;
        }
    }
}