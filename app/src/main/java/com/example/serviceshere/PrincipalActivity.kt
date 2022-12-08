package com.example.serviceshere

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.serviceshere.Adapters.ServicioAdapter
import com.example.serviceshere.Models.ServicioEntity
import com.example.serviceshere.databinding.ActivityPrincipalBinding
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread


class PrincipalActivity : AppCompatActivity() {
    // Variable para configurar viewBinding
    private lateinit var binding: ActivityPrincipalBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var servicioAdapter: ServicioAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Configuracion de viewBinding
        binding = ActivityPrincipalBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Habilitar action bar
        configRecyclerView()
        getServicios()

        binding.imgCuenta.setOnClickListener(){
            //startActivity(Intent(this, PerfilActivity::class.java))
            doAsync {
                val idUser = ServiceHereApplication.database.tokenDao().getUsuarioPorToken("1").toLong()
                val validarToken = ServiceHereApplication.database.tokenDao().getToken()
                val validarDatos = ServiceHereApplication.database.datosPersonalesDao().getComprobacion(idUser)
                if (validarToken.getactivo()){
                    if (validarDatos.isNotEmpty()){
                        irAlPerfil()

                    }else{
                        irAgregarInfo()
                        finish()
                    }
                }
                uiThread {

                }
            }

        }
    }

    private fun configRecyclerView(){
        recyclerView = binding.servicios
        servicioAdapter = ServicioAdapter(mutableListOf(), this)
        linearLayoutManager = LinearLayoutManager(this)

        recyclerView.apply {
            recyclerView.setHasFixedSize(true)
            recyclerView.layoutManager = linearLayoutManager
            recyclerView.adapter = servicioAdapter
        }
    }

    private fun getServicios(){
        doAsync {
            val servicio = ServiceHereApplication.database.servicioDao().getAll()
            uiThread {
                servicioAdapter.setServicio(servicio)
            }
        }
    }

    fun onClickListener(servicioEntity: ServicioEntity, position: Int) {
        irAlPerfilEspecifico(servicioEntity.getserviciouserfk())
    }

    fun irAlPerfil(){
        startActivity(Intent(this, PerfilActivity::class.java))
    }

    fun irAgregarInfo(){
        startActivity(Intent(this, InfoPerActivity::class.java))
    }

    fun irAlPerfilEspecifico(userid:String){
        val i = Intent(this, PerfilActivity::class.java)
        i.putExtra("idUsuario",userid)
        this.startActivity(i)
    }
}