package uz.jamshid.androidtest.data

import uz.jamshid.androidtest.data.local.EstimateDao
import uz.jamshid.androidtest.data.local.PersonDao

class MainRepository(private val personDao: PersonDao, private val estimateDao: EstimateDao) {

    suspend fun getEstimate() = estimateDao.getEstimates()
    suspend fun getPersonCreatedBy(created_by: String) = personDao.getPersonCreatedBy(created_by)
    suspend fun getPersonRequestedBy(requested_by: String) = personDao.getPersonRequestedBy(requested_by)
    suspend fun getPersonContact(contact: String) = personDao.getPersonContact(contact)
}