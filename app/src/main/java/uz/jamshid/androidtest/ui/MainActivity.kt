package uz.jamshid.androidtest.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import uz.jamshid.androidtest.MyApplication
import uz.jamshid.androidtest.R
import uz.jamshid.androidtest.data.ViewModelFactory
import uz.jamshid.androidtest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    private val viewModel: MainViewModel by viewModels {
        ViewModelFactory((application as MyApplication).personDao, (application as MyApplication).estimateDao)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        viewModel.failureLiveData.observe(this, Observer {
            Log.d(TAG, "Failure: ${it.message}")
        })

        viewModel.getPerson()
    }

}