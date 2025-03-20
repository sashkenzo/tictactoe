package com.example.tikataka

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainWinner : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main_winner)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        var winner= getIntent().getStringExtra("winner")
        val image : ImageView = findViewById(R.id.imageView2)
        var text_winner2 : TextView = findViewById(R.id.text_winner2)
        if(winner==null){
            text_winner2.text="ничья";
        }
        if (winner=="X"){
            text_winner2.text="победил игрок "+winner;
            image.setImageResource(R.drawable.x_win)
        }
        if (winner=="O"){
            text_winner2.text="победил игрок "+winner;
            image.setImageResource(R.drawable.o_win)
        }

        val btnPlay: Button = findViewById(R.id.button_play);
        btnPlay.setOnClickListener {
            val intent = Intent(this, MainGameBoard::class.java);
            startActivity(intent);
        }
        val btnAbout: Button = findViewById(R.id.button_about);
        btnAbout.setOnClickListener {
            val intent = Intent(this, MainAbout::class.java);
            startActivity(intent);
        }
    }
}