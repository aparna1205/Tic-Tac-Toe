package com.aparna.tic_tac_toe

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


lateinit var btnsinglePlayer:Button
lateinit var btnDualplayer:Button

class StartActivity:AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.start_activity)
        super.onCreate(savedInstanceState)
        btnsinglePlayer=findViewById(R.id.btnsinglePlayer)
        btnDualplayer=findViewById(R.id.btnDualplayer)
        val intent=Intent(this,MainActivity::class.java)


        btnsinglePlayer.setOnClickListener {
            intent.putExtra("single",true)
            startActivity(intent)
            finish()
        }
        btnDualplayer.setOnClickListener {
            intent.putExtra("double",true)
            startActivity(intent)
            finish()
        }

    }
}