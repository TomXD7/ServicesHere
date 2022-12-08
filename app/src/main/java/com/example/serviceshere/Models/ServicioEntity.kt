package com.example.serviceshere.Models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ServicioEntity")
class ServicioEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long= 0,
    var nombreServicio : String,
    var descripcion : String,
    var precio: String,
    var horario: String,
    var serviciouserfk : String
){
    fun getid() : Long = this.id

    fun getnombreServicio(): String = this.nombreServicio

    fun getdescripcion(): String = this.descripcion

    fun getprecio() : String = this.precio

    fun gethorario(): String = this.horario

    fun getserviciouserfk() : String = this.serviciouserfk
}