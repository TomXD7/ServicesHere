package com.example.serviceshere

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.serviceshere.Adapters.ServicioAdapter
import com.example.serviceshere.Adapters.ServicioAdapterPerfil
import com.example.serviceshere.Models.ServicioEntity
import com.example.serviceshere.databinding.ActivityPerfilBinding
import com.example.serviceshere.databinding.ActivityPrincipalBinding
import com.example.serviceshere.databinding.ActivityServiciosPorUsuarioBinding
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class ServiciosPorUsuario : AppCompatActivity() {
    private lateinit var binding: ActivityServiciosPorUsuarioBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var servicioAdapterPerfil: ServicioAdapterPerfil
    private lateinit var linearLayoutManager: LinearLayoutManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityServiciosPorUsuarioBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Habilitar action bar
        configRecyclerView()
        getServicios()

        binding.imgAgregar.setOnClickListener(){
            startActivity(Intent(this, ServiceActivity::class.java))
            finish()
        }
    }



    private fun configRecyclerView(){
        recyclerView = binding.servicios
        servicioAdapterPerfil = ServicioAdapterPerfil(mutableListOf(), this)
        linearLayoutManager = LinearLayoutManager(this)

        recyclerView.apply {
            recyclerView.setHasFixedSize(true)
            recyclerView.layoutManager = linearLayoutManager
            recyclerView.adapter = servicioAdapterPerfil
        }
    }

    private fun getServicios(){
        doAsync {
            val idUser = ServiceHereApplication.database.tokenDao().getUsuarioPorToken("1")
            val servicio = ServiceHereApplication.database.servicioDao().getServicioPorUsuario(idUser)
            uiThread {
                servicioAdapterPerfil.setServicio(servicio)
            }
        }
    }

    fun onClickListener(servicioEntity: ServicioEntity, position: Int) {
        val i = Intent(this@ServiciosPorUsuario, ServiceActivity::class.java)
        i.putExtra("edit",servicioEntity.getid().toString())
        this.startActivity(i)
    }

    fun onDeleteServicio(servicioEntity: ServicioEntity, position: Int) {
        AlertDialog.Builder(this)
            .setTitle("Eliminar")
            .setMessage("Realmente desea eliminar?")
            .setPositiveButton("Aceptar", DialogInterface.OnClickListener { dialog, id ->
                doAsync {
                    ServiceHereApplication.database.servicioDao().deleteServicio(servicioEntity)
                    uiThread {
                        servicioAdapterPerfil.deleteServicio(servicioEntity)
                    }
                }
            })
            .setNegativeButton("Cancelar",null)
            .show()


    }
}