package com.fib.db

import android.content.Context
import com.fib.db.ConstantsDB.TABLE_NAME

import org.jetbrains.anko.db.MapRowParser
import org.jetbrains.anko.db.*
import timber.log.Timber

class ItemMercadoRepository(val context: Context) {

    fun findAll() : ArrayList<ItemMercado> = context.database.use {
        val itemsMercado = ArrayList<ItemMercado>()

        select(TABLE_NAME, "id","nome")
            .parseList(object: MapRowParser<List<ItemMercado>> {
                override fun parseRow(columns: Map<String, Any?>): List<ItemMercado> {
                    val id = columns.getValue("id")
                    val nome = columns.getValue("nome")

                    val itemMercado = ItemMercado(
                        id.toString().toLong(),
                        nome?.toString())

                    itemsMercado.add(itemMercado)

                    return itemsMercado
                }
            })

        itemsMercado
    }

    fun create(itemMercado: ItemMercado) = context.database.use {
        insert(TABLE_NAME,"nome" to itemMercado.nome)
    }

    fun update(itemMercado: ItemMercado) = context.database.use {
        val updateResult = update(TABLE_NAME,
            "nome" to itemMercado.nome)
            .whereArgs("id = {id}","id" to itemMercado.id).exec()

        Timber.d("Update result code is $updateResult")
    }
}