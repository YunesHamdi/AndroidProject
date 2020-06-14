package com.example.andproject.viewmodels;

import androidx.lifecycle.ViewModel;

import com.example.andproject.repositories.AdviceRepository;

public class AdviceFragmentViewModel extends ViewModel {

    AdviceRepository repository;

    public AdviceRepository getRepository() {
        return repository;
    }

    public AdviceFragmentViewModel() {
        repository = AdviceRepository.getInstance();
    }

    public void fetchAdvice() {
        repository.fetchAdvice();
    }
}