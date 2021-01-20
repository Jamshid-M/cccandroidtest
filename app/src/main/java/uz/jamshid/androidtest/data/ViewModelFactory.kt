package uz.jamshid.androidtest.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import uz.jamshid.androidtest.data.local.EstimateDao
import uz.jamshid.androidtest.data.local.PersonDao
import uz.jamshid.androidtest.ui.MainViewModel

class ViewModelFactory(private val personDao: PersonDao, private val estimateDao: EstimateDao) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(MainRepository(personDao, estimateDao)) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}