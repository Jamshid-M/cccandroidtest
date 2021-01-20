package uz.jamshid.androidtest

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import uz.jamshid.androidtest.data.local.AppDataBase

class MyApplication : Application() {
    private val applicationScope = CoroutineScope(SupervisorJob())

    private val database by lazy {
        AppDataBase.getDatabase(this, applicationScope)
    }

    val estimateDao by lazy {
        database.getEstimateDao()
    }

    val personDao by lazy {
        database.getPersonDao()
    }
}