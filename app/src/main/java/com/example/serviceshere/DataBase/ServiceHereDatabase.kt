package com.example.serviceshere.DataBase

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.serviceshere.Interfaces.DatosPersonalesDao
import com.example.serviceshere.Interfaces.ServicioDao
import com.example.serviceshere.Interfaces.TokenDao
import com.example.serviceshere.Interfaces.UsuarioDao
import com.example.serviceshere.Models.DatosPersonalesEntity
import com.example.serviceshere.Models.ServicioEntity
import com.example.serviceshere.Models.TokenEntity
import com.example.serviceshere.Models.UsuarioEntity

@Database(entities = [UsuarioEntity::class, ServicioEntity::class, DatosPersonalesEntity::class, TokenEntity::class], version = 2)
abstract class ServiceHereDatabase: RoomDatabase() {
    abstract fun usuarioDao(): UsuarioDao
    abstract fun servicioDao(): ServicioDao
    abstract fun datosPersonalesDao(): DatosPersonalesDao
    abstract fun tokenDao(): TokenDao

}