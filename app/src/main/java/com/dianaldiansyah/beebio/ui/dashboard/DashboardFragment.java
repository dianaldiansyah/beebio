package com.dianaldiansyah.beebio.ui.dashboard;

// DashboardFragment.java

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.dianaldiansyah.beebio.databinding.FragmentDashboardBinding;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;
    private SharedPreferences sharedPreferences;
    private final String sharedPreferencesName = "MySharedPreferences";
    private final String nameKey = "nameKey";
    private final String hobbyKey = "hobbyKey";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Initialize SharedPreferences
        sharedPreferences = requireActivity().getSharedPreferences(sharedPreferencesName, Context.MODE_PRIVATE);

        // Get references to views
        EditText nameEditText = binding.nameEditText;
        EditText hobbyEditText = binding.hobbyEditText;
        Button saveButton = binding.saveButton;
        TextView savedDataTextView = binding.savedDataTextView;

        // Load data from SharedPreferences and display it
        loadSavedData(savedDataTextView);

        saveButton.setOnClickListener(v -> {
            // Save data to SharedPreferences
            String name = nameEditText.getText().toString();
            String hobby = hobbyEditText.getText().toString();

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(nameKey, name);
            editor.putString(hobbyKey, hobby);
            editor.apply();

            // Display saved data below the "Save" button
            String savedData = "Name: " + name + "\nHobby: " + hobby;
            savedDataTextView.setText(savedData);
        });

        return root;
    }

    private void loadSavedData(TextView savedDataTextView) {
        String name = sharedPreferences.getString(nameKey, "");
        String hobby = sharedPreferences.getString(hobbyKey, "");

        // Display saved data below the "Save" button
        String savedData = "Name: " + name + "\nHobby: " + hobby;
        savedDataTextView.setText(savedData);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
