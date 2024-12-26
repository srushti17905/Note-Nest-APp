package com.example.note_app

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class Database(context: Context) : SQLiteOpenHelper(context, "mydb.db", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {

        val table =
            "CREATE TABLE user (ID INTEGER Primary key autoincrement , title text , detail text)"

        db!!.execSQL(table)

    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

    fun insertData(title: String, detail: String) {

        val insert = "INSERT INTO user (title , detail) VALUES ('$title' , '$detail')"

        try {
            writableDatabase.execSQL(insert)
        } catch (e: Exception) {

            Log.d("===data====", "insertData: ${e.localizedMessage}")

        }
    }

    fun selectData(title: String, detail: String): Cursor {

        val select = "SELECT * FROMM user WHERE title = '$title' AND detail = '$detail'"
        val cursur = readableDatabase.rawQuery(select, null)
        return cursur

    }

    fun updateData(id: Int, newTitle: String, newDetail: String) {

        val update = "UPDATE user SET title = '$newTitle' , detail = '$newDetail' WHERE ID = '$id'"
        writableDatabase.execSQL(update)

    }

    fun delete(id: Int) {

        val deleteNote = "DELETE FROM user WHERE id = '$id'"
        writableDatabase.execSQL(deleteNote)

    }

}