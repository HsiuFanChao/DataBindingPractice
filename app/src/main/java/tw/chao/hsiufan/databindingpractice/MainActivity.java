package tw.chao.hsiufan.databindingpractice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import tw.chao.hsiufan.databindingpractice.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        // implementation 'androidx.lifecycle:lifecycle-extensions:2.0.0'
        SimpleViewModelSolution viewModel = ViewModelProviders.of(this).get(SimpleViewModelSolution.class);
        binding.setViewmodel(viewModel);

        binding.setLifecycleOwner(this);
    }
}
