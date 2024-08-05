package com.example.smoothstepper;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class StepperView extends LinearLayout {

    private ViewPager2 viewPager;
    private Button prevButton;
    private Button nextButton;
    private StepperAdapter stepperAdapter;
    private int fragmentsAmount;

    private TabLayout tabLayout;

    private FinishButtonClickListener finishButtonClickListener;

    public StepperView(Context context) {
        super(context);
        init(context);
    }

    public StepperView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public StepperView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void setFinishButtonClickListener(FinishButtonClickListener listener) {
        this.finishButtonClickListener = listener;
    }

    public interface FinishButtonClickListener {
        void onFinishButtonClick();
    }

    private void init(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.stepper_layout, this, true);

        viewPager = view.findViewById(R.id.stepContent);
        prevButton = view.findViewById(R.id.prevButton);
        nextButton = view.findViewById(R.id.nextButton);
        tabLayout = view.findViewById(R.id.tabLayout);

        prevButton.setOnClickListener(v -> prevStep());
        nextButton.setOnClickListener(v -> {
            if (viewPager.getCurrentItem() == fragmentsAmount - 1) {
                if (finishButtonClickListener != null) {
                    finishButtonClickListener.onFinishButtonClick();
                }
            } else {
                nextStep();
            }
        });

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                Objects.requireNonNull(tabLayout.getTabAt(position)).select();
                updateButtons();
            }
        });
    }

    public void setSteps(FragmentActivity fragmentActivity, List<Fragment> fragments) {
        FragmentManager fragmentManager = fragmentActivity.getSupportFragmentManager();
        stepperAdapter = new StepperAdapter(fragmentActivity, fragments);
        viewPager.setAdapter(stepperAdapter);
        fragmentsAmount = fragments.size();

        // Create and add tabs dynamically
        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> tab.setText("Step " + (position + 1))).attach();

        Log.d("StepperView", "setSteps: " + fragmentsAmount);
        updateButtons();
    }

    public void updateSteps(List<Fragment> newFragments) {
        if (stepperAdapter != null) {
            stepperAdapter.updateSteps(newFragments);
            updateButtons();
        }
    }

    private void prevStep() {
        int currentItem = viewPager.getCurrentItem();
        if (currentItem > 0) {
            viewPager.setCurrentItem(currentItem - 1);
        }
        updateButtons();
    }

    private void nextStep() {
        int currentItem = viewPager.getCurrentItem();
        if (currentItem < stepperAdapter.getItemCount() - 1) {
            viewPager.setCurrentItem(currentItem + 1);
        }
        updateButtons();
    }

    private void updateButtons() {
        int currentItem = viewPager.getCurrentItem();
        Log.d("StepperView", "currentItem: " + currentItem);

        // Handle the visibility of the previous button
        if (currentItem == 0) {
            prevButton.setVisibility(GONE);
        } else {
            prevButton.setVisibility(VISIBLE);
        }

        // Set text for the next button based on the current step
        if (currentItem == fragmentsAmount - 1) {
            nextButton.setText(getContext().getString(R.string.finish));
        } else {
            nextButton.setText(getContext().getString(R.string.next));
        }

        // Set the enabled state for both buttons
        prevButton.setEnabled(currentItem > 0);
    }
}
