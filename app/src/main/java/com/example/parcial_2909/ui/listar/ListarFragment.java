package com.example.parcial_2909.ui.listar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.parcial_2909.databinding.FragmentListarBinding;

public class ListarFragment extends Fragment {

    private FragmentListarBinding binding;
    private ListarViewModel vm;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        vm = new ViewModelProvider(this).get(ListarViewModel.class);
        binding = FragmentListarBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        vm.getListaMutable().observe( getViewLifecycleOwner(), productos -> {
            ListarAdapter listarAdapter = new ListarAdapter(productos, getContext(), getLayoutInflater());
            GridLayoutManager glm = new GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL, false);
            binding.listaProducto.setLayoutManager(glm);
            binding.listaProducto.setAdapter(listarAdapter);
        });

        vm.listarInventario();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}