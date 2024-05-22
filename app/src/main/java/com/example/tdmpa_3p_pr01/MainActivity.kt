package com.example.tdmpa_3p_pr01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val txtUser = findViewById<EditText>(R.id.txt_user)
        val txtPass = findViewById<EditText>(R.id.txt_password)
        val btnLogin = findViewById<Button>(R.id.btn_login)

        btnLogin.setOnClickListener{
            var correcto = iniciar(txtUser.text.toString(), txtPass.text.toString())
            var admin = adminCheck(txtUser.text.toString(), txtPass.text.toString())
            if(correcto){
                if(admin){
                    val intento = Intent(this@MainActivity, AdminActivity::class.java)
                    startActivity(intento)
                }
                else{
                    val intento = Intent(this@MainActivity, PerfilActivity::class.java)
                    intento.putExtra("username",txtUser.text.toString())
                    startActivity(intento)
                }
            }
            else{
                Toast.makeText(this, "El usuario o la contraseña son incorrectos", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun iniciar(user:String, password:String):Boolean {
        var correcto = false

        if(user == "root"){
            if(password == "root"){
                correcto = true
            }
        }

        val dbHelper = DatabaseHelper(this)
        val retrieveLogin = dbHelper.getLoginByUsername(user)
        if(retrieveLogin != null){
            if(retrieveLogin.password.equals(password)){
                correcto = true
            }
        }
        return correcto
    }

    fun adminCheck(user: String, password: String):Boolean {
        var admin = false
        if(user == "root"){
            if(password == "root"){
                admin = true
            }
        }
        return admin
    }
}