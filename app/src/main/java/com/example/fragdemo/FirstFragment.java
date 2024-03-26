package com.example.fragdemo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.example.fragdemo.databinding.FragmentFirstBinding;

import java.util.List;

public class FirstFragment extends Fragment {

    ColorSpecViewModel colorSpecViewModel;
    List<ColorSpec> FragColors;
    ColorSpecAdapter colorSpecAdapter;
    private FragmentFirstBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        colorSpecViewModel = new ViewModelProvider(requireActivity()).get(ColorSpecViewModel.class);
        colorSpecViewModel.getColorList().observe(getViewLifecycleOwner(), new Observer<List<ColorSpec>>() {
            @Override
            public void onChanged(List<ColorSpec> colorSpecs) {
                FragColors = colorSpecs;
                colorSpecAdapter = new ColorSpecAdapter(FragColors);
                binding.spinnerColors.setAdapter(colorSpecAdapter);
                // spinner should be in dialog mode
            }
        });
        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt("COLORVAL", FragColors.get(binding.spinnerColors.getSelectedItemPosition()).getColorVal());
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment, bundle);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}