package com.example.tikataka

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.graphics.toColorInt

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

        var move = arrayOf('X', 'O');

            val field0x0: Button = findViewById(R.id.field_0_0);
            val field0x1: Button = findViewById(R.id.field_0_1);
            val field0x2: Button = findViewById(R.id.field_0_2);
            val field1x0: Button = findViewById(R.id.field_1_0);
            val field1x1: Button = findViewById(R.id.field_1_1);
            val field1x2: Button = findViewById(R.id.field_1_2);
            val field2x0: Button = findViewById(R.id.field_2_0);
            val field2x1: Button = findViewById(R.id.field_2_1);
            val field2x2: Button = findViewById(R.id.field_2_2);

        var boardFields = arrayOf(field0x0,field0x1,field0x2,field1x0,field1x1,field1x2,field2x0,field2x1,field2x2);

        fun checkBoard(){
            var resultCheck=arrayOf(boardFields[0].text,boardFields[1].text,boardFields[2].text,boardFields[3].text,boardFields[4].text,boardFields[5].text,boardFields[6].text,boardFields[7].text,boardFields[8].text);
            if(resultCheck[0]!="-" && resultCheck[1]!="-" && resultCheck[2]!="-" &&
                resultCheck[3]!="-" && resultCheck[4]!="-" && resultCheck[5]!="-"
                && resultCheck[6]!="-" && resultCheck[7]!="-" && resultCheck[8]!="-"){
                val intent = Intent(this, MainWinner::class.java);
                startActivity(intent);
            }

            if ((resultCheck[0]==resultCheck[1] && resultCheck[1]==resultCheck[2] && resultCheck[1]!="-") ||
                    (resultCheck[3]==resultCheck[4] && resultCheck[4]==resultCheck[5] && resultCheck[4]!="-") ||
                        (resultCheck[6]==resultCheck[7] && resultCheck[7]==resultCheck[8] && resultCheck[7]!="-") ||
                            (resultCheck[0]==resultCheck[3] && resultCheck[3]==resultCheck[6] && resultCheck[3]!="-") ||
                                (resultCheck[1]==resultCheck[4] && resultCheck[4]==resultCheck[7] && resultCheck[4]!="-") ||
                                    (resultCheck[2]==resultCheck[5] && resultCheck[5]==resultCheck[8] && resultCheck[5]!="-") ||
                                        (resultCheck[0]==resultCheck[4] && resultCheck[4]==resultCheck[8] && resultCheck[8]!="-") ||
                                            (resultCheck[2]==resultCheck[4] && resultCheck[4]==resultCheck[6] && resultCheck[6]!="-")
                ){
                val intent = Intent(this, MainWinner::class.java);
                intent.putExtra("winner",move[1-activePlayer].toString())
                startActivity(intent);

            }


        }
        for (n in 0..8) {
            boardFields[n].setOnClickListener {
                boardFields[n].setText(move[activePlayer].toString());
                boardFields[n].setTextColor("#ffffff".toColorInt())
                boardFields[n].setBackgroundColor("#EC0C0C".toColorInt())
                boardFields[n].isEnabled = false
                activePlayer = playerChange(activePlayer);
                checkBoard()
            }
        }


        }
    }