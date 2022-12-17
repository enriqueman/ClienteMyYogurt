package edu.unicauca.aplimovil.clientemyyogurt.roomdb

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query


@Dao

interface YogurtDao{
    @Insert
    fun insertYogurt(product : YogurtModel)
    @Delete
    fun deleteYogurt(product: YogurtModel)
    @Query("SELECT * FROM yogures")
    fun getAllYogurt() : List<YogurtModel>
    @Query("SELECT * FROM yogures WHERE yogurtId =:id")
    fun isExit(id: String) : YogurtModel


}