package de.nicidienase.geniesser_app.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query

@Dao
abstract class CategoryDao: BaseDao<Category>() {

    @Query("SELECT * FROM Category")
    abstract fun getAll(): LiveData<List<Category>>
}