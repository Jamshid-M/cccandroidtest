package uz.jamshid.androidtest.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import uz.jamshid.androidtest.MyApplication
import uz.jamshid.androidtest.R
import uz.jamshid.androidtest.data.ViewModelFactory

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    private val viewModel: MainViewModel by viewModels {
        ViewModelFactory((application as MyApplication).personDao, (application as MyApplication).estimateDao)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.personRequestedBy.observe(this, Observer {
            Log.d(TAG, "Requested by: $it")
        })

        viewModel.personCreatedBy.observe(this, Observer {
            Log.d(TAG, "Created by: $it")
        })

        viewModel.personContact.observe(this, Observer {
            Log.d(TAG, "Contact: $it")
        })

        viewModel.getPerson()
    }

}