package com.example.jetpackemployeedetails.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.jetpackemployeedetails.model.DataModel

@Dao
interface AppDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(dataModel: List<DataModel>)

    @Query("select * from Employee_Details")
    suspend fun getAllData():List<DataModel>

    @Query("select count(*) from Employee_Details")
    suspend fun getAllDataCount():Long

}