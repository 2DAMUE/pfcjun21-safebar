package com.dam.safebar.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.dam.safebar.R;


public class AboutUsFragment extends Fragment {

    public AboutUsFragment() {
        // Required empty public constructor
    }

    public AboutUsFragment newInstance() {
        AboutUsFragment fragment = new AboutUsFragment();
        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_about_us, container, false);

        ImageView imageView1 = (ImageView) view.findViewById(R.id.imgAboutUs1);
        ImageView imageView2 = (ImageView) view.findViewById(R.id.imgAboutUs2);
        ImageView imageView3 = (ImageView) view.findViewById(R.id.imgAboutUs3);

        Glide.with(imageView1)
                .load(R.drawable.img_alex)
                .placeholder(null)
                .into(imageView1);
        Glide.with(imageView2)
                .load(R.drawable.img_miguel)
                .placeholder(null)
                .into(imageView2);
        Glide.with(imageView3)
                .load(R.drawable.img_pablo)
                .placeholder(null)
                .into(imageView3);


        return view;
    }
}