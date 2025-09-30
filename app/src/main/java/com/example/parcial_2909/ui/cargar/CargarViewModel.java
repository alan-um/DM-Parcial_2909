package com.example.parcial_2909.ui.cargar;

import static com.example.parcial_2909.MainActivity.*;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.parcial_2909.modelo.Producto;

import java.util.ArrayList;

public class CargarViewModel extends AndroidViewModel {
    private MutableLiveData<Producto> producto;
    private MutableLiveData<String> error;

    public CargarViewModel(@NonNull Application application) {
        super(application);
    }

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

    public void cargarProducto(String stcodigo, String descripcion, String stprecio) {
        int codigo = -1;
        String regexInt = "^\\d+$";
        double precio = -1;
        String regexDouble = "^\\d*(\\.\\d+)?$";
        ArrayList<String> errores = new ArrayList<>();
        Producto nuevo;

        //Valida el CODIGO
        if (stcodigo.isBlank()) {
            errores.add("Debe ingresar el código");
        } else if (!stcodigo.trim().matches(regexInt)) {
            errores.add("El código ingresado no es válido");
        } else {
            codigo = Integer.parseInt(stcodigo.trim());
            if (codigo <= 0) {
                errores.add("El código debe ser un número mayor a cero");
            }
        }

        //Valida la DESCRIPCION
        if (descripcion.isBlank()) {
            errores.add("Debe ingresar la descripción");
        }

        //Valida el PRECIO
        if (stprecio.isBlank()) {
            errores.add("Debe ingresar el precio");
        } else if (!stprecio.trim().matches(regexDouble)) {
            errores.add("El precio ingresado no es válido");
        } else {
            precio = Double.parseDouble(stprecio.trim());
            if (precio < 0) {
                errores.add("El precio debe ser un número mayor o igual a cero");
            }
        }

        //Valida que el código no esté repetido en el inventario
        nuevo = new Producto(codigo, descripcion, precio);
        if (inventario.contains(nuevo)) {
            errores.add("El código ingresado ya existe en el inventario");
        }

        //Si hay errores NO valida y actualiza en LiveData de errores.
        if (errores.size() > 0) {
            StringBuilder sbError = new StringBuilder();
            for (String e :
                    errores) {
                sbError.append(e + "\n");
            }
            error.setValue(sbError.toString());
        } else {//Si no actualiza el LiveData de producto
            inventario.add(nuevo);
            producto.setValue(nuevo);
        }
    }
}