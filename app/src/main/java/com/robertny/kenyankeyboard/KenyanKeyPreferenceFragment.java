package com.robertny.kenyankeyboard;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;

/**
 * Created by robertnyangate on 23/08/16.
 */
public class KenyanKeyPreferenceFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener {
    @Override
    public void onCreate(final Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getActivity());
//        String syncConnPref = sharedPref.getString(SettingsActivity.KEY_PREF_SYNC_CONN, "");
    }



    private void updatePreference(String key){
        if (key.equals("pref_key_message")){
            Preference preference = findPreference(key);
            if (preference instanceof EditTextPreference){
                EditTextPreference editTextPreference =  (EditTextPreference)preference;
                if (editTextPreference.getText().trim().length() > 0){
                    editTextPreference.setSummary("  " + editTextPreference.getText
                            ());
                }else{
                    editTextPreference.setSummary("Enter Your message here");
                }
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        getPreferenceScreen().getSharedPreferences()
                .registerOnSharedPreferenceChangeListener(this);
        updatePreference("pref_key_message");
    }

    @Override
    public void onPause() {
        super.onPause();
        getPreferenceScreen().getSharedPreferences()
                .unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        updatePreference(key);
    }
}
