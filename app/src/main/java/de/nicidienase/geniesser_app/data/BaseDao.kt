package de.nicidienase.geniesser_app.data

import androidx.room.*

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