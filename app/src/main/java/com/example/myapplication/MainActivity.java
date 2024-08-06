package com.example.myapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.smoothstepper.StepperView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StepperView stepperView = findViewById(R.id.stepperView);
        ArrayList<Integer> icons = new ArrayList<>();
        icons.add(R.drawable.user_icon);
        icons.add(R.drawable.fruit_icon);

        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new StepFragment());
        fragments.add(new MoreStepFragment());
        stepperView.setButtonsBackgroundColor(Color.BLUE).
                setButtonsTextColor(Color.WHITE)
                .setTabsColor(Color.RED, Color.GRAY)
                .setSteps(this, fragments)
                .areTabsClickable(false).addTabsIcons(icons)
                .setFinishButtonClickListener(finishStepper());
    }

    private StepperView.FinishButtonClickListener finishStepper() {
        return () -> Log.e("MainActivity", "Finish button clicked");
    }
}
