package com.example.roomproject

import androidx.room.*

@Dao
interface TaskDao {
    @Query("SELECT * FROM tasks")
    fun getAll(): List<TaskModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(task: TaskModel)

    @Delete
    fun delete(task: TaskModel)
}