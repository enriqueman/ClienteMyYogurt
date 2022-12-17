package edu.unicauca.aplimovil.clientemyyogurt.roomdb

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName= " ")

data class YogurtModel(
    @PrimaryKey
    @NonNull
    val yogurtId: String,
    @ColumnInfo(name="yogurtName")
    val yogurtName: String? = "",
    @ColumnInfo(name="yogurtImages")
    val yogurtImages: String? = "",
    @ColumnInfo(name="yogurtPrecio")
    val yogurtPrecio: String? = "",


    )
