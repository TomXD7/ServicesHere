package com.example.serviceshere

import android.app.Activity
import android.content.Intent

object ImagenControlador {
    fun seleccionarImagen(activity: Activity, code: Int){
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        activity.startActivityForResult(intent,code)
    }
}