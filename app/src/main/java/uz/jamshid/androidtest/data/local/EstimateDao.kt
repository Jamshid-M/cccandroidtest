package uz.jamshid.androidtest.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Flowable
import uz.jamshid.androidtest.data.model.Estimate

@Dao
interface EstimateDao {

    @Query("SELECT * FROM estimate")
    fun getEstimates(): Flowable<Estimate>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(estimate: Estimate)
}