package com.example.serviceshere.Interfaces

import androidx.room.*
import com.example.serviceshere.Models.DatosPersonalesEntity

@Dao
interface DatosPersonalesDao {
    @Query("SELECT * FROM DatosPersonalesEntity")
    fun getAll(): MutableList<DatosPersonalesEntity>

    @Query("SELECT * FROM DatosPersonalesEntity WHERE usuarioFk=(:idU)")
    fun getComprobacion(idU: Long): MutableList<DatosPersonalesEntity>

    @Query("SELECT * FROM DatosPersonalesEntity WHERE usuarioFk=(:idU)")
    fun getDatosPorUsuario(idU: Long) : DatosPersonalesEntity

    @Insert
    fun addDatosPersonales(DatosPersonalesEntity: DatosPersonalesEntity)

    @Update
    fun updateDatosPersonales(DatosPersonalesEntity: DatosPersonalesEntity)

    @Delete
    fun deleteDatosPersonales(DatosPersonalesEntity: DatosPersonalesEntity)
}