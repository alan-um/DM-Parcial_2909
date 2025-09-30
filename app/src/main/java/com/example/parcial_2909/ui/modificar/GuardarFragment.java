package com.example.parcial_2909.ui.modificar;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.parcial_2909.R;
import com.example.parcial_2909.databinding.FragmentGuardarBinding;
import com.example.parcial_2909.databinding.FragmentModificarBinding;

public class GuardarFragment extends Fragment {
    private FragmentGuardarBinding binding;
    private GuardarViewModel vm;

    public static GuardarFragment newInstance() {
        return new GuardarFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        vm = new ViewModelProvider(this).get(GuardarViewModel.class);
        binding = FragmentGuardarBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //Observe
        vm.getProducto().observe(getViewLifecycleOwner(), producto -> {
            binding.tvErrorModificar.setText("");
            binding.etCodigoModificar.setText("");
            binding.etDescripcionModificar.setText("");
            binding.etPrecioModificar.setText("");
            Toast.makeText(getContext(), "El producto se ha modificado correctamente!!", Toast.LENGTH_SHORT).show();
        });

        vm.getError().observe(getViewLifecycleOwner(), error -> {
            binding.tvErrorModificar.setText(error);
        });

        //Listener
        binding.btGuardar.setOnClickListener(v -> {
            String c = binding.etCodigoModificar.getText().toString().trim();
            String d = binding.etDescripcionModificar.getText().toString().trim();
            String p = binding.etPrecioModificar.getText().toString().trim();
            vm.guardarProducto(c,d,p);
        });

        //Other
        Bundle bundle = getArguments();
        String c = bundle.getString("codigo");
        String d = bundle.getString("descripcion");
        String p = bundle.getString("precio");
        binding.etCodigoModificar.setText(c);
        binding.etDescripcionModificar.setText(d);
        binding.etPrecioModificar.setText(p);
        vm.cargarCodigoAnterior(c);



        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}