package com.dam.safebar.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.dam.safebar.R;

public class ProtocoloCovidFragment extends Fragment {

    public ProtocoloCovidFragment() {
        // Required empty public constructor
    }

    public ProtocoloCovidFragment newInstance() {
        ProtocoloCovidFragment fragment = new ProtocoloCovidFragment();
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

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_protocolo_covid, container, false);
        final String pdfString = "https://www.mincotur.gob.es/es-es/COVID-19/GuiasSectorTurismo/Restaurantes.pdf";
        String path = "https://docs.google.com/gview?embedded=true&url=" + pdfString;
        final WebView webView = (WebView) view.findViewById(R.id.webViewCovid);
        webView.loadUrl(path);

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        webView.setWebViewClient(new WebViewClient());

        return view;
    }
}