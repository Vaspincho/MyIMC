package com.example.myimcv3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myimcv3.databinding.FragmentImcBinding
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.snackbar.Snackbar.make
import java.util.*


/**
 * A simple [Fragment] subclass.
 * Use the [Imc.newInstance] factory method to
 * create an instance of this fragment.
 */

class ImcFragment : Fragment() {

    private var imcView: View? = null
    private lateinit var binding: FragmentImcBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        imcView = inflater.inflate(R.layout.fragment_imc, container, false)

        binding = FragmentImcBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btCalcular.setOnClickListener({ calcularIMC() })

    }

    // Realiza la lectura de los datos y las operaciones de cálculo del IMC
    fun calcularIMC() {
        val fecha = Calendar.getInstance()

        // Se comprueba si hay datos de peso y altura
        if (binding.tbPeso.text.isNotEmpty() && binding.tbAltura.text.isNotEmpty()) {
            val altura: Double = (binding.tbAltura.text.toString()).toDouble()
            var peso: Double = (binding.tbPeso.text.toString()).toDouble()
            // Cálculo del IMC según la fórmula
            val imc = peso / ((altura/100) * (altura/100))
            // Formateo de la cadena de texto a dos decimales
            binding.twCalculo.text = String.format("%.2f", imc)
            // Si se selecciona "hombre"
            if (binding.rbHombre.isChecked) {
                if (imc < 18.5) {
                    binding.lbResultado.text = getString(R.string.peso_inferior)
                } else if (imc <= 24.9) {
                    binding.lbResultado.text = getString(R.string.normal)
                } else if (imc <= 29.9) {
                    binding.lbResultado.text = getString(R.string.sobrepeso)
                } else {
                    binding.lbResultado.text = getString(R.string.obesidad)
                }

                var midato = DatoHistorico("${fecha.get(Calendar.DAY_OF_MONTH)}"+"-${fecha.get(Calendar.MONTH)+1}"+"-${fecha.get(Calendar.YEAR)}",
                    "hombre", imc, binding.lbResultado.text.toString(), peso,altura)

                    val myDialogFragment = MyDialogFragment(midato)
                    getFragmentManager()?.let {
                        myDialogFragment.show(it, "")
                }

                // Si se selecciona "mujer"
            } else if (binding.rbMujer.isChecked) {
                if (imc < 18.5) {
                    binding.lbResultado.text = getString(R.string.peso_inferior)
                } else if (imc <= 23.9) {
                    binding.lbResultado.text = getString(R.string.normal)
                } else if (imc <= 28.9) {
                    binding.lbResultado.text = getString(R.string.sobrepeso)
                } else {
                    binding.lbResultado.text = getString(R.string.obesidad)
                }

                var midato = DatoHistorico("${fecha.get(Calendar.DAY_OF_MONTH)}"+"-${fecha.get(Calendar.MONTH)+1}"+"-${fecha.get(Calendar.YEAR)}",
                    "mujer", imc, binding.lbResultado.text.toString(), peso,altura)

                    val myDialogFragment = MyDialogFragment(midato)
                    getFragmentManager()?.let {
                        myDialogFragment.show(it, "")

                }

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
}
