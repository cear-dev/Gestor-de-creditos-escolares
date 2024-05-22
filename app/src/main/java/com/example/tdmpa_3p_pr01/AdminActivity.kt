package com.example.tdmpa_3p_pr01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class AdminActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin)

        val dbHelper = DatabaseHelper(this)

        val txtUserAdmin = findViewById<EditText>(R.id.txt_userEdit)
        val btnEdit = findViewById<Button>(R.id.btn_searchUser)
        val btnAdd = findViewById<Button>(R.id.btn_addUser)

        btnAdd.setOnClickListener{
            var intento = Intent(this@AdminActivity, AddActivity::class.java)
            startActivity(intento)
        }

        btnEdit.setOnClickListener{
            val retrieveLogin = dbHelper.getLoginByUsername(txtUserAdmin.text.toString())
            if(retrieveLogin?.password != null) {
                var intento = Intent(this@AdminActivity, EditActivity::class.java)
                intento.putExtra("username", retrieveLogin?.username.toString())
                startActivity(intento)
            }
            else{
                Toast.makeText(this@AdminActivity, "Error: Usuario no encontrado", Toast.LENGTH_SHORT).show()
            }
        }
    }
}