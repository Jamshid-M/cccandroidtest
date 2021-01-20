package uz.jamshid.androidtest.data

import uz.jamshid.androidtest.data.local.EstimateDao
import uz.jamshid.androidtest.data.local.PersonDao

class MainRepository(private val personDao: PersonDao, private val estimateDao: EstimateDao) {

    fun getEstimate() = estimateDao.getEstimates()
    fun getPersonCreatedBy(created_by: String) = personDao.getPersonCreatedBy(created_by)
    fun getPersonRequestedBy(requested_by: String) = personDao.getPersonRequestedBy(requested_by)
    fun getPersonContact(contact: String) = personDao.getPersonContact(contact)
}