package uz.jamshid.androidtest.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "person")
data class Person(
    @PrimaryKey val id: String, val first_name: String, val last_name: String,
    val email: String, val phone_number: String
) {
    fun name() = "$first_name $last_name"

    companion object {
        fun newEmpty() = Person("", "", "", "", "")
    }
}