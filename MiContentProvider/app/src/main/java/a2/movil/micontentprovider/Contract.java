package a2.movil.micontentprovider;

import android.net.Uri;
import android.provider.BaseColumns;

public final class Contract {
    private Contract(){

    }
    public static final String AUTHORITY = "a2.movil.micontentprovider.provider";
    public static final String CONTENT_PATH = "asignaturas";
    //content://a2.movil.micontentprovider.provider/asignaturas
    public static final Uri CONTENT_URI= Uri.parse("content://"+AUTHORITY+"/"+CONTENT_PATH);

    static final int ALL_ITEMS = -2;
    static final String ASIGNATURA_ID="id";
public static final String COUNT="count";
public static final Uri ROW_COUNT_URI= Uri.parse("content://"+AUTHORITY+"/"+CONTENT_PATH+"/"+COUNT);
    static final String SINGLE_RECORD_MIME_TYPE = "vnd.android.cursor.item/vnd.a2.movil.micontentprovider.provider.asignaturas";
    static final String MULTIPLE_RECORDS_MIME_TYPE="vnd.android.cursor.dir/vnd.a2.movil.micontentprovider.provider.asignaturas";

    public static final String DATABASE_NOMBRE="asignaturas";
    public static abstract class  Asignaturas implements BaseColumns{
        public static final String ASIGNATURAS_TABLA= "ASIGNATURAS_NOMBRES";
        public static final String ID="_id";
        public static final String NOMBRE="nombre";
    }

}
