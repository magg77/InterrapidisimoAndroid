package com.interrapidisimo.android.auth.data.provider.local

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SQLiteHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        val createTableQuery = """
            CREATE TABLE authenticate_custom (
                identificacion TEXT PRIMARY KEY,
                usuario TEXT NOT NULL,
                nombre TEXT NOT NULL
            );
        """.trimIndent()
        db.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS authenticate_custom")
        onCreate(db)
    }

    // Abrir la base de datos para App Inspection
    fun getDatabase(): SQLiteDatabase {
        return this.writableDatabase
    }

    companion object {
        private const val DATABASE_NAME = "interrapidisimo_go_bd"
        private const val DATABASE_VERSION = 2
    }
}