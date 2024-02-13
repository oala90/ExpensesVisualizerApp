package com.example.expensesvisualizerapp.data.dto

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.expensesvisualizerapp.data.dto.Expenses

@Entity(tableName = "persons")
data class Person(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    val name: String?,
    val age: Int,
    val position: String?,
    val budget: Int,
    val expenses: List<Expenses>?
)
//    : Parcelable {
//    constructor(parcel: Parcel) : this(
//        parcel.readLong(),
//        parcel.readString(),
//        parcel.readInt(),
//        parcel.readString(),
//        parcel.readInt(),
//        TODO("expenses")
//    ) {
//    }
//
//    override fun writeToParcel(parcel: Parcel, flags: Int) {
//        parcel.writeLong(id)
//        parcel.writeString(name)
//        parcel.writeInt(age)
//        parcel.writeString(position)
//        parcel.writeInt(budget)
//    }
//
//    override fun describeContents(): Int {
//        return 0
//    }
//
//    companion object CREATOR : Parcelable.Creator<Person> {
//        override fun createFromParcel(parcel: Parcel): Person {
//            return Person(parcel)
//        }
//
//        override fun newArray(size: Int): Array<Person?> {
//            return arrayOfNulls(size)
//        }
//    }
//}