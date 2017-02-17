package evapeng.calculatorapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.databinding.DataBindingUtil;

import evapeng.calculatorapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private CalcModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        CalcModel viewModel = new CalcModel(this);
        binding.setViewModel(viewModel);
        //setContentView(R.layout.activity_main);
    }


}
