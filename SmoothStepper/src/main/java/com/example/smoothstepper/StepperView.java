package com.example.smoothstepper;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
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
    private String toastMessage;

    private TabLayout tabLayout;
    private int selectedColor;
    private int unselectedColor;

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

    public StepperView setFinishButtonClickListener(FinishButtonClickListener listener) {
        this.finishButtonClickListener = listener;
        return this;
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

        setTabsColor(Color.BLUE, Color.GRAY);


        prevButton.setOnClickListener(v -> prevStep());
        nextButton.setOnClickListener(v -> {
            if (!isDataValid(viewPager.getCurrentItem())) {
                String message = (toastMessage != null && !toastMessage.trim().isEmpty())
                        ? toastMessage
                        : "Please complete the required fields.";
                Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
                return;
            }
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



    public StepperView setSteps(FragmentActivity fragmentActivity, List<Fragment> fragments) {
        stepperAdapter = new StepperAdapter(fragmentActivity, fragments);
        viewPager.setAdapter(stepperAdapter);
        fragmentsAmount = fragments.size();

        // Create and add tabs dynamically
        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> tab.setText("Step " + (position + 1))).attach();
        updateButtons();
        return this;
    }

    public StepperView areTabsClickable(boolean areTabsClickable) {
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            Objects.requireNonNull(tabLayout.getTabAt(i)).view.setClickable(areTabsClickable);
        }
        return this;
    }

    public StepperView addTabsIcons(ArrayList<Integer> drawableIds) {
        if (drawableIds.size() != tabLayout.getTabCount()) {
            return this;
        }
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            Objects.requireNonNull(tabLayout.getTabAt(i)).setIcon(drawableIds.get(i));
        }
        return this;
    }


    public StepperView setButtonsBackgroundColor(int color) {
        prevButton.setBackgroundColor(color);
        nextButton.setBackgroundColor(color);
        return this;
    }

    public StepperView setButtonsTextColor(int color) {
        prevButton.setTextColor(color);
        nextButton.setTextColor(color);
        return this;
    }

    public StepperView setButtonsTextSize(float size) {
        prevButton.setTextSize(size);
        nextButton.setTextSize(size);
        return this;
    }

    public StepperView setTabsColor(int selectedColor, int unselectedColor) {
        this.selectedColor = selectedColor;
        this.unselectedColor = unselectedColor;
        tabLayout.setTabTextColors(unselectedColor, selectedColor);
        tabLayout.setSelectedTabIndicatorColor(selectedColor);
        return this;
    }

    private boolean isDataValid(int stepIndex) {
        Fragment fragment = stepperAdapter.createFragment(stepIndex);
        if (fragment instanceof BaseStepperFragment) {
            return ((BaseStepperFragment) fragment).isDataValid();
        }
        return false;
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

        Fragment currentFragment = stepperAdapter.getFragments().get(currentItem);
        if (currentFragment instanceof BaseStepperFragment) {
            ((BaseStepperFragment) currentFragment).setToastMessage();
            this.toastMessage= ((BaseStepperFragment) currentFragment).getToastMessage();

        }

        if (Objects.requireNonNull(tabLayout.getTabAt(currentItem)).getIcon() != null) {

            Objects.requireNonNull(Objects.requireNonNull(tabLayout.getTabAt(currentItem)).getIcon()).setTint(selectedColor);
        }


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

        prevButton.setEnabled(currentItem > 0);
    }
}
