package com.example.myimcv3

import android.app.Activity
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.example.myimcv3.databinding.FragmentImcBinding
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.snackbar.Snackbar.make
import java.io.IOException
import java.io.OutputStreamWriter
import java.util.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Imc.newInstance] factory method to
 * create an instance of this fragment.
 */

class ImcFragment : Fragment(){

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var imcView: View? = null
    private lateinit var binding: FragmentImcBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
    Log.d("pruebas", "1")

        imcView =  inflater.inflate(R.layout.fragment_imc, container, false)

    binding = FragmentImcBinding.inflate(inflater, container, false)
    return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btCalcular.setOnClickListener({ calcularIMC() })


    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment RootTab1Fragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ImcFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }


    private fun calcularIMC() {

        val fecha = Calendar.getInstance()
        val linea: String? = null
        // Se comprueba si hay datos de peso y altura
        if (binding.tbPeso.text.isNotEmpty() && binding.tbAltura.text.isNotEmpty()){
            val altura: Double =   (binding.tbAltura.text.toString()).toDouble()/100
            var peso: Double = (binding.tbPeso.text.toString()).toDouble()
            // Calculo del IMC según la fórmula
            val imc = peso / (altura * altura)
            // Formateo de la cadena de texto a dos decimales
            binding.twCalculo.text = String.format("%.2f", imc)
            // Si se selecciona "hombre"
            if (binding.rbHombre.isChecked) {
                if (imc < 18.5){
                    binding.lbResultado.text = getString(R.string.peso_inferior)
                } else if (imc <= 24.9){
                    binding.lbResultado.text = getString(R.string.normal)
                }else if (imc <= 29.9) {
                    binding.lbResultado.text = getString(R.string.sobrepeso)
                } else {
                    binding.lbResultado.text = getString(R.string.obesidad)
                }

                var linea = "${fecha.get(Calendar.DAY_OF_MONTH) }" + "-${fecha.get(Calendar.MONTH) + 1}" + "-${fecha.get(
                    Calendar.YEAR
                )}"
                linea += ";Hombre;" + String.format("%.2f", imc) + ";${binding.lbResultado.text}" +";${binding.tbAltura.text.toString()}" + ";${binding.tbPeso.text.toString()}"



                //writeFile(linea, false)
                // Si se selecciona "mujer"
            } else if (binding.rbMujer.isChecked){
                if (imc < 18.5){
                    binding.lbResultado.text = getString(R.string.peso_inferior)
                } else if (imc <= 23.9){
                    binding.lbResultado.text = getString(R.string.normal)
                }else if (imc <= 28.9) {
                    binding.lbResultado.text = getString(R.string.sobrepeso)
                } else {
                    binding.lbResultado.text = getString(R.string.obesidad)
                }
                var linea = "${fecha.get(Calendar.DAY_OF_MONTH) }" + "-${fecha.get(Calendar.MONTH) + 1}" + "-${fecha.get(
                    Calendar.YEAR
                )}"
                linea += ";Mujer;" + String.format("%.2f", imc) + ";${binding.lbResultado.text}" +";${binding.tbAltura.text.toString()}" + ";${binding.tbPeso.text.toString()}"

                //writeFile(linea, false)
            } else {
                // En caso de que no se seleccione sexo, se muestra un aviso
                getActivity()?.let {
                    make(
                        it.findViewById(android.R.id.content),
                        getString(R.string.seleccionar_sexo),
                        Snackbar.LENGTH_LONG
                    ).show()
                }
            }

            AlertDialog.Builder(context!!)
                .setTitle(R.string.aviso)
                .setMessage(R.string.quieres_guardar_los_datos) // Specifying a listener allows you to take an action before dismissing the dialog.
                // The dialog is automatically dismissed when a dialog button is clicked.
                .setPositiveButton(
                    android.R.string.yes
                ) { dialog, which ->
                   if (linea != null) {
                       writeFile(linea, false)
                   }
                    getActivity()?.let {
                        Snackbar.make(
                            it.findViewById(android.R.id.content),
                            getString(R.string.datos_guardados),
                            Snackbar.LENGTH_LONG
                        ).show()
                    }

                } // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton(android.R.string.no) { dialog, which ->
                    getActivity()?.let {
                        Snackbar.make(
                            it.findViewById(android.R.id.content),
                            getString(R.string.datos_no_guardados),
                            Snackbar.LENGTH_LONG
                        ).show()
                    }
                }
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show()


            // En caso de que no se se pongan datos de peso o altura, se muestra un aviso
        } else {
            getActivity()?.let {
                Snackbar.make(
                    it.findViewById(android.R.id.content),
                    getString(R.string.campos_sexo_altura),
                    Snackbar.LENGTH_LONG
                ).show()
            }
        }

    }

    fun writeFile(datos: String, tipo: Boolean) {
        try {

            val salida: OutputStreamWriter
            if (tipo) {
                // Si el fichero no existe se crea
                salida = OutputStreamWriter(
                    getActivity()?.openFileOutput(
                        getString(R.string.filename),
                        Activity.MODE_PRIVATE
                    )
                )
            } else {
                //si existe se añade la información.
                salida = OutputStreamWriter(
                    getActivity()?.openFileOutput(
                        getString(R.string.filename),
                        Activity.MODE_APPEND
                    )
                )
            }
            // Se escribe en el fichero línea a línea.

            salida.write(datos + '\n')
            2
            // Se confirma la escritura.
            salida.flush()
            salida.close()
            getActivity()?.let {
                make(
                    it.findViewById(android.R.id.content),
                    getString(R.string.msg_correct),
                    Snackbar.LENGTH_LONG
                ).show()
            }
        } catch (e: IOException) {

            getActivity()?.let {
                e.message?.let { it1 ->
                    make(
                        it.findViewById(android.R.id.content),
                        it1,
                        Snackbar.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

     class SaveDialogFragment  : DialogFragment() {

        override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
            return activity?.let {
                // Use the Builder class for convenient dialog construction
                val builder = AlertDialog.Builder(it)
                builder.setMessage(R.string.quieres_guardar_los_datos)
                    .setPositiveButton(R.string.label_ok,
                        DialogInterface.OnClickListener { dialog, id ->
                            // FIRE ZE MISSILES!
                        })
                    .setNegativeButton(R.string.label_cancel,
                        DialogInterface.OnClickListener { dialog, id ->
                            // User cancelled the dialog
                        })
                // Create the AlertDialog object and return it
                builder.create()
            } ?: throw IllegalStateException("Activity cannot be null")
        }
    }

}