package de.nicidienase.geniesser_app.data.fccampus

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import de.nicidienase.geniesser_app.data.BaseDao

@Dao
abstract class FcNewsDao : BaseDao<FcNews>() {

    @Query("SELECT * FROM FcNews")
    abstract fun getAll(): LiveData<List<FcNews>>
}
