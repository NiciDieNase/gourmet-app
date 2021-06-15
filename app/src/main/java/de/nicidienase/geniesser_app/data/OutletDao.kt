package de.nicidienase.geniesser_app.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query

@Dao
abstract class OutletDao : BaseDao<Outlet>() {

    @Query("SELECT * FROM outlet")
    abstract fun getAll(): LiveData<List<Outlet>>

    @Query("SELECT * FROM outlet WHERE locationId = :locationId")
    abstract fun getOutletsForLocation(locationId: Long): LiveData<List<Outlet>>

    @Query("SELECT * FROM outlet WHERE locationId = :locationId ORDER BY `order` LIMIT 1")
    abstract suspend fun getFirstOutletForLocation(locationId: Long): Outlet

    @Query("SELECT * FROM outlet WHERE outletId = :outletId")
    abstract fun getOutletById(outletId: Long): LiveData<Outlet>
}
