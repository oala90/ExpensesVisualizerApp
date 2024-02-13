package com.example.expensesvisualizerapp.domain.entities

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

data class ExpensesEntity(
    val id: Long,
    val personId: Long,
    val description: String,
    val amount: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readLong(),
        parcel.readString() ?: "",
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeLong(personId)
        parcel.writeString(description)
        parcel.writeInt(amount)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ExpensesEntity> {
        override fun createFromParcel(parcel: Parcel): ExpensesEntity {
            return ExpensesEntity(parcel)
        }

        override fun newArray(size: Int): Array<ExpensesEntity?> {
            return arrayOfNulls(size)
        }
    }
}