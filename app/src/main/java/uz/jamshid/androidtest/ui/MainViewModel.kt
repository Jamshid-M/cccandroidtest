package uz.jamshid.androidtest.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import uz.jamshid.androidtest.data.MainRepository
import uz.jamshid.androidtest.data.model.Estimate
import uz.jamshid.androidtest.data.model.Person

class MainViewModel(private val repo: MainRepository) : ViewModel() {

    val personCreatedBy = MutableLiveData<Person>(Person.newEmpty())
    val personRequestedBy = MutableLiveData<Person>(Person.newEmpty())
    val personContact = MutableLiveData<Person>(Person.newEmpty())
    val estimate = MutableLiveData<Estimate>(Estimate.newEmpty())
    val failureLiveData = MutableLiveData<Throwable>()

    private val disposable = CompositeDisposable()

    fun getPerson() {
        disposable.add(
            repo.getEstimate()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    estimate.value = it
                    getPersonCreatedBy(it.created_by)
                    getPersonRequestedBy(it.requested_by)
                    getPersonContact(it.contact)
                }, {
                    failureLiveData.value = it
                })
        )
    }

    private fun getPersonCreatedBy(created_by: String) {
        disposable.add(
            repo.getPersonCreatedBy(created_by)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    personCreatedBy.value = it
                }, {
                    failureLiveData.value = it
                })
        )
    }

    private fun getPersonRequestedBy(requested_by: String) {
        disposable.add(
            repo.getPersonRequestedBy(requested_by)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    personRequestedBy.value = it
                }, {
                    failureLiveData.value = it
                })
        )
    }

    private fun getPersonContact(contact: String) {
        disposable.add(
            repo.getPersonContact(contact)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    personContact.value = it
                }, {
                    failureLiveData.value = it
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }
}