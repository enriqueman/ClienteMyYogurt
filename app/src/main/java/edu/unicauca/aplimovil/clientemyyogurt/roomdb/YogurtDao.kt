package edu.unicauca.aplimovil.clientemyyogurt.roomdb

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query


@Dao

interface YogurtDao{
    @Insert
    suspend fun insertYogurt(product : YogurtModel)
    @Delete
    suspend fun deleteYogurt(product: YogurtModel)
    @Query("SELECT * FROM yogures")
    fun getAllYogurt() : LiveData<List<YogurtModel>>
    @Query("SELECT * FROM yogures WHERE yogurtId =:id")
    fun isExit(id: String) : YogurtModel


}