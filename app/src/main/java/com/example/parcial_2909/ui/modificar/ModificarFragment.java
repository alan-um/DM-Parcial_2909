package com.example.parcial_2909.ui.modificar;

import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.parcial_2909.MainActivity;
import com.example.parcial_2909.R;
import com.example.parcial_2909.databinding.FragmentCargarBinding;
import com.example.parcial_2909.databinding.FragmentModificarBinding;
import com.example.parcial_2909.ui.cargar.CargarViewModel;

public class ModificarFragment extends Fragment {
    private FragmentModificarBinding binding;
    private ModificarViewModel vm;

    public static ModificarFragment newInstance() {
        return new ModificarFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        vm = new ViewModelProvider(this).get(ModificarViewModel.class);
        binding = FragmentModificarBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //Observe
        vm.getProducto().observe(getViewLifecycleOwner(), producto -> {
            binding.tvErrorBuscar.setText("");
            binding.etCodigoBuscar.setText("");
            Bundle bundle = new Bundle();
            bundle.putString("codigo", String.valueOf(producto.getCogido()));
            bundle.putString("descripcion", producto.getDescripcion());
            bundle.putString("precio", String.valueOf(producto.getPrecio()));
            vm.limpiarProducto();
            Navigation.findNavController(getActivity(), R.id.nav_host_fragment_content_main).navigate(R.id.guardarFragment, bundle);
            //Toast.makeText(getContext(), "Producto encontrado!!", Toast.LENGTH_SHORT).show();
        });

        vm.getError().observe(getViewLifecycleOwner(), error -> {
            binding.tvErrorBuscar.setText(error);
        });

        //Listener
        binding.btModificar.setOnClickListener(v -> {
            String c = binding.etCodigoBuscar.getText().toString().trim();
            vm.buscarProducto(c);
        });

        //Other
        /*if(getArguments()!=null){
        Bundle bundle = getArguments();
        String error = bundle.getString("error");
        if (error != null) {
            binding.tvErrorBuscar.setText(error);
        }}*/


        return root;
        //return inflater.inflate(R.layout.fragment_modificar, container, false);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}