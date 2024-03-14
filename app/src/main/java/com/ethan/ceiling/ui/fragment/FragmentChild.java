package com.ethan.ceiling.ui.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ethan.ceiling.adapter.ChildAdapter;
import com.ethan.ceiling.databinding.FragmentChildBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentChild#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentChild extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private List<String>         list ;
    private FragmentChildBinding mBinding;
    public FragmentChild() {

    }


    public static FragmentChild newInstance(String param1, String param2) {
        FragmentChild fragment = new FragmentChild();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding =FragmentChildBinding.inflate(inflater,container,false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initListData();
        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        ChildAdapter adapter =new ChildAdapter(list);
        mBinding.recyclerView.setAdapter(adapter);
    }

    private void initListData(){
        list =new ArrayList<>(100);
        for (int i = 0; i < 100; i++) {
            list.add("第"+i+"个");
        }
    }
}