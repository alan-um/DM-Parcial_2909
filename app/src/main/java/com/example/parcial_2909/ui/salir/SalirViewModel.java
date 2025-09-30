package com.example.parcial_2909.ui.salir;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SalirViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public SalirViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("¿Está seguro que desea salir?");
    }

    public LiveData<String> getText() {
        return mText;
    }
}