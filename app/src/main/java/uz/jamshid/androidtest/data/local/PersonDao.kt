package uz.jamshid.androidtest.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import uz.jamshid.androidtest.data.model.Person

@Dao
interface PersonDao {

    @Query("SELECT * FROM person WHERE id = :created_by")
    suspend fun getPersonCreatedBy(created_by: String): Person

    @Query("SELECT * FROM person WHERE id = :requested_by")
    suspend fun getPersonRequestedBy(requested_by: String): Person

    @Query("SELECT * FROM person WHERE id = :contact")
    suspend fun getPersonContact(contact: String): Person

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(person: Person)
}