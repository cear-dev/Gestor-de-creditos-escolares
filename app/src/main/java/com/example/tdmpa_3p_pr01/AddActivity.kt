package com.example.tdmpa_3p_pr01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class AddActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        val dbHelper = DatabaseHelper(this)

        val txtAddUsername = findViewById<EditText>(R.id.txt_addUsername)
        val txtAddPassword = findViewById<EditText>(R.id.txt_addPassword)
        val txtAddName = findViewById<EditText>(R.id.txt_addName)
        val txtAddLastname = findViewById<EditText>(R.id.txt_addLastName)
        val txtAddMajor = findViewById<EditText>(R.id.txt_addMajor)

        val btnAddNewUser = findViewById<Button>(R.id.btn_addNewUser)
        btnAddNewUser.setOnClickListener{
            val loginModel = LoginModel(0, txtAddUsername.text.toString(), txtAddPassword.text.toString(), txtAddName.text.toString(), txtAddLastname.text.toString(), txtAddMajor.text.toString(), 0, 0)
            dbHelper.addLogin(loginModel)
            val toast = Toast.makeText(this, "El usuario ha sido registrado exitosamente", Toast.LENGTH_SHORT).show()
            finish()
        }

    }
}