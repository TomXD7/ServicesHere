package com.example.serviceshere

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.serviceshere.Adapters.ServicioAdapter
import com.example.serviceshere.Adapters.ServicioAdapterPerfil
import com.example.serviceshere.Models.ServicioEntity
import com.example.serviceshere.databinding.ActivityPerfilBinding
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class PerfilActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPerfilBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var servicioAdapterPerfil: ServicioAdapterPerfil
    private lateinit var linearLayoutManager: LinearLayoutManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_perfil)
        binding = ActivityPerfilBinding.inflate(layoutInflater)
        setContentView(binding.root)


        actualizarDatos()


        binding.lblServicios.setOnClickListener(){
            startActivity(Intent(this, ServiciosPorUsuario::class.java))
        }
        binding.lblInfo.setOnClickListener(){
            binding.layoutservicios.visibility = View.GONE
            binding.layoutinfo.visibility = View.VISIBLE
            binding.imgCrearServicio.visibility = View.GONE
        }

        binding.imgEditarPerfil.setOnClickListener(){
            startActivity(Intent(this, InfoPerActivity::class.java))
        }
        binding.imgInicio.setOnClickListener(){
            startActivity(Intent(this, PrincipalActivity::class.java))
            finish()
        }

        binding.imgCrearServicio.setOnClickListener(){
            startActivity(Intent(this, ServiceActivity::class.java))
        }

    }

    private fun actualizarDatos(){

        doAsync {
            val idUser = ServiceHereApplication.database.tokenDao().getUsuarioPorToken("1").toLong()
            val userDeIntent = intent.getStringExtra("idUsuario")
            if (intent.hasExtra("idUsuario")) {
                if (userDeIntent == idUser.toString()) {
                    val usuario =
                        ServiceHereApplication.database.usuarioDao().getUsuarioEntity(idUser)
                    val datos = ServiceHereApplication.database.datosPersonalesDao()
                        .getDatosPorUsuario(idUser)
                    binding.txtCorreo.text = usuario.getcorreo()
                    binding.txtUsuario.text = usuario.getusuario()
                    binding.txtDireccion.text = datos.getdireccion()
                    binding.txtTelefono.text = datos.gettelefono()
                    binding.txtNombre.text = datos.getnombre() + " " + datos.getapellido()
                    binding.txtProfesion.text = datos.getprofesion()
                    binding.imgEditarPerfil.visibility = View.VISIBLE
                    binding.lblServicios.visibility = View.VISIBLE
                    binding.contenedorInfoServ.weightSum = 2.0f
                } else {
                    val usuario = ServiceHereApplication.database.usuarioDao()
                        .getUsuarioEntity(userDeIntent.toString().toLong())
                    val datos = ServiceHereApplication.database.datosPersonalesDao()
                        .getDatosPorUsuario(userDeIntent.toString().toLong())
                    binding.txtCorreo.text = usuario.getcorreo()
                    binding.txtUsuario.text = usuario.getusuario()
                    binding.txtDireccion.text = datos.getdireccion()
                    binding.txtTelefono.text = datos.gettelefono()
                    binding.txtNombre.text = datos.getnombre() + " " + datos.getapellido()
                    binding.txtProfesion.text = datos.getprofesion()
                    binding.imgEditarPerfil.visibility = View.GONE
                    binding.lblServicios.visibility = View.GONE
                    binding.contenedorInfoServ.weightSum = 1.0f
                }
            }else{
                val usuario =
                    ServiceHereApplication.database.usuarioDao().getUsuarioEntity(idUser)
                val datos = ServiceHereApplication.database.datosPersonalesDao()
                    .getDatosPorUsuario(idUser)
                binding.txtCorreo.text = usuario.getcorreo()
                binding.txtUsuario.text = usuario.getusuario()
                binding.txtDireccion.text = datos.getdireccion()
                binding.txtTelefono.text = datos.gettelefono()
                binding.txtNombre.text = datos.getnombre() + " " + datos.getapellido()
                binding.txtProfesion.text = datos.getprofesion()
                binding.imgEditarPerfil.visibility = View.VISIBLE
                binding.lblServicios.visibility = View.VISIBLE
                binding.contenedorInfoServ.weightSum = 2.0f
            }

            uiThread {

            }
        }



    }



    fun onClickListener(servicioEntity: ServicioEntity, position: Int) {

    }
}