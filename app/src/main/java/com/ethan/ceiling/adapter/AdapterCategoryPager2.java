package com.ethan.ceiling.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.ethan.ceiling.ui.fragment.FragmentChild;

import java.util.List;

public class AdapterCategoryPager2 extends FragmentStateAdapter {

    private List<String> list;
    public AdapterCategoryPager2(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle, List<String> list) {
        super(fragmentManager, lifecycle);
        this.list =list;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return FragmentChild.newInstance(list.get(position),"test");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
