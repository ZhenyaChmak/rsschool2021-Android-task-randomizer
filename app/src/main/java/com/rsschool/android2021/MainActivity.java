package com.rsschool.android2021;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity
        implements FirstFragment.OnFragmentMinMax, SecondFragment.OnFragmentValue{

    private int valueRandomIsSecondFragment = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState == null) {
            openFirstFragment(0);
        }
    }

    private void openFirstFragment(int previousNumber) {
        final Fragment firstFragment = FirstFragment.newInstance(previousNumber);
        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, firstFragment);
        transaction.commit();
    }

    private void openSecondFragment(int min, int max) {
        final Fragment secondFragment = SecondFragment.newInstance(min, max);
        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, secondFragment);
        transaction.commit();
    }

    @Override
    public void transferFragmentMinMax(int min, int max){
        openSecondFragment(min,max);
    }

    @Override
    public void transferFragmentValue(int value){
        openFirstFragment(value);
    }

    @Override
    public void onValueRandomIsSecondFragment(int valueRandomIsSecondFragment){
        this.valueRandomIsSecondFragment = valueRandomIsSecondFragment;
    }

    @Override
    public void onBackPressed(){
        if(SecondFragment.getFlagBack()){
           transferFragmentValue(valueRandomIsSecondFragment);
        } else {
            super.onBackPressed();
        }
    }
}
