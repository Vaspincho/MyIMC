package com.example.myimcv3

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myimcv3.databinding.FragmentHistoricoBinding
import com.example.myimcv3.databinding.FragmentImcBinding
import com.google.android.material.snackbar.Snackbar
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

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

        //return inflater.inflate(R.layout.fragment_historico, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("prueba", "onViewCreated")
        setUpRecyclerView()

    }


    private fun setUpRecyclerView() {
        Log.d("prueba", "setUpRecyclerView")
        binding.rvDatos.setHasFixedSize(true)
        binding.rvDatos.adapter = myAdapter
        binding.rvDatos.layoutManager = LinearLayoutManager(activity)

        context?.let { myAdapter.RecyclerAdapter(readFile(), it) }
    }

    fun readFile(): MutableList<DatoHistorico> {
        // Se comprueba si existe el fichero.
        Log.d("prueba", "readFile")
        val datos: MutableList<DatoHistorico> = arrayListOf()

        try {
            Log.d("prueba", "Try")
            val entrada = InputStreamReader(
                getActivity()?.openFileInput(getString(R.string.filename))
            )
            val br = BufferedReader(entrada)
            // para cada linea crea un objeto y los guarda en la lista
            br.forEachLine {
                Log.d("prueba", "for")
                var linea = it.split(";")
                Log.d("TAG", linea.toString())
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