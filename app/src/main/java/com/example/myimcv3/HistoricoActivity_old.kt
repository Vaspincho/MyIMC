package com.example.myimcv3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myimcv3.databinding.ActivityHistoricoBinding
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

class HistoricoActivity_old : AppCompatActivity() {
    private lateinit var binding2: ActivityHistoricoBinding
    private val myAdapter : RecyclerAdapter = RecyclerAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding2 = ActivityHistoricoBinding.inflate(layoutInflater)
        setContentView(binding2.root)

        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {

        binding2.rvDatos.setHasFixedSize(true)
        binding2.rvDatos.adapter = myAdapter
        binding2.rvDatos.layoutManager = LinearLayoutManager(this)
        myAdapter.RecyclerAdapter(readFile(), this)

   }
    // Lectura del fichero con el histórico de datos
    fun readFile(): MutableList<DatoHistorico> {
        // Se comprueba si existe el fichero.

        val datos: MutableList<DatoHistorico> = arrayListOf()

        try {
            val entrada = InputStreamReader(
                openFileInput(getString(R.string.filename))
            )
            val br = BufferedReader(entrada)
            // para cada linea crea un objeto y los guarda en la lista
            br.forEachLine {

                var linea = it.split(";")
                Log.d("TAG", linea.toString())
                datos.add(
                    DatoHistorico(
                        linea[0].toString(),
                        linea[1].toString(),
                        linea[2].toDouble(),
                        linea[3].toString(),
                        linea[4].toDouble(),
                        linea[5].toDouble()

                    )
                )
            }

        } catch (e: IOException) {
            Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
        }

        return datos

    }}
