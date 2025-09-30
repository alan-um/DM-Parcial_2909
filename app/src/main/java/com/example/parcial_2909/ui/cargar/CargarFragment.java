package com.example.parcial_2909.ui.cargar;

import static com.example.parcial_2909.MainActivity.*;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.parcial_2909.databinding.FragmentCargarBinding;

public class CargarFragment extends Fragment {

    private FragmentCargarBinding binding;
    private CargarViewModel vm;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        vm = new ViewModelProvider(this).get(CargarViewModel.class);
        binding = FragmentCargarBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //Observe
        vm.getProducto().observe(getViewLifecycleOwner(), producto -> {
            binding.tvErrorCargar.setText("");
            binding.etCodigo.setText("");
            binding.etDescripcion.setText("");
            binding.etPrecio.setText("");
            Toast.makeText(getContext(), "El producto se ha agregado correctamente!!", Toast.LENGTH_SHORT).show();
        });

        vm.getError().observe(getViewLifecycleOwner(), error -> {
            binding.tvErrorCargar.setText(error);
        });

        //Listener
        binding.btAgregar.setOnClickListener(v -> {
            String c = binding.etCodigo.getText().toString().trim();
            String d = binding.etDescripcion.getText().toString().trim();
            String p = binding.etPrecio.getText().toString().trim();
            vm.cargarProducto(c,d,p);
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}