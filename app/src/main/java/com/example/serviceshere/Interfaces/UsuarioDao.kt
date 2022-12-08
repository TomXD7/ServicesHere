package com.example.serviceshere.Interfaces

import androidx.room.*
import com.example.serviceshere.Models.UsuarioEntity

@Dao
interface UsuarioDao {
    @Query("SELECT * FROM UsuarioEntity")
    fun getAll(): MutableList<UsuarioEntity>

    @Query("SELECT * FROM UsuarioEntity WHERE usuario=(:usuarioId) and contrasena=(:passwordId)")
    fun getUsuario(usuarioId: String, passwordId: String) : Boolean

    @Query("SELECT id FROM UsuarioEntity WHERE usuario=(:usuarioId)")
    fun getIdUsuario(usuarioId: String) : Long

    @Query("SELECT * FROM UsuarioEntity WHERE id=(:usuarioId)")
    fun getUsuarioEntity(usuarioId: Long) : UsuarioEntity

    @Insert
    fun addUsuario(UsuarioEntity: UsuarioEntity)

    @Update
    fun updateUsuario(UsuarioEntity: UsuarioEntity)

    @Delete
    fun deleteUsuario(UsuarioEntity: UsuarioEntity)

    @Query("Delete from UsuarioEntity")
    fun alv()
}