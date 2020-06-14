package com.example.andproject.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.andproject.R;
import com.example.andproject.models.Advice;
import com.example.andproject.viewmodels.AdviceFragmentViewModel;

public class AdviceFragment extends Fragment {

    AdviceFragmentViewModel mAdviceFragmentViewModel;
    TextView adviceText;
    View v;
    Button getAdviceButton;
    TextView label;

    public AdviceFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdviceFragmentViewModel = new ViewModelProvider(this).get(AdviceFragmentViewModel.class);
    }

    public void observeRepository() {
        mAdviceFragmentViewModel.getRepository().adviceLiveData().observe(getViewLifecycleOwner(), new Observer<Advice>() {
            @Override
            public void onChanged(Advice advice) {
                String content = advice.getSlip().getAdvice();
                adviceText.append(content);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.advice_fragment, container, false);
        adviceText = v.findViewById(R.id.advice);
        getAdviceButton = v.findViewById(R.id.getadvicebutton);
        label = v.findViewById(R.id.adviceLabel);
        getAdviceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adviceText.setText("");
                mAdviceFragmentViewModel.fetchAdvice();
            }
        });
        return v;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        observeRepository();
        mAdviceFragmentViewModel.fetchAdvice();
    }
}