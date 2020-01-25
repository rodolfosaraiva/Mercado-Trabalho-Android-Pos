package com.fib.db

import java.io.Serializable

data class ItemMercado(
    var id: Long = 0,
    var nome: String? = null
) : Serializable {
    override fun toString(): String {
        return nome.toString()
    }
}
