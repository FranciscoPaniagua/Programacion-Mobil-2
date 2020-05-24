package francisco.p.appherramientabraille.Fragmentos;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import francisco.p.appherramientabraille.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentoMayusculas extends Fragment {


    public FragmentoMayusculas() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragmento_mayusculas, container, false);
    }

}
