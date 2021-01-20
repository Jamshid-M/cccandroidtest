package uz.jamshid.androidtest.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import uz.jamshid.androidtest.data.model.Estimate

@Dao
interface EstimateDao {

    @Query("SELECT * FROM estimate")
    suspend fun getEstimates(): Estimate

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(estimate: Estimate)
}