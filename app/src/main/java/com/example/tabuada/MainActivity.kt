package com.example.tabuada

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.RatingBar
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    lateinit var Number1:TextView
    lateinit var Number2:TextView
    lateinit var NumDigitado: EditText
    lateinit var ratingBar:RatingBar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Number1 = findViewById(R.id.Num1)
        Number2 = findViewById(R.id.Num2)

        Number1.text = Random.nextInt(1,9).toString()
        Number2.text = Random.nextInt(1,9).toString()
    }

    fun iniciar_OnClick(view: View){
        Number1 = findViewById(R.id.Num1)
        Number2 = findViewById(R.id.Num2)
        NumDigitado = findViewById(R.id.input)

        var resultado = Number1.text.toString().toInt() * Number2.text.toString().toInt()

        if(resultado == NumDigitado.text.toString().toInt()){
            //acertou
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Resultado")
            builder.setMessage("Correto!")
            builder.setNeutralButton("Proxima ->") { dialog, which -> dialog.cancel() }
            builder.show()
            Number1.text = Random.nextInt(1,9).toString()
            Number2.text = Random.nextInt(1,9).toString()
            atualizaRating(1)
        }else{
            //errou
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Resultado")
            builder.setMessage("Não foi dessa vez...")
            builder.setNeutralButton("Tente novamente ->") { dialog, which -> dialog.cancel() }
            builder.show()
            atualizaRating(0)

        }
        NumDigitado.text.clear()
    }
    fun atualizaRating(acertou:Int){
        ratingBar = findViewById(R.id.ratingBar)
        if(acertou==1){
            if(ratingBar.rating<5){
                ratingBar.rating = ratingBar.rating + 1
            }else{
                val builder = AlertDialog.Builder(this)
                builder.setTitle("Você ganhou!!!")
                builder.setMessage("Tentar Novamente")
                builder.setNeutralButton("Vamos lá ->") { dialog, which -> dialog.cancel() }
                builder.show()
                ratingBar.rating = 0F

            }
        }else{
            if(ratingBar.rating>0){
                ratingBar.rating = ratingBar.rating -1
            }

        }

    }
}