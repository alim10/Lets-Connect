package org.alimapps.letsconnect.core.common

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

/**
 * Generic DAO contains basic functions to cut boilerplate code
 */
interface BaseDao<T> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(obj: T)
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg obj: T)
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(list: List<T>)
    
    @Delete
    suspend fun delete(obj: T)
    
    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(obj: T)
    
    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(vararg obj: T)
}