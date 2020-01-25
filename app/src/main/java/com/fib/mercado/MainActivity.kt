package com.fib.mercado

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.fib.db.ItemMercado
import com.fib.db.ItemMercadoRepository
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var itemMercados:ArrayList<ItemMercado>? = null
    private var itemMercadoSelecionado:ItemMercado? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val myToolbar = toolbar
        myToolbar.setTitleTextColor(Color.WHITE)
        setSupportActionBar(myToolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.novo -> {
                val intent = Intent(this, ItemMercadoActivity::class.java)
                startActivity(intent)
                return false
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }


    override fun onResume() {
        super.onResume()
        itemMercados = ItemMercadoRepository(this).findAll()
        val adapter= ArrayAdapter(this, android.R.layout.simple_list_item_1, itemMercados)
        lista?.adapter = adapter
        adapter.notifyDataSetChanged()


        lista.setOnItemClickListener { _, _, position, id ->
            val intent = Intent(this@MainActivity, ItemMercadoActivity::class.java)
            intent.putExtra("itemMercado", itemMercados?.get(position))
            startActivity(intent)
        }

        lista.onItemLongClickListener = AdapterView.OnItemLongClickListener { adapter, view, posicao, id ->
            itemMercadoSelecionado = itemMercados?.get(posicao)
            false
        }
    }


}
