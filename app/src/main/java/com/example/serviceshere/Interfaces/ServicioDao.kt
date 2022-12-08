package com.example.serviceshere.Interfaces

import androidx.room.*
import com.example.serviceshere.Models.ServicioEntity

@Dao
interface ServicioDao {
    @Query("SELECT * FROM ServicioEntity")
    fun getAll(): MutableList<ServicioEntity>

    @Query("SELECT * FROM ServicioEntity where serviciouserfk=(:idU)")
    fun getServicioPorUsuario(idU:String): MutableList<ServicioEntity>

    @Query("SELECT * FROM ServicioEntity where id=(:idU)")
    fun getServicioPorId(idU:String): ServicioEntity

    @Insert
    fun addServicio(ServicioEntity: ServicioEntity)

    @Update
    fun updateServicio(ServicioEntity: ServicioEntity)

    @Delete
    fun deleteServicio(ServicioEntity: ServicioEntity)

}