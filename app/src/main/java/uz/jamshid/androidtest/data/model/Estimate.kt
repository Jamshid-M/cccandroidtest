package uz.jamshid.androidtest.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "estimate")
data class Estimate(
    @PrimaryKey val id: String, val company: String, val address: String,
    val number: Int, val revision_number: Int, val created_date: String,
    val created_by: String, val requested_by: String, val contact: String
) {

    companion object {
        fun newEmpty() = Estimate(
            "", "", "", 0, 0, "",
            "", "", ""
        )
    }
}