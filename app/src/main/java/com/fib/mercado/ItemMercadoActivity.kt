package com.fib.mercado
import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.DatePicker
import kotlinx.android.synthetic.main.activity_mercado.*
import java.text.SimpleDateFormat
import java.util.*
import androidx.appcompat.app.AppCompatActivity
import com.fib.db.ItemMercado
import com.fib.db.ItemMercadoRepository

class ItemMercadoActivity : AppCompatActivity() {

    private var cal = Calendar.getInstance()
    private var datanascimento: Button? = null
    private var itemMercado: ItemMercado? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mercado)

        val myChildToolbar = toolbar_child
        setSupportActionBar(myChildToolbar)
        // Get a support ActionBar corresponding to this toolbar
        val ab = supportActionBar
        // Enable the Up button
        ab!!.setDisplayHomeAsUpEnabled(true)

        btnCadastro?.setOnClickListener {
            itemMercado?.nome = txtNome?.text.toString()

            if(itemMercado?.id == 0.toLong()){
                ItemMercadoRepository(this).create(itemMercado!!)
            }else{
                ItemMercadoRepository(this).update(itemMercado!!)
            }
            finish()
        }

    }

    override fun onResume() {
        super.onResume()
        val intent = intent
        if(intent != null){
            if(intent.getSerializableExtra("itemMercado") != null){
                itemMercado = intent.getSerializableExtra("itemMercado") as ItemMercado
                txtNome?.setText(itemMercado?.nome)
            }else{
                itemMercado = ItemMercado()
            }
        }
    }

}
