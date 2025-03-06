package com.example.tikataka

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainGameBoard : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main_game_board)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val btnAbout: Button = findViewById(R.id.button_about);
        btnAbout.setOnClickListener {
            val intent = Intent(this, MainAbout::class.java);
            startActivity(intent);
        }

        var activePlayer = 0;

        fun playerChange(num: Int): Int{ return 1-num }

        val move = arrayOf('X', 'O');

        var board = arrayOf(arrayOf('-', '-', '-'), arrayOf('-', '-', '-'), arrayOf('-', '-', '-'))

        var board_2 = Array(3) { Array(3) { '-' } }

        fun btnPressMoves(field: String) {
            val btnField: Button = findViewById(R.id.field_0_0);
            btnField.setOnClickListener {

                btnField.setText("${move[activePlayer].toString()}");
                btnField.setTextColor(Color.parseColor("#ffffff"))
                btnField.setBackgroundColor(Color.parseColor("#EC0C0C"))
                //btnField.isEnabled = false
                activePlayer=playerChange(activePlayer);
                }
            }

            val btnField0x0: Button = findViewById(R.id.field_0_0);
            btnField0x0.setOnClickListener {

                btnField0x0.setText("${move[activePlayer].toString()}");
                btnField0x0.setTextColor(Color.parseColor("#ffffff"))
                btnField0x0.setBackgroundColor(Color.parseColor("#EC0C0C"))
                btnField0x0.isEnabled = false
                activePlayer=playerChange(activePlayer);

            }



        }
    }