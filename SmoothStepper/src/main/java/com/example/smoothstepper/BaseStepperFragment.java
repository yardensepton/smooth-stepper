package com.example.smoothstepper;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.tabs.TabLayout;

public abstract class BaseStepperFragment extends Fragment {

    private TextView titleTextView;
    private FrameLayout customContentContainer;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_base_stepper, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Initialize UI components
        titleTextView = view.findViewById(R.id.titleTextView);
        customContentContainer = view.findViewById(R.id.customContentContainer);


        initializeUI(view);
        loadData();
    }

    protected abstract void initializeUI(View view);

    protected abstract void loadData();

    protected void setTitle(String title) {
        if (titleTextView != null) {
            titleTextView.setText(title);
        }
    }


    protected void addCustomContent(View customView) {
        if (customContentContainer != null) {
            customContentContainer.removeAllViews();
            customContentContainer.addView(customView);
        }
    }
}
