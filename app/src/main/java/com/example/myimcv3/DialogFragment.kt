package com.example.myimcv3


import android.app.Activity
import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.google.android.material.snackbar.Snackbar
import java.io.IOException
import java.io.OutputStreamWriter


class MyDialogFragment(dato: DatoHistorico) : DialogFragment() {

    lateinit var dato: DatoHistorico

    init {
        this.dato = dato
    }
    // Se crea la estructura del diálogo.
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)

            builder.setMessage(R.string.quieres_guardar_los_datos)
                .setPositiveButton(
                    android.R.string.yes
                ) { dialog, which ->
                    // Guarda los datos en el fichero.
                    writeFile(dato, false)

                    Snackbar.make(
                        it.findViewById(android.R.id.content),
                        R.string.datos_guardados,
                        Snackbar.LENGTH_SHORT
                    ).show()

                }
                .setNegativeButton(
                    android.R.string.no
                ) { dialog, which ->
                    // Si se pulsa no, solo muestra un aviso
                    Snackbar.make(
                        it.findViewById(android.R.id.content),
                        R.string.datos_no_guardados,
                        Snackbar.LENGTH_SHORT
                    ).show()
                }

            builder.create()
        } ?: throw IllegalStateException("La Activity no puede ser nula")
    }

    fun writeFile(datos: DatoHistorico, tipo: Boolean) {
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
            // Se escribe el dato en el fichero.

            salida.write(datos.toString() + '\n')

            // Se confirma la escritura.
            salida.flush()
            salida.close()
            getActivity()?.let {
                Snackbar.make(
                    it.findViewById(android.R.id.content),
                    getString(R.string.msg_correct),
                    Snackbar.LENGTH_LONG
                ).show()
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
    }
}