package com.example.serviceshere.Models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "DatosPersonalesEntity")
class DatosPersonalesEntity (
    @PrimaryKey(autoGenerate = true)
    var id: Long= 0,
    var usuarioFk: String,
    var telefono: String,
    var nombre: String,
    var apellido: String,
    var direccion: String,
    var profesion: String,
    var fecha: String){

    fun getid() : Long = this.id

    fun getusuarioFk() : String = this.usuarioFk

    fun gettelefono(): String = this.telefono

    fun getnombre(): String = this.nombre

    fun getapellido() : String = this.apellido

    fun getdireccion(): String = this.direccion

    fun getprofesion(): String = this.profesion

    fun getfecha(): String = this.fecha
}