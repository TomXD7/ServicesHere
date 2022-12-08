package com.example.serviceshere

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.serviceshere.Models.DatosPersonalesEntity
import com.example.serviceshere.Models.ServicioEntity
import com.example.serviceshere.databinding.ActivityPerfilBinding
import com.example.serviceshere.databinding.ActivityServiceBinding
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class ServiceActivity : AppCompatActivity() {
    private lateinit var binding: ActivityServiceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_service)
        binding = ActivityServiceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        cargarDatos()

        binding.btnConfirmarServicio.setOnClickListener(){
            if(binding.txtServicio.text.toString().isNotEmpty() && binding.txtDescripcion.text.toString().isNotEmpty() &&
                binding.txtHorarioInicio.text.toString().isNotEmpty() && binding.txtPrecio.text.toString().isNotEmpty()){
                doAsync {

                    val idUser = ServiceHereApplication.database.tokenDao().getUsuarioPorToken("1")
                    if (!intent.hasExtra("edit")) {
                        ServiceHereApplication.database.servicioDao().addServicio(
                            ServicioEntity(
                                nombreServicio = binding.txtServicio.text.toString(),
                                descripcion = binding.txtDescripcion.text.toString(),
                                precio = binding.txtPrecio.text.toString(),
                                horario = binding.txtHorarioInicio.text.toString(),
                                serviciouserfk = idUser
                            )
                        )
                    }else{
                        ServiceHereApplication.database.servicioDao().updateServicio(
                            ServicioEntity(
                                id = intent.getStringExtra("edit").toString().toLong(),
                                nombreServicio = binding.txtServicio.text.toString(),
                                descripcion = binding.txtDescripcion.text.toString(),
                                precio = binding.txtPrecio.text.toString(),
                                horario = binding.txtHorarioInicio.text.toString(),
                                serviciouserfk = idUser
                            )
                        )
                    }
                    uiThread {
                        startActivity(Intent(this@ServiceActivity,PerfilActivity::class.java))
                        finish()
                    }
                }

            } else {
                binding.txtServicio.error = "Campo requerido"
                binding.txtDescripcion.error = "Campo requerido"
                binding.txtHorarioInicio.error = "Campo requerido"
                binding.txtPrecio.error = "Campo requerido"
            }
        }
    }

    fun cargarDatos(){
        doAsync{
            val servicioid = intent.getStringExtra("edit").toString()
            val validarDatos = ServiceHereApplication.database.servicioDao().getServicioPorId(servicioid)

            uiThread {
                if (intent.hasExtra("edit")){
                    binding.txtServicio.setText(validarDatos.getnombreServicio())
                    binding.txtDescripcion.setText(validarDatos.getdescripcion())
                    binding.txtPrecio.setText(validarDatos.getprecio())
                    binding.txtHorarioInicio.setText(validarDatos.gethorario())

                }
            }
        }
    }
}