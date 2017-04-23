package in.ac.iiita.go.models;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import in.ac.iiita.go.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LectureFragment extends Fragment {
    
    
    public LectureFragment() {
        
    }
    
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lecture, container, false);
    }
    
}
