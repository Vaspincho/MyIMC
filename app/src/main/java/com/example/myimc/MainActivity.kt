package com.example.myimc

import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import android.widget.Toast
import com.example.myimc.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btCalcular.setOnClickListener{calcularIMC()}
    }

    private fun calcularIMC(){
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
                    binding.lbResultado.text = "Peso inferior al normal"
                } else if (imc <= 24.9){
                    binding.lbResultado.text = "Normal"
                }else if (imc <= 29.9) {
                    binding.lbResultado.text = "Sobrepeso"
                } else {
                        binding.lbResultado.text = "Obesidad"
                }

            // Si se selecciona "mujer"
            } else if (binding.rbMujer.isChecked){
                if (imc < 18.5){
                    binding.lbResultado.text = "Peso inferior al normal"
                } else if (imc <= 23.9){
                    binding.lbResultado.text = "Normal"
                }else if (imc <= 28.9) {
                    binding.lbResultado.text = "Sobrepeso"
                } else {
                    binding.lbResultado.text = "Obesidad"
                }
            } else {
                // En caso de que no se seleccione sexo, se muestra un aviso
                Toast.makeText(
                        this,
                        "Tiene que seleccionar el sexo",
                        Toast.LENGTH_LONG
                ).show()
            }
            // En caso de que no se se pongan datos de peso o altura, se muestra un aviso
        } else {
            Toast.makeText(
                    this,
                    "Tienes que rellenar los campos de peso y altura",
                    Toast.LENGTH_LONG
            ).show()
        }
    }
}