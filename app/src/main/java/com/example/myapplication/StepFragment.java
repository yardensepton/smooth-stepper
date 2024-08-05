package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.example.smoothstepper.BaseStepperFragment;

public class StepFragment extends BaseStepperFragment {
EditText editText ;

    @Override
    protected void initializeUI(View view) {
        setTitle("Step Two Title");

        View customView = LayoutInflater.from(getContext()).inflate(R.layout.custom_content_layout, null);
        editText = customView.findViewById(R.id.userInputEditText);

        addCustomContent(customView);
    }

    @Override
    protected void loadData() {
        // Load additional data if needed
    }

    public String getCustomUserInput() {
        if (editText != null) {
            return editText.getText().toString();
        }
        return "";
    }
}
