package com.example.tdmpa_3p_pr01

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION){
    companion object{
        private const val DATABASE_NAME = "LoginDB"
        private const val DATABASE_VERSION = 1
        private const val  TABLE_NAME = "LoginTable"
        private const val KEY_ID = "_id"
        private const val KEY_USERNAME = "username"
        private const val KEY_PASSWORD = "password"
        private const val KEY_NAME = "name"
        private const val KEY_LASTNAME = "lastname"
        private const val KEY_MAJOR = "major"
        private const val KEY_DEPORTIVOS = "deportivos"
        private const val KEY_CULTURALES = "culturales"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = ("CREATE TABLE $TABLE_NAME ($KEY_ID INTEGER PRIMARY KEY, $KEY_USERNAME TEXT, $KEY_PASSWORD TEXT, $KEY_NAME TEXT, $KEY_LASTNAME TEXT, $KEY_MAJOR TEXT, $KEY_DEPORTIVOS INTEGER, $KEY_CULTURALES INTEGER);")
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun addLogin(loginModel: LoginModel){
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(KEY_USERNAME, loginModel.username)
        values.put(KEY_PASSWORD, loginModel.password)
        values.put(KEY_NAME, loginModel.name)
        values.put(KEY_LASTNAME, loginModel.lastname)
        values.put(KEY_MAJOR, loginModel.major)
        values.put(KEY_DEPORTIVOS, loginModel.deportivos)
        values.put(KEY_CULTURALES, loginModel.culturales)
        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    @SuppressLint("Range")
    fun getLoginByUsername(username: String):LoginModel?{
        val db = this.readableDatabase
        val cursor = db.query(
            TABLE_NAME,
            arrayOf(KEY_ID, KEY_USERNAME, KEY_PASSWORD, KEY_NAME, KEY_LASTNAME, KEY_MAJOR, KEY_DEPORTIVOS, KEY_CULTURALES),
            "$KEY_USERNAME=?",
            arrayOf(username),
            null,
            null,
            null
        )

        return if(cursor.moveToFirst()){
            val id = cursor.getInt(cursor.getColumnIndex(KEY_ID))
            val password = cursor.getString(cursor.getColumnIndex(KEY_PASSWORD))
            val name = cursor.getString(cursor.getColumnIndex(KEY_NAME))
            val lastname = cursor.getString(cursor.getColumnIndex(KEY_LASTNAME))
            val major = cursor.getString(cursor.getColumnIndex(KEY_MAJOR))
            val deportivos = cursor.getInt(cursor.getColumnIndex(KEY_DEPORTIVOS))
            val culturales = cursor.getInt(cursor.getColumnIndex(KEY_CULTURALES))
            LoginModel(id, username, password, name, lastname, major, deportivos, culturales)
        }
        else{
            null
        }
    }

    fun updateLogin(loginModel: LoginModel){
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(KEY_USERNAME,loginModel.username)
        values.put(KEY_PASSWORD, loginModel.password)
        values.put(KEY_NAME, loginModel.name)
        values.put(KEY_LASTNAME, loginModel.lastname)
        values.put(KEY_MAJOR, loginModel.major)
        values.put(KEY_DEPORTIVOS, loginModel.deportivos)
        values.put(KEY_CULTURALES, loginModel.culturales)
        db.update(TABLE_NAME,values,"$KEY_ID=?", arrayOf(loginModel.id.toString()))
        db.close()
    }

    fun updateCreditos(loginModel: LoginModel){
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(KEY_USERNAME,loginModel.username)
        values.put(KEY_PASSWORD, loginModel.password)
        values.put(KEY_NAME, loginModel.name)
        values.put(KEY_LASTNAME, loginModel.lastname)
        values.put(KEY_MAJOR, loginModel.major)
        values.put(KEY_DEPORTIVOS, loginModel.deportivos)
        values.put(KEY_CULTURALES, loginModel.culturales)
        db.update(TABLE_NAME,values,"$KEY_ID=?", arrayOf(loginModel.id.toString()))
        db.close()
    }

}

data class LoginModel(val id:Int, val username:String, val password:String, val name:String, val lastname:String, val major:String, val deportivos:Int, val culturales:Int)