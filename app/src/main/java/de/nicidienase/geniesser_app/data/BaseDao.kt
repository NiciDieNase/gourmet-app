package de.nicidienase.geniesser_app.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

@Dao
abstract class BaseDao<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(vararg item: T): List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(items: List<T>): List<Long>

    @Update
    abstract fun update(vararg item: T)

    @Delete
    abstract fun delete(vararg item: T)
}
