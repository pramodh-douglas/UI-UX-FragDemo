package com.example.fragdemo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class ColorSpecViewModel extends ViewModel {
    MutableLiveData<List<ColorSpec>> ColorList = new MutableLiveData<>();

    public LiveData<List<ColorSpec>> getColorList() {
        if(ColorList == null) {
            ColorList = new MutableLiveData<>();
        }

        return ColorList;
    }

    public void setColorList(List<ColorSpec> colorList) {
        ColorList.setValue(colorList); // can be done if view model is updated in main thread
        // use postValue() if using background threads
    }
}
