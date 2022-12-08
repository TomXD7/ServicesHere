package com.example.serviceshere

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.serviceshere.Models.DatosPersonalesEntity
import com.example.serviceshere.Models.UsuarioEntity
import com.example.serviceshere.databinding.ActivityInfoPerBinding
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class InfoPerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInfoPerBinding
    private val SELECT_ACTIVITY = 50
    private var imageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info_per)
        binding = ActivityInfoPerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        cargarDatos()

        binding.btnFoto.setOnClickListener(){
            ImagenControlador.seleccionarImagen(this,SELECT_ACTIVITY)
        }

        binding.btnConfirmarServicio.setOnClickListener(){
            if(binding.txtNombres.text.toString().isNotEmpty() && binding.txtApellidos.text.toString().isNotEmpty() &&
                binding.txtFecha.text.toString().isNotEmpty() && binding.txtProfesion.text.toString().isNotEmpty()){
                    doAsync {
                        val idUser = ServiceHereApplication.database.tokenDao().getUsuarioPorToken("1")
                        val validarDatos = ServiceHereApplication.database.datosPersonalesDao().getComprobacion(idUser.toLong())
                        if (validarDatos.isEmpty()) {
                            ServiceHereApplication.database.datosPersonalesDao().addDatosPersonales(
                                DatosPersonalesEntity(
                                    usuarioFk = idUser,
                                    apellido = binding.txtApellidos.text.toString(),
                                    nombre = binding.txtNombres.text.toString(),
                                    telefono = binding.txtTelefono.text.toString(),
                                    direccion = binding.txtDireccion.text.toString(),
                                    profesion = binding.txtProfesion.text.toString(),
                                    fecha = binding.txtFecha.text.toString()

                                )
                            )
                        }else{

                            ServiceHereApplication.database.datosPersonalesDao().updateDatosPersonales(
                                DatosPersonalesEntity(
                                    id = validarDatos[0].getid(),
                                    usuarioFk = idUser,
                                    apellido = binding.txtApellidos.text.toString(),
                                    nombre = binding.txtNombres.text.toString(),
                                    telefono = binding.txtTelefono.text.toString(),
                                    direccion = binding.txtDireccion.text.toString(),
                                    profesion = binding.txtProfesion.text.toString(),
                                    fecha = binding.txtFecha.text.toString()
                                )
                            )
                        }

                        uiThread {
                            finish()
                        }
                    }

            } else {
                binding.txtNombres.error = "Campo requerido"
                binding.txtApellidos.error = "Campo requerido"
                binding.txtProfesion.error = "Campo requerido"
                binding.txtDireccion.error = "Campo requerido"
                binding.txtTelefono.error = "Campo requerido"
                binding.txtFecha.error = "Campo requerido"
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when{
            requestCode == SELECT_ACTIVITY && resultCode== Activity.RESULT_OK ->{
                imageUri = data!!.data

            }
        }
    }

    fun cargarDatos(){
        doAsync{
            val idUser = ServiceHereApplication.database.tokenDao().getUsuarioPorToken("1")
            val validarDatos = ServiceHereApplication.database.datosPersonalesDao().getComprobacion(idUser.toLong())

            uiThread {
                if (validarDatos.isNotEmpty()){
                    binding.txtNombres.setText(validarDatos[0].getnombre())
                    binding.txtApellidos.setText(validarDatos[0].getapellido())
                    binding.txtProfesion.setText(validarDatos[0].getprofesion())
                    binding.txtDireccion.setText(validarDatos[0].getdireccion())
                    binding.txtTelefono.setText(validarDatos[0].gettelefono())
                    binding.txtFecha.setText(validarDatos[0].getfecha())
                }
            }
        }
    }
}