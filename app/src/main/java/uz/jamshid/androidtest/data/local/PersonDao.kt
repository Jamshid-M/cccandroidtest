package uz.jamshid.androidtest.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Flowable
import uz.jamshid.androidtest.data.model.Person

@Dao
interface PersonDao {

    @Query("SELECT * FROM person WHERE id = :created_by")
    fun getPersonCreatedBy(created_by: String): Flowable<Person>

    @Query("SELECT * FROM person WHERE id = :requested_by")
    fun getPersonRequestedBy(requested_by: String): Flowable<Person>

    @Query("SELECT * FROM person WHERE id = :contact")
    fun getPersonContact(contact: String): Flowable<Person>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(person: Person)
}