package com.example.myimcv3

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myimcv3.databinding.FragmentHistoricoBinding
import com.google.android.material.snackbar.Snackbar
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader


/**
 * A simple [Fragment] subclass.
 * Use the [HistoricoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HistoricoFragment : Fragment() {
    private val ARG_OBJECT = "object"
    private lateinit var binding: FragmentHistoricoBinding
    private val myAdapter: RecyclerAdapter = RecyclerAdapter()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHistoricoBinding.inflate(inflater, container, false)
        return binding.root

    }
    /*
        Se usa este método para que cada vez que se accede a la tab de histórico se carguen los datos
    */
    override fun onResume() {
        super.onResume()
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        Log.d("prueba", "setUpRecyclerView")
        binding.rvDatos.setHasFixedSize(true)
        binding.rvDatos.adapter = myAdapter
        binding.rvDatos.layoutManager = LinearLayoutManager(activity)

        context?.let { myAdapter.RecyclerAdapter(readFile(), it) }
    }

    /*
    Función que lee el fichero y crea una lista con todos los datos
     */
    fun readFile(): MutableList<DatoHistorico> {
        // Se comprueba si existe el fichero.
        val datos: MutableList<DatoHistorico> = arrayListOf()

        try {
            val entrada = InputStreamReader(
                getActivity()?.openFileInput(getString(R.string.filename))
            )
            val br = BufferedReader(entrada)
            // para cada linea crea un objeto y los guarda en la lista
            br.forEachLine {
                var linea = it.split(";")
                datos.add(
                    DatoHistorico(
                        linea[0].toString(),
                        linea[1].toString(),
                        linea[2].toDouble(),
                        linea[3].toString(),
                        linea[4].toString().toDouble(),
                        linea[5].toString().toDouble()

                    )
                )
            }

        } catch (e: IOException) {

            getActivity()?.let {
                e.message?.let { it1 ->
                    Snackbar.make(
                        it.findViewById(android.R.id.content),
                        it1,
                        Snackbar.LENGTH_LONG
                    ).show()
                }
            }
        }
        return datos
    }
}