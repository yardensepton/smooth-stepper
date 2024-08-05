package com.example.smoothstepper;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class StepperAdapter extends FragmentPagerAdapter {

    private final List<Fragment> fragments;

    public StepperAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.fragments = new ArrayList<>(fragments);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    public void updateSteps(List<Fragment> newFragments) {
        this.fragments.clear();
        this.fragments.addAll(newFragments);
        notifyDataSetChanged();
    }
}
