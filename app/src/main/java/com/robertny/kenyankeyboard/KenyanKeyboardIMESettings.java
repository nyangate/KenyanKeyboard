package com.robertny.kenyankeyboard;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by robertnyangate on 23/08/16.
 */
public class KenyanKeyboardIMESettings extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settingsview);

        getFragmentManager().beginTransaction().replace(R.id.settings, new
                KenyanKeyPreferenceFragment()).commit();
    }
}
