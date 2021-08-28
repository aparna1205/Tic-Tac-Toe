package com.aparna.tic_tac_toe

import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.SystemClock
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlin.random.Random


lateinit var txtchange:TextView
lateinit var btnRestart:Button
lateinit var btnMenu:Button
lateinit var btnExit:Button

var single:Boolean=false
var dual: Boolean= false
var activeplayer=1
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnRestart=findViewById(R.id.btnRestart)
        btnMenu=findViewById(R.id.btnMenu)
        btnExit=findViewById(R.id.btnExit)

        title="Lets play the game"
        txtchange = findViewById(R.id.txtchange)
        if(intent!=null){
            single=intent.getBooleanExtra("single",false)
            dual=intent.getBooleanExtra("double",false)
        }
        if(single==true){
            activeplayer=2
            autoplay()

        }


        btnRestart.setOnClickListener {
            val intent=Intent(this,this::class.java )
            intent.putExtra("single",single)
            intent.putExtra("double",dual)

            btnRestart.setBackgroundColor(Color.parseColor("#F9AA33"))
            startActivity(intent)

        }
        btnExit.setOnClickListener {
            btnRestart.setBackgroundColor(Color.parseColor("#F9AA33"))
            onBackPressed()
            finish()
        }
        btnMenu.setOnClickListener {
            val intentss=Intent(this,StartActivity::class.java )
            btnRestart.setBackgroundColor(Color.parseColor("#F9AA33"))
            startActivity(intentss)
            finish()
        }
    }

   fun buClick(view:View){
      val buttonSelected=view as Button
       var pos=0
       when(buttonSelected.id){
        R.id.txt1->pos=1
        R.id.txt2->pos=2
        R.id.txt3->pos=3
        R.id.txt4->pos=4
        R.id.txt5->pos=5
        R.id.txt6->pos=6
        R.id.txt7->pos=7
        R.id.txt8->pos=8
        R.id.txt9->pos=9
       }

       playgame(buttonSelected,pos)
    }

    var player1=ArrayList<Int>()
    var player2=ArrayList<Int>()
    fun playgame(selectedButton: Button,pos:Int){
        if(activeplayer==1){
         player1.add(pos)
            selectedButton.text="X"
            selectedButton.setBackgroundColor(Color.parseColor("#F9AA33"))
            activeplayer=2
            selectedButton.isEnabled=false
            checkgame()
            if(single==true){
                autoplay()
            }
        }
        else if(dual==true){
            player2.add(pos)
            selectedButton.text="O"
            selectedButton.setTextColor(Color.parseColor("#FFFFFF"))
            selectedButton.setBackgroundColor(Color.parseColor("#232F34"))
            selectedButton.isEnabled=false
            activeplayer=1
        }
        else if(single==true){
            selectedButton.text="O"
            selectedButton.setTextColor(Color.parseColor("#FFFFFF"))
            selectedButton.setBackgroundColor(Color.parseColor("#232F34"))
            selectedButton.isEnabled=false
            //autoplay()
        }
        checkgame()
        selectedButton.isEnabled=false
    }


    fun autoplay(){

        var presentpos= Random.nextInt(1,10)
        while(presentpos in player1 || presentpos in player2){
            presentpos= Random.nextInt(1,10)
        }

        player2.add(presentpos)
        lateinit var button:Button
        when(presentpos){
            1->button=findViewById(R.id.txt1)
            2->button=findViewById(R.id.txt2)
            3->button=findViewById(R.id.txt3)
            4->button=findViewById(R.id.txt4)
            5->button=findViewById(R.id.txt5)
            6->button=findViewById(R.id.txt6)
            7->button=findViewById(R.id.txt7)
            8->button=findViewById(R.id.txt8)
            9->button=findViewById(R.id.txt9)
        }
        activeplayer=2
        buClick(button)
        activeplayer=1
    }


    fun checkgame(){
        val totalarray= arrayListOf<Int>()
        totalarray.addAll(player1+player2)
        val completearray= arrayListOf<Int>(1,2,3,4,5,6,7,8,9)

        var gamewinner=-1
        //rows check
        if((player1.contains(1)&&player1.contains(2)&&player1.contains(3))||(player1.contains(4)&&player1.contains(5)&&player1.contains(6))||(player1.contains(7)&&player1.contains(8)&&player1.contains(9))){
            gamewinner=1
        }
        else if((player2.contains(1)&&player2.contains(2)&&player2.contains(3))||(player2.contains(4)&&player2.contains(5)&&player2.contains(6))||(player2.contains(7)&&player2.contains(8)&&player2.contains(9))){
            gamewinner=2
        }
        //columns check

        if((player1.contains(1)&&player1.contains(4)&&player1.contains(7))||(player1.contains(2)&&player1.contains(5)&&player1.contains(8))||(player1.contains(3)&&player1.contains(6)&&player1.contains(9))){
            gamewinner=1
        }
        else if((player2.contains(1)&&player2.contains(4)&&player2.contains(7))||(player2.contains(2)&&player2.contains(5)&&player2.contains(8))||(player2.contains(3)&&player2.contains(6)&&player2.contains(9))){
            gamewinner=2
        }
        //diagonals check
        if((player1.contains(1)&&player1.contains(5)&&player1.contains(9))||(player1.contains(3)&&player1.contains(5)&&player1.contains(7))){
            gamewinner=1
        }
        else if((player2.contains(1)&&player2.contains(5)&&player2.contains(9))||(player2.contains(3)&&player2.contains(5)&&player2.contains(7))){
            gamewinner=2
        }
        if(gamewinner!=-1){
            val dialogBox1=AlertDialog.Builder(this as Context)
            dialogBox1.setTitle("Game Over")
            dialogBox1.setMessage("Game is completed player $gamewinner is the winner")
            dialogBox1.setPositiveButton("OK"){text,listener->
                startActivity(Intent(this@MainActivity,StartActivity::class.java))
                finish()
            }
            dialogBox1.create()
            dialogBox1.show()

        }
        if (totalarray.containsAll(completearray) && gamewinner==-1){

            val dialogBox=AlertDialog.Builder(this as Context)
            dialogBox.setTitle("Game Over")
            dialogBox.setMessage("Game is completed It is a tie between 1 and 2")
            dialogBox.setPositiveButton("OK"){text,listener->
                startActivity(Intent(this@MainActivity,StartActivity::class.java))
                finish()
            }
            dialogBox.create()
            dialogBox.show()

        }
    }
}