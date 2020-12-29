package com.example.myimcv3

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myimcv3.databinding.ItemDatoListBinding


class RecyclerAdapter : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    private var datos : MutableList<DatoHistorico> = ArrayList()
    private lateinit var context : Context


    fun RecyclerAdapter  (datosList : MutableList<DatoHistorico>, contxt : Context){
        this.datos = datosList
        this.context = contxt
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater= LayoutInflater.from(parent.context)
        return ViewHolder(
            layoutInflater.inflate(
                R.layout.item_dato_list,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int){
        val item = datos.get(position)
        holder.bind(item, context)
    }

    override fun getItemCount(): Int {
       return datos.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){

        private val binding = ItemDatoListBinding.bind(view)

        fun bind(dato: DatoHistorico, contxt: Context){
            val array = contxt.resources.getStringArray(R.array.meses)
            binding.tvFechaMes.text = array[dato.fecha.split("-")[1].toInt()-1]
            binding.tvFechaDia.text = dato.fecha.split("-")[0]
            binding.tvFechaYear.text = dato.fecha.split("-")[2]
            binding.tvRes.text = dato.resultado
            binding.tvResIMC.text = dato.imc.toString()
            binding.tvSexo.text = dato.sexo
            binding.tvPeso.text = dato.peso.toString()
            binding.tvAltura.text = dato.altura.toString()


        }

    }

}

