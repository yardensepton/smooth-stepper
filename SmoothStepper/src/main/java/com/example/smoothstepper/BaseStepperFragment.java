package com.example.smoothstepper;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public abstract class BaseStepperFragment extends Fragment {

    private TextView titleTextView;
    private FrameLayout customContentContainer;
    protected String toastMessage;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_base_stepper, container, false);
    }

    protected abstract boolean isDataValid();

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        titleTextView = view.findViewById(R.id.titleTextView);
        customContentContainer = view.findViewById(R.id.customContentContainer);


        initializeUI(view);
        loadData();
        setToastMessage();
    }

    protected abstract void initializeUI(View view);

    protected abstract void setToastMessage();

    protected abstract void loadData();

    protected void setTitle(String title) {
        if (titleTextView != null) {
            titleTextView.setText(title);
        }
    }

    protected String getToastMessage() {
        return this.toastMessage;
    }

    protected void setTitleColor(int color) {
        if (titleTextView != null) {
            titleTextView.setTextColor(color);
        }
    }

    protected void setTitleSize(float size) {
        if (titleTextView != null) {
            titleTextView.setTextSize(size);
        }
    }


    protected void addCustomContent(View customView) {
        if (customContentContainer != null) {
            customContentContainer.removeAllViews();
            customContentContainer.addView(customView);
        }
    }
}
