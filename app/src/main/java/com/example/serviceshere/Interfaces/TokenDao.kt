package com.example.serviceshere.Interfaces

import androidx.room.*
import com.example.serviceshere.Models.TokenEntity

@Dao
interface TokenDao {
    @Query("SELECT * FROM TokenEntity")
    fun getAll(): MutableList<TokenEntity>

    @Query("SELECT usuarioFk FROM TokenEntity where id=(:idU)")
    fun getUsuarioPorToken(idU: String): String

    @Query("SELECT * FROM TokenEntity")
    fun getToken(): TokenEntity

    @Insert
    fun addToken(TokenEntity: TokenEntity)

    @Update
    fun updateToke(TokenEntity: TokenEntity)

    @Delete
    fun deleteToken(TokenEntity: TokenEntity)

    @Query("Delete from TokenEntity")
    fun alv()
}