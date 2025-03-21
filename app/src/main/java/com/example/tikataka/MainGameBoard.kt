package com.example.tikataka

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.Switch
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.graphics.toColorInt
import kotlinx.coroutines.delay
import kotlin.random.Random

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

        val move = arrayOf("X", "O");

        var saveMove = arrayOf("", "", "", "", "", "", "", "", "")

        var countMove = 0


        val field0: Button = findViewById(R.id.field_0_0);
        val field1: Button = findViewById(R.id.field_0_1);
        val field2: Button = findViewById(R.id.field_0_2);
        val field3: Button = findViewById(R.id.field_1_0);
        val field4: Button = findViewById(R.id.field_1_1);
        val field5: Button = findViewById(R.id.field_1_2);
        val field6: Button = findViewById(R.id.field_2_0);
        val field7: Button = findViewById(R.id.field_2_1);
        val field8: Button = findViewById(R.id.field_2_2);

        val boardTiles =
            arrayOf(field0, field1, field2, field3, field4, field5, field6, field7, field8);


        fun playerChange(num: Int): Int {
            return 1 - num
        }

        fun drawCheck(result: Array<Button>): Boolean {
            if (result[0].text != "-" && result[1].text != "-" && result[2].text != "-" &&
                result[3].text != "-" && result[4].text != "-" && result[5].text != "-" &&
                result[6].text != "-" && result[7].text != "-" && result[8].text != "-"
            ) {
                return true
            } else {
                return false
            }

        }

        fun matchCheck(result: Array<Button>, player: String): Boolean {

            if ((result[0].text == player && result[1].text == player && result[2].text == player) ||
                (result[3].text == player && result[4].text == player && result[5].text == player) ||
                (result[6].text == player && result[7].text == player && result[8].text == player) ||
                (result[0].text == player && result[3].text == player && result[6].text == player) ||
                (result[1].text == player && result[4].text == player && result[7].text == player) ||
                (result[2].text == player && result[5].text == player && result[8].text == player) ||
                (result[0].text == player && result[4].text == player && result[8].text == player) ||
                (result[2].text == player && result[4].text == player && result[6].text == player)
            ) {
                return true
            } else {
                return false
            }
        }

        fun playerMove(moveTile: Int, saveMove: Array<String>, boardTiles: Array<Button>) {
            boardTiles[moveTile].setText(move[activePlayer].toString());
            boardTiles[moveTile].setTextColor("#ffffff".toColorInt())
            boardTiles[moveTile].setBackgroundColor("#EC0C0C".toColorInt())
            boardTiles[moveTile].isEnabled = false
            saveMove[moveTile] = move[activePlayer]
            countMove += 1
            if (drawCheck(boardTiles) && !matchCheck(boardTiles, move[activePlayer])) {
                val intent = Intent(this, MainWinner::class.java);
                startActivity(intent);
            }
            if (matchCheck(boardTiles, move[activePlayer])) {
                val intent = Intent(this, MainWinner::class.java);
                intent.putExtra(
                    "winner", move[activePlayer].toString(),
                )
                startActivity(intent);
            }
            activePlayer = playerChange(activePlayer);


        }


        fun randomTile(saveMove: Array<String>): Int {
            while (true) {
                val nextMove = Random.nextInt(0, 8)
                if (saveMove[nextMove] == "") {
                    return nextMove
                }
            }
        }


        for (n in 0..8) {

            boardTiles[n].setOnClickListener {
                if (activePlayer == 0) {
                    playerMove(n, saveMove, boardTiles)
                }
                if (activePlayer == 1 && countMove<9) {
                    val nextMove = randomTile(saveMove)
                    playerMove(nextMove, saveMove, boardTiles)
                }
            }
        }
    }
}




