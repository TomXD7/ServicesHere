package com.example.serviceshere.Models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "UsuarioEntity")
class UsuarioEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long= 0,
    var usuario: String,
    var contrasena: String,
    var correo: String){

    fun getid() : Long = this.id

    fun getusuario(): String = this.usuario

    fun getcontrasena(): String = this.contrasena

    fun getcorreo() : String = this.correo

}