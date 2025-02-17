package com.interrapidisimo.android.auth.data.repository.local

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.interrapidisimo.android.auth.data.provider.local.SQLiteHelper
import com.interrapidisimo.android.auth.data.provider.remote.model.AuthenticateCustom
import javax.inject.Inject

class AuthenticateLocalRepositoryImpl @Inject constructor(
    private val dbHelper: SQLiteHelper
) : AuthenticateLocalRepository {

    override fun insertAuthenticate(auth: AuthenticateCustom) {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("identificacion", auth.identificacion)
            put("usuario", auth.usuario)
            put("nombre", auth.nombre)
        }
        db.insertWithOnConflict("authenticate_custom", null, values, SQLiteDatabase.CONFLICT_REPLACE)
        db.close()
    }

    override fun getAuthenticateById(identificacion: String): AuthenticateCustom? {
        val db = dbHelper.readableDatabase
        val cursor: Cursor = db.rawQuery(
            "SELECT * FROM authenticate_custom WHERE identificacion = ?", arrayOf(identificacion)
        )

        var auth: AuthenticateCustom? = null
        if (cursor.moveToFirst()) {
            auth = AuthenticateCustom(
                cursor.getString(cursor.getColumnIndexOrThrow("identificacion")),
                cursor.getString(cursor.getColumnIndexOrThrow("usuario")),
                cursor.getString(cursor.getColumnIndexOrThrow("nombre"))
            )
        }
        cursor.close()
        db.close()
        return auth
    }

    override fun deleteAuthenticate(identificacion: String) {
        val db = dbHelper.writableDatabase
        db.delete("authenticate_custom", "identificacion = ?", arrayOf(identificacion))
        db.close()
    }
}
