package com.rsschool.android2021;

import android.text.InputFilter;
import android.text.Spanned;

public class CustomRangeInputFilter implements InputFilter {
    private int minValue;
    private int maxValue;

    public CustomRangeInputFilter(int minVal, int maxVal) {
        this.minValue = minVal;
        this.maxValue = maxVal;
    }

    @Override
    public CharSequence filter(CharSequence charSequence, int i, int i1, Spanned spanned, int i2, int i3) {
        try{
            int val=Integer.parseInt(spanned.toString()+charSequence.toString());
            if(Integer.MIN_VALUE<=val&&val<=Integer.MAX_VALUE)
                return null;
        }catch (NumberFormatException e){
                System.out.println(e);
        }
        return "";
    }
}
