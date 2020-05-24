package francisco.p.appherramientabraille.Fragmentos;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import francisco.p.appherramientabraille.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentoAlfabeto extends Fragment {
    private Button btna;

    public FragmentoAlfabeto() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_fragmento_alfabeto, container, false);
        btna=(Button)view.findViewById(R.id.btna);
        btna.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(getActivity(), "Hola", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

}
