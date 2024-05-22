package com.example.tdmpa_3p_pr01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class EditActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        val dbHelper = DatabaseHelper(this)

        var actualView = ""

        val username = intent.getStringExtra("username")
        val txtEditPassword = findViewById<EditText>(R.id.txt_newPassword)
        val txtEditDeportivos = findViewById<EditText>(R.id.txt_editDeportivos)
        val txtEditCulturales = findViewById<EditText>(R.id.txt_editCulturales)
        val btnSaveChanges = findViewById<Button>(R.id.btn_saveChanges)
        val btnNewPassword = findViewById<Button>(R.id.btn_editPassword)
        val btnEditCreditos = findViewById<Button>(R.id.btn_editCreditos)
        val txtReadDeportivos = findViewById<TextView>(R.id.txt_creditosDeportivos)
        val txtReadCulturales = findViewById<TextView>(R.id.txt_creditosCulturales)
        val txtReadPassword = findViewById<TextView>(R.id.txt_pass)

        fun hideAll(){
            txtEditCulturales.visibility = View.INVISIBLE
            txtEditDeportivos.visibility = View.INVISIBLE
            txtEditPassword.visibility = View.INVISIBLE
            txtReadCulturales.visibility = View.INVISIBLE
            txtReadDeportivos.visibility = View.INVISIBLE
            txtReadPassword.visibility = View.INVISIBLE
            btnSaveChanges.visibility = View.INVISIBLE
        }

        btnEditCreditos.setOnClickListener {
            hideAll()
            txtEditCulturales.visibility = View.VISIBLE
            txtReadCulturales.visibility = View.VISIBLE
            txtEditDeportivos.visibility = View.VISIBLE
            txtReadDeportivos.visibility = View.VISIBLE
            btnSaveChanges.visibility = View.VISIBLE
            actualView = "CREDITOS"
        }

        btnNewPassword.setOnClickListener {
            hideAll()
            txtEditPassword.visibility = View.VISIBLE
            txtReadPassword.visibility = View.VISIBLE
            btnSaveChanges.visibility = View.VISIBLE
            actualView = "PASS"
        }

        btnSaveChanges.setOnClickListener{

            val retrieveLogin = dbHelper.getLoginByUsername(username.toString())

            if (actualView=="CREDITOS"){

                var credDeportivos = 0
                var credCulturales = 0

                if (txtEditDeportivos.text.toString() != ""){
                    credDeportivos = txtEditDeportivos.text.toString().toInt()
                }

                if (txtEditCulturales.text.toString() != ""){
                    credCulturales = txtEditCulturales.text.toString().toInt()
                }

                val loginModel = LoginModel(
                    retrieveLogin?.id.toString().toInt(),
                    retrieveLogin?.username.toString(),
                    retrieveLogin?.password.toString(),
                    retrieveLogin?.name.toString(),
                    retrieveLogin?.lastname.toString(),
                    retrieveLogin?.major.toString(),
                    credDeportivos,
                    credCulturales
                )

                dbHelper.updateLogin(loginModel)
                Toast.makeText(this@EditActivity,"Los datos se guardaron exitosamente", Toast.LENGTH_SHORT).show()
                finish()

            } else if (actualView=="PASS") {
                val loginModel = LoginModel(
                    retrieveLogin?.id.toString().toInt(),
                    retrieveLogin?.username.toString(),
                    txtEditPassword.text.toString(),
                    retrieveLogin?.name.toString(),
                    retrieveLogin?.lastname.toString(),
                    retrieveLogin?.major.toString(),
                    retrieveLogin?.deportivos.toString().toInt(),
                    retrieveLogin?.culturales.toString().toInt(),
                )
                if(retrieveLogin?.password.toString() != txtEditPassword.text.toString()){
                    dbHelper.updateLogin(loginModel)
                    Toast.makeText(this@EditActivity,"Los datos se guardaron exitosamente", Toast.LENGTH_SHORT).show()
                    finish()
                }

            }
            else   {
                Toast.makeText(this@EditActivity, "Error: Ingrese una contraseña diferente", Toast.LENGTH_SHORT).show()
            }
        }

        hideAll()

    }
}