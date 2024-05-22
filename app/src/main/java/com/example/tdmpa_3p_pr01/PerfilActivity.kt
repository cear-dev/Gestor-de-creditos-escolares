package com.example.tdmpa_3p_pr01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class PerfilActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil)

        val txtUsername = findViewById<TextView>(R.id.txt_Username)
        val txtName = findViewById<TextView>(R.id.txt_name)
        val txtLastName = findViewById<TextView>(R.id.txt_lastName)
        val txtMajor = findViewById<TextView>(R.id.txt_major)
        val txtDeportivos = findViewById<TextView>(R.id.txt_deportivos)
        val txtCulturales = findViewById<TextView>(R.id.txt_culturales)

        val dbHelper = DatabaseHelper(this)
        val username = intent.getStringExtra("username")

        val retrieveLogin = dbHelper.getLoginByUsername(username.toString())
        txtUsername.text = "Nombre de usuario: ${username}"
        txtName.text = "Nombre: ${retrieveLogin?.name.toString()}"
        txtLastName.text = "Apellido: ${retrieveLogin?.lastname.toString()}"
        txtMajor.text = "Carrera: ${retrieveLogin?.major.toString()}"
        txtDeportivos.text = "Créditos deportivos: ${retrieveLogin?.deportivos.toString()}"
        txtCulturales.text = "Créditos culturales: ${retrieveLogin?.culturales.toString()}"
    }
}