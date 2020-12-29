package com.example.myimcv3

import com.google.android.material.snackbar.Snackbar
import java.util.*

class Funciones {
/*
    private fun calcularIMC() {
        val fecha = Calendar.getInstance()
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
                    Calendar.YEAR)}"
                linea += ";Hombre;" + String.format("%.2f", imc) + ";${binding.lbResultado.text}"

                writeFile(linea, false )
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
                    Calendar.YEAR)}"
                linea += ";Mujer;" + String.format("%.2f", imc) + ";${binding.lbResultado.text}"

                writeFile(linea, false )
            } else {
                // En caso de que no se seleccione sexo, se muestra un aviso
                Toast.makeText(
                    this,
                    getString(R.string.seleccionar_sexo),
                    Toast.LENGTH_LONG
                ).show()
            }
            // En caso de que no se se pongan datos de peso o altura, se muestra un aviso
        } else {
            Toast.makeText(
                this,
                getString(R.string.campos_sexo_altura),
                Toast.LENGTH_LONG
            ).show()
        }

    }

    private fun writeFile(linea: String, b: Boolean) {
        try {

            val salida: OutputStreamWriter
            if (tipo) {
                // Si el fichero no existe se crea
                salida = OutputStreamWriter(
                    openFileOutput(
                        getString(R.string.filename),
                        Activity.MODE_PRIVATE
                    )
                )
            } else {
                //si existe se añade la información.
                salida = OutputStreamWriter(
                    openFileOutput(
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

            Toast.makeText(
                this,
                getString(R.string.msg_correct),
                Toast.LENGTH_SHORT
            ).show()
        } catch (e: IOException) {
            Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
        }
    }
*/

}