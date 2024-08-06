package com.example.myapplication;


import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;

import com.example.smoothstepper.BaseStepperFragment;

import java.util.ArrayList;

public class MoreStepFragment extends BaseStepperFragment {
    ArrayList<CheckBox> checkBoxes;

    @Override
    protected boolean isDataValid() {
        return !getCustomUserInput().isEmpty();
    }

    @Override
    protected void initializeUI(View view) {
        setTitle("Step Two Title");
        setTitleColor(Color.RED);

        View customView = LayoutInflater.from(getContext()).inflate(R.layout.custom_checkboxes_layout, null);
        checkBoxes = new ArrayList<>();
        checkBoxes.add(customView.findViewById(R.id.checkBox1));
        checkBoxes.add(customView.findViewById(R.id.checkBox2));
        checkBoxes.add(customView.findViewById(R.id.checkBox3));
        checkBoxes.add(customView.findViewById(R.id.checkBox4));
        checkBoxes.add(customView.findViewById(R.id.checkBox5));
        addCustomContent(customView);
    }

    @Override
    protected void setToastMessage() {
        this.toastMessage = "fill the checkboxes";
    }

    @Override
    protected void loadData() {
        // Load additional data if needed
    }

    public String getCustomUserInput() {
        StringBuilder selectedItems = new StringBuilder();

        for (CheckBox checkBox : checkBoxes) {
            if (checkBox.isChecked()) {
                if (selectedItems.length() > 0) {
                    selectedItems.append(", ");
                }
                selectedItems.append(checkBox.getText().toString());
            }
        }

        return selectedItems.toString();
    }

}
