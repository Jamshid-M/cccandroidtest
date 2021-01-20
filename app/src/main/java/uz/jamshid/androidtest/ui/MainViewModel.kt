package uz.jamshid.androidtest.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import uz.jamshid.androidtest.data.MainRepository
import uz.jamshid.androidtest.data.model.Person
import java.lang.Exception

class MainViewModel(private val repo: MainRepository) : ViewModel() {

    val personCreatedBy = MutableLiveData<Person>()
    val personRequestedBy = MutableLiveData<Person>()
    val personContact = MutableLiveData<Person>()
    val failureLiveData = MutableLiveData<Throwable>()

    fun getPerson() {
        viewModelScope.launch {
            try {

            val estimate = async {
                repo.getEstimate()
            }.await()

            personCreatedBy.value = repo.getPersonCreatedBy(estimate.created_by)
            personRequestedBy.value = repo.getPersonRequestedBy(estimate.requested_by)
            personContact.value = repo.getPersonContact(estimate.contact)
            }catch (e: Exception){
                failureLiveData.value = e.cause
            }
        }
    }
}