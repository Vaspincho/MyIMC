package com.example.myimcv3

class DatoHistorico(val fecha: String, val sexo: String, val imc: Double, val resultado: String, val peso: Double, val altura: Double)  {

    var fechaDat : String
    var sexoDat : String
    var imcDat : Double
    var resultadoDat : String
    var pesoDat: Double
    var alturaDat: Double

    init {
        this.fechaDat = fecha
        this.sexoDat = sexo
        this.imcDat = imc
        this.resultadoDat = resultado
        this.pesoDat = peso
        this.alturaDat = altura
    }
}
