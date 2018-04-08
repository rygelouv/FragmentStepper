package com.rygelouv.fragmentstepperproject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.rygelouv.fragmentstepper.FragmentStepper;
import com.rygelouv.fragmentstepper.StepsManager;

import org.jetbrains.annotations.NotNull;

public class MainJavaActivity extends AppCompatActivity implements StepsManager, OnFragmentInteractionListener {

    FragmentStepper stepper;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        stepper = findViewById(R.id.view_pager);
        progressBar = findViewById(R.id.progress_bar);

        stepper.setParentActivity(this);
        stepper.setStepsChangeListener(new FragmentStepper.StepsChangeListener() {
            @Override
            public void onStepsChanged() {
                Toast.makeText(MainJavaActivity.this, "Page changed java", Toast.LENGTH_SHORT).show();
                progressBar.setProgress(stepper.getCurrentItem());
            }
        });
    }

    @Override
    public int getCount() {
        return 3;
    }

    @NotNull
    @Override
    public Fragment getStep(int position) {
        switch (position) {
            case 0:
                return new FirstFragment();
            case 1:
                return new SecondFragment();
            case 2:
                return ThirdFragment.newInstance("Boom Java", "Baam Java");
            default:
                 return new FirstFragment();
        }
    }

    @Override
    public void onFragmentInteraction() {
        stepper.setCurrentItem(stepper.getCurrentItem()+1);
    }

    @Override
    public void onBackPressed() {
        if (stepper.getCurrentItem() == 0)
            super.onBackPressed();
        else
            stepper.setCurrentItem(stepper.getCurrentItem() - 1);
    }
}
