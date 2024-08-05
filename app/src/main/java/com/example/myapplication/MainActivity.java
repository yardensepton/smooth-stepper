package com.example.myapplication;

import android.os.Bundle;
import androidx.activity.ComponentActivity;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.example.smoothstepper.StepperView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StepperView stepperView = findViewById(R.id.stepperView);

        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new StepFragment());
        fragments.add(new MoreStepFragment());
        // Uncomment if needed
        // fragments.add(new Step3Fragment());

        // Pass the FragmentActivity to setSteps
        stepperView.setSteps(this, fragments);
    }
}
