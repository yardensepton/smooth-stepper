package com.example.smoothstepper;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.List;

public class StepperAdapter extends FragmentStateAdapter {

    private final List<Fragment> fragments;

    public StepperAdapter(@NonNull FragmentActivity fragmentActivity, List<Fragment> fragments) {
        super(fragmentActivity);
        this.fragments = fragments;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragments.get(position);
    }

    @NonNull
    public List<Fragment> getFragments() {
        return fragments;
    }

    @Override
    public int getItemCount() {
        return fragments.size();
    }

}
