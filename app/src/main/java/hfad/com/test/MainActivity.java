package hfad.com.test;

import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onResume() {
        super.onResume();
        Fragment frag = getFragmentManager().findFragmentByTag("photo");
        if (frag != null) {
            loadFragment(frag);
        } else {
            loadFragment();
        }
    }

    private void loadFragment(Fragment frag) {
        getFragmentManager().beginTransaction().replace(R.id.frag_container,frag, PhotoFragment.TAG).addToBackStack(null).commit();
    }

    private void loadFragment() {
        Fragment frag = new PhotoFragment();
        getFragmentManager().beginTransaction().add(R.id.frag_container,frag, PhotoFragment.TAG).addToBackStack(null).commit();
    }
}
