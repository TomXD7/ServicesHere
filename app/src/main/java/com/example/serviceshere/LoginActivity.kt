package com.example.serviceshere

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.serviceshere.Models.TokenEntity
import com.example.serviceshere.Models.UsuarioEntity
import com.example.serviceshere.databinding.ActivityMainBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //setContentView(R.layout.activity_main)

        binding.lblRegistrarse.setOnClickListener{
            binding.lblRegistrarse.background = resources.getDrawable(R.drawable.switch_trcks,null)
            binding.lblRegistrarse.setTextColor(resources.getColor(R.color.textColor, null))
            binding.lblEntrar.background = null
            binding.registrolayout.visibility = View.VISIBLE
            binding.loginlayout.visibility = View.GONE
            binding.lblEntrar.setTextColor(resources.getColor(R.color.bgColorLogin, null))
        }


        binding.lblEntrar.setOnClickListener{
            binding.lblRegistrarse.background = null
            binding.lblRegistrarse.setTextColor(resources.getColor(R.color.bgColorLogin, null))
            binding.lblEntrar.background = resources.getDrawable(R.drawable.switch_trcks, null)
            binding.registrolayout.visibility = View.GONE
            binding.loginlayout.visibility = View.VISIBLE
            binding.lblEntrar.setTextColor(resources.getColor(R.color.textColor, null))
        }


        binding.btnInvitado.setOnClickListener(){
            doAsync {
                ServiceHereApplication.database.tokenDao().updateToke(TokenEntity(1,usuarioFk = "10000", activo = false))

                uiThread {
                    startActivity(Intent(this@LoginActivity,PrincipalActivity::class.java))
                }
            }

        }

        binding.btnRegistrar.setOnClickListener(){
            //Registrar usuario

            if(binding.txtRUsuario.text.toString().isNotEmpty() && binding.txtRClave.text.toString().isNotEmpty() &&
                binding.txtREmail.text.toString().isNotEmpty() && binding.txtRClave2.text.toString().isNotEmpty()){
                if(binding.txtRClave.text.toString() == binding.txtRClave2.text.toString()) {
                    doAsync {
                        val userE = ServiceHereApplication.database.usuarioDao().getUsuario(binding.txtRUsuario.text.toString(),binding.txtRClave.text.toString())
                        if(!userE){
                            ServiceHereApplication.database.usuarioDao().addUsuario(
                                UsuarioEntity(
                                    usuario = binding.txtRUsuario.text.toString(),
                                    contrasena = binding.txtRClave.text.toString(),
                                    correo = binding.txtREmail.text.toString()
                                )
                            )
                        }
                        uiThread {
                            if(userE){
                                binding.txtRUsuario.error = "El usuario ya existe"
                            }
                            binding.lblRegistrarse.background = null
                            binding.lblRegistrarse.setTextColor(resources.getColor(R.color.bgColorLogin, null))
                            binding.lblEntrar.background = resources.getDrawable(R.drawable.switch_trcks, null)
                            binding.registrolayout.visibility = View.GONE
                            binding.loginlayout.visibility = View.VISIBLE
                            binding.lblEntrar.setTextColor(resources.getColor(R.color.textColor, null))

                            binding.txtRUsuario.setText("")
                            binding.txtREmail.setText("")
                            binding.txtRClave.setText("")
                            binding.txtRClave2.setText("")

                        }
                    }
                }else{
                    binding.txtRClave.error = "No coinciden las contraseñas"
                }
            } else {
                binding.txtRUsuario.error = "Campo requerido"
                binding.txtRClave.error = "Campo requerido"
                binding.txtREmail.error = "Campo requerido"
                binding.txtRClave2.error = "Campo requerido"
            }
        }

        binding.btnEntrar.setOnClickListener (){
            //Iniciar sesion
            login()
        }
    }

    private fun login(){
        if(binding.txtUsuario.text.toString().isNotEmpty() && binding.txtClave.text.toString().isNotEmpty()){
            val userName = binding.txtUsuario.text.toString()
            doAsync {
                val usuarios = ServiceHereApplication.database.usuarioDao().getUsuario(
                    binding.txtUsuario.text.toString(),
                    binding.txtClave.text.toString()
                )
                uiThread {
                    accesoUsuario(usuarios, userName)
                }
            }
        }else{
            MaterialAlertDialogBuilder(this)
                .setTitle("Campos")
                .setMessage("Complete los campos")
                .setPositiveButton("Aceptar", null)
                .show()
        }
    }

    private fun accesoUsuario(usuarios : Boolean, userName : String){
        if(!usuarios){
            MaterialAlertDialogBuilder(this)
                .setTitle("Credenciales incorrectas...")
                .setMessage("Usuario/Contraseña no son correctos")
                .setPositiveButton("Aceptar", null)
                .show()
        }else {
            activarToken()
            startActivity(Intent(this, PrincipalActivity::class.java))
            Toast.makeText(this, "Bienvenido $userName", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
    private fun activarToken(){
        doAsync {

            if(ServiceHereApplication.database.tokenDao().getAll().isEmpty()){
                var idUserLong = ServiceHereApplication.database.usuarioDao().getIdUsuario(binding.txtUsuario.text.toString())
                var idUser = idUserLong.toString()
                ServiceHereApplication.database.tokenDao().addToken(TokenEntity(1,usuarioFk = idUser, activo = true))
            }else{
                var idUserLong = ServiceHereApplication.database.usuarioDao().getIdUsuario(binding.txtUsuario.text.toString())
                var idUser = idUserLong.toString()
                ServiceHereApplication.database.tokenDao().updateToke(TokenEntity(1,usuarioFk = idUser, activo = true))
            }
            uiThread {

            }
        }

    }

}