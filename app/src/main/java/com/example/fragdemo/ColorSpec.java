package com.example.fragdemo;

public class ColorSpec {
    private String colorDesc; // eg: BLACK
    private int colorVal; //eg: Color.LTGRAY

    public String getColorDesc() {
        return colorDesc;
    }

    public void setColorDesc(String colorDesc) {
        this.colorDesc = colorDesc;
    }

    public int getColorVal() {
        return colorVal;
    }

    public void setColorVal(int colorVal) {
        this.colorVal = colorVal;
    }

    public ColorSpec(String colorDesc, int colorVal) {
        this.colorDesc = colorDesc;
        this.colorVal = colorVal;
    }
}
