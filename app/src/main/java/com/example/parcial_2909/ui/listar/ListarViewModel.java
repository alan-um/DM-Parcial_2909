package com.example.parcial_2909.ui.listar;

import static com.example.parcial_2909.MainActivity.*;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.parcial_2909.modelo.Producto;

import java.util.Comparator;
import java.util.List;

public class ListarViewModel extends AndroidViewModel {

    private MutableLiveData<List<Producto>> listaMutable;

    public ListarViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<Producto>> getListaMutable() {
        if(listaMutable==null) {listaMutable=new MutableLiveData<>();}
        return listaMutable;
    }

    public void listarInventario(){
        inventario.sort(Comparator.comparing(Producto::getDescripcionLowerCase));
        listaMutable.setValue(inventario);
    }
}