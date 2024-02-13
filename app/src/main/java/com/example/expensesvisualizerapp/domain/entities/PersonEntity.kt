package com.example.expensesvisualizerapp.domain.entities

import android.os.Parcel
import android.os.Parcelable

data class PersonEntity(
    val id: Long = 0L,
    val name: String?,
    val age: Int,
    val position: String?,
    val budget: Int,
    val expenses: List<ExpensesEntity>?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readInt(),
        parcel.createTypedArrayList(ExpensesEntity.CREATOR)
    )

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeString(name)
        parcel.writeInt(age)
        parcel.writeString(position)
        parcel.writeInt(budget)
        parcel.writeTypedList(expenses)
    }

    companion object CREATOR : Parcelable.Creator<PersonEntity> {
        override fun createFromParcel(parcel: Parcel): PersonEntity {
            return PersonEntity(parcel)
        }

        override fun newArray(size: Int): Array<PersonEntity?> {
            return arrayOfNulls(size)
        }
    }
}