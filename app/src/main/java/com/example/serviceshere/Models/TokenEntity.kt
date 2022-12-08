package com.example.serviceshere.Models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TokenEntity")
class TokenEntity (
    @PrimaryKey(autoGenerate = true)
    var id: Long= 0,
    var usuarioFk: String,
    var activo:Boolean){

    fun getid() : Long = this.id

    fun getusuario(): String = this.usuarioFk

    fun getactivo(): Boolean = this.activo
}