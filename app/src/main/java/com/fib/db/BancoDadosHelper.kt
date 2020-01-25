package com.fib.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*
import com.fib.db.ConstantsDB.DB_NAME
import com.fib.db.ConstantsDB.TABLE_NAME

class BancoDadosHelper(context: Context) :
    ManagedSQLiteOpenHelper(ctx = context,
        name = DB_NAME, version = 1) {

    private val scriptSQLCreate = arrayOf(
        "INSERT INTO $TABLE_NAME VALUES(1,'Arroz')",
        "INSERT INTO $TABLE_NAME VALUES(2,'Feijão');",
        "INSERT INTO $TABLE_NAME VALUES(3,'Refrigerante');",
        "INSERT INTO $TABLE_NAME VALUES(4,'Banana');")

    //singleton da classe
    companion object {
        private var instance: BancoDadosHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): BancoDadosHelper {
            if (instance == null) {
                instance = BancoDadosHelper(ctx.getApplicationContext())
            }
            return instance!!
        }
    }



    override fun onCreate(db: SQLiteDatabase) {
        // Criação de tabelas
        db.createTable(TABLE_NAME, true,
            "id" to INTEGER + PRIMARY_KEY + UNIQUE,
            "nome" to TEXT
        )

// insere dados iniciais na tabela
        scriptSQLCreate.forEach {sql ->
            db.execSQL(sql)
        }

    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable(TABLE_NAME, true)
        onCreate(db)
    }
}

val Context.database: BancoDadosHelper get() = BancoDadosHelper.getInstance(getApplicationContext())
