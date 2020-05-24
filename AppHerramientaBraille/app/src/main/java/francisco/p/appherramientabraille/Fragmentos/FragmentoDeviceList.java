package francisco.p.appherramientabraille.Fragmentos;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import francisco.p.appherramientabraille.DeviceList;
import francisco.p.appherramientabraille.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentoDeviceList extends Fragment {

Button btnConectar=null;
    public FragmentoDeviceList() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_fragmento_device_list,container,false);
        btnConectar=(Button)view.findViewById(R.id.btn_conectar);
        btnConectar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getActivity(), DeviceList.class);
                startActivity(i);
            }

        });
        return view;
    }

}
