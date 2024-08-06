package com.example.myapplication;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.example.smoothstepper.BaseStepperFragment;

public class StepFragment extends BaseStepperFragment {
EditText editText ;

    @Override
    protected boolean isDataValid() {
        return !editText.getText().toString().trim().isEmpty();
    }

    @Override
    protected void initializeUI(View view) {
        setTitle("Step One Title");
        setTitleColor(Color.GRAY);

        View customView = LayoutInflater.from(getContext()).inflate(R.layout.custom_content_layout, null);
        editText = customView.findViewById(R.id.userInputEditText);

        addCustomContent(customView);
    }

    @Override
    protected void setToastMessage() {
        this.toastMessage = null;

    }

    @Override
    protected void loadData() {
        // Load additional data if needed
    }



}
