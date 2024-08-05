package com.example.myapplication;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.smoothstepper.StepperView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        StepperView stepperView = findViewById(R.id.stepperView);
        FragmentManager fragmentManager = getSupportFragmentManager();

        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new StepFragment());
        fragments.add(new MoreStepFragment());


//        fragments.add(new Step3Fragment());

        stepperView.setSteps( fragmentManager,fragments);

    }
}