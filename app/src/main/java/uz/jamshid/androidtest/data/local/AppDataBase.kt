package uz.jamshid.androidtest.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import uz.jamshid.androidtest.data.model.Estimate
import uz.jamshid.androidtest.data.model.Person

@Database(entities = [Estimate::class, Person::class], version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun getEstimateDao(): EstimateDao
    abstract fun getPersonDao(): PersonDao

    class OnCreateCallback(private val scope: CoroutineScope) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let {
                scope.launch {
                    val estimateDao = it.getEstimateDao()
                    estimateDao.insert(
                        Estimate(
                            "c574b0b4-bdef-4b92-a8f0-608a86ccf5ab",
                            "Placebo Secondary School",
                            "32 Commissioners Road East",
                            32,
                            3,
                            "2020-08-22 15:23:54",
                            "85a57f85-a52d-4137-a0d1-62e61362f716",
                            "85a57f85-a52d-4137-a0d1-62e61362f716",
                            "85a57f85-a52d-4137-a0d1-62e61362f716"
                        )
                    )

                    val personDao = it.getPersonDao()
                    personDao.insert(
                        Person(
                            "85a57f85-a52d-4137-a0d1-62e61362f716",
                            "Joseph",
                            "Sham",
                            "joseph.sham@fake.fake",
                            "123-456-7890"
                        )
                    )
                }
            }
        }
    }

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: AppDataBase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): AppDataBase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java,
                    "word_database"
                ).addCallback(OnCreateCallback(scope))
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}