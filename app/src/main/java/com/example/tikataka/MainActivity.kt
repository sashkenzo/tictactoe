package com.example.tikataka

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.content.Intent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    val btnPlay: Button = findViewById(R.id.button_play);
        btnPlay.setOnClickListener {
            val intent = Intent(this, MainGameBoard::class.java);
            intent.putExtra(
                "player2", false,
            )
            startActivity(intent);
        }
    val btnPlay2: Button = findViewById(R.id.button_play2);
    btnPlay2.setOnClickListener {
        val intent = Intent(this, MainGameBoard::class.java);
        intent.putExtra(
            "player2",true,
        )
        startActivity(intent);
        }
    val btnAbout: Button = findViewById(R.id.button_about);
        btnAbout.setOnClickListener {
            val intent = Intent(this, MainAbout::class.java);
            startActivity(intent);
        }
}}