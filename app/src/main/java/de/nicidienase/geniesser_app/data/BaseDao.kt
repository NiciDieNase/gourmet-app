package de.nicidienase.geniesser_app.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update

@Dao
abstract class BaseDao<T> {

    @Insert
    abstract fun insert(vararg item: T)

    @Update
    abstract fun update(vararg item: T)

    @Delete
    abstract fun delete(vararg item: T)
}