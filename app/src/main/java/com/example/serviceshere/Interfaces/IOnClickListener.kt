package com.example.serviceshere.Interfaces

import com.example.serviceshere.Models.DatosPersonalesEntity
import com.example.serviceshere.Models.ServicioEntity
import com.example.serviceshere.Models.TokenEntity
import com.example.serviceshere.Models.UsuarioEntity

interface IOnClickListener {
    fun onDeleteUsuario(usuarioEntity: UsuarioEntity, position: Int)
    fun onDeleteServicio(servicioEntity: ServicioEntity,position: Int)
    fun onDeleteDatosPersonales(datosPersonalesEntity: DatosPersonalesEntity,position: Int)
    fun onDeleteToken(tokenEntity: TokenEntity,position: Int)

}