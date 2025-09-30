package com.example.parcial_2909.ui.modificar;

import static com.example.parcial_2909.MainActivity.inventario;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.navigation.Navigation;

import com.example.parcial_2909.R;
import com.example.parcial_2909.modelo.Producto;

import java.util.ArrayList;
import java.util.Iterator;

public class ModificarViewModel extends AndroidViewModel {
    private MutableLiveData<Producto> producto;
    private MutableLiveData<String> error;

    public ModificarViewModel(@NonNull Application application) {
        super(application);
    }
    // TODO: Implement the ViewModel

    public LiveData<Producto> getProducto() {
        if (producto == null) {
            producto = new MutableLiveData<>();
        }
        return producto;
    }

    public LiveData<String> getError() {
        if (error == null) {
            error = new MutableLiveData<>();
        }
        return error;
    }

    public void limpiarProducto(){
        producto=null;
        error=null;
    }
    public void buscarProducto(String stcodigo) {
        int codigo = -1;
        String regexInt = "^\\d+$";

        //Valida el CODIGO
        if (stcodigo.isBlank()) {
            error.setValue("Debe ingresar el código");
        } else if (!stcodigo.trim().matches(regexInt)) {
            error.setValue("El código ingresado no es válido");
        } else {
            codigo = Integer.parseInt(stcodigo.trim());
            if (codigo <= 0) {
                error.setValue("El código debe ser un número mayor a cero");
            }
        }

        //Busca el Producto según su código en el inventario
        if(codigo>0) {
            Producto encontrado = buscarProductoPorCodigo(codigo);
            if (encontrado != null) {
                producto.setValue(encontrado);
            } else {
                error.setValue("No se ha encontrado el producto");
            }
        }
    }

    private Producto buscarProductoPorCodigo(int codigoBuscado) {
        for (Producto producto : inventario) {
            if (producto.getCogido() == codigoBuscado) {
                return producto;
            }
        }
        return null;
    }
}