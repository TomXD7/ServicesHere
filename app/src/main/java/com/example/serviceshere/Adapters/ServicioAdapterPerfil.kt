package com.example.serviceshere.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.serviceshere.Interfaces.ServicioDao
import com.example.serviceshere.Models.ServicioEntity
import com.example.serviceshere.PerfilActivity
import com.example.serviceshere.R
import com.example.serviceshere.PrincipalActivity
import com.example.serviceshere.ServiciosPorUsuario
import com.example.serviceshere.databinding.ItemServicelistBinding


class ServicioAdapterPerfil(private var lstServicio: MutableList<ServicioEntity>, private val listener: ServiciosPorUsuario)
    : RecyclerView.Adapter<ServicioAdapterPerfil.ViewHolder>()  {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val layout = LayoutInflater.from(parent.context)
        return ViewHolder(layout.inflate(R.layout.item_servicelist, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = lstServicio[position]
        // Uso de funcion de alcance para agregar acciones al objeto en un mismo bloque
        with(holder){
            // A continuación se realiza un parceo o casteo
            val servicioEntity: ServicioEntity = item
            setListener(servicioEntity, position)
            // Configurando contenido del cardview en base al objeto casteado
            binding.imageView.setImageResource(R.mipmap.ic_launcher)
            binding.txvNombreServicio.text = servicioEntity.getnombreServicio()
            binding.txvDescripcion.text = servicioEntity.getdescripcion()
        }
    }

    override fun getItemCount(): Int = lstServicio.size

    fun setServicio(servicioEntity: MutableList<ServicioEntity>) {
        this.lstServicio = servicioEntity.toMutableList()
        notifyDataSetChanged()
    }

    fun updateServicio(servicioEntity: ServicioEntity) {
        val index = lstServicio.indexOf(servicioEntity)
        if(index != -1){
            lstServicio[index] = servicioEntity
            notifyItemChanged(index)
        }
    }

    fun deleteServicio(servicioEntity: ServicioEntity) {
        val index = lstServicio.indexOf(servicioEntity)
        if(index != -1){
            lstServicio.removeAt(index)
            notifyItemRemoved(index)
        }
    }


    inner class ViewHolder(view: View) :RecyclerView.ViewHolder(view){
        val binding = ItemServicelistBinding.bind(view)

        fun setListener(servicioEntity: ServicioEntity, position: Int){
            with(binding.root){
                setOnClickListener{
                    listener.onClickListener(servicioEntity, position)
                }
                setOnLongClickListener{
                    listener.onDeleteServicio(servicioEntity, position)
                    true
                }
            }
        }
    }
}