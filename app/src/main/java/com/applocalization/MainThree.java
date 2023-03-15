package com.applocalization;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;

import java.util.Locale;

/**
 * Created by Prem Kumar on 15-03-2023.
 */
class MainThree extends Fragment {

    View view;
    Spinner spinner;
    ArrayAdapter<CharSequence> adapter;
    String[] languageList = {"Select Language", "English", "Arabic", "Russian"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_main, container, false);

        spinner = view.findViewById(R.id.spinner);


        adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, languageList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String data = parent.getItemAtPosition(position).toString();
                switch (data) {
                    case "English":
                        setLocale(requireContext(), "en");
                        moveActivity();
                        break;
                    case "Arabic":
                        setLocale(requireContext(), "ar");
                        moveActivity();
                        break;
                    case "Russian":
                        setLocale(requireContext(), "ru");
                        moveActivity();
                        break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        return view;
    }

    private void moveActivity()
    {
        Intent intent = new Intent(requireActivity(),MainTwo.class);
        startActivity(intent);
    }


    public static void setLocale(Context activity, String languageCode) {
        Locale locale = new Locale(languageCode);
        Locale.setDefault(locale);
        Resources resources = activity.getResources();
        Configuration config = resources.getConfiguration();
        config.setLocale(locale);
        resources.updateConfiguration(config, resources.getDisplayMetrics());
    }

}
