package com.example.tikataka

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.graphics.toColorInt
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
        val btnAbout: Button = findViewById(R.id.button_about)
        btnAbout.setOnClickListener {
            val intent = Intent(this, MainAbout::class.java);
            startActivity(intent);
        }
        var activePlayer = 0;

        val move = arrayOf("X", "O");

        var saveMove = arrayOf(2, 2, 2, 2, 2, 2, 2, 2, 2)

        var countMove = 0

        val player = getIntent().getBooleanExtra("player2",false)

        val field0: Button = findViewById(R.id.field_0_0);
        val field1: Button = findViewById(R.id.field_0_1);
        val field2: Button = findViewById(R.id.field_0_2);
        val field3: Button = findViewById(R.id.field_1_0);
        val field4: Button = findViewById(R.id.field_1_1);
        val field5: Button = findViewById(R.id.field_1_2);
        val field6: Button = findViewById(R.id.field_2_0);
        val field7: Button = findViewById(R.id.field_2_1);
        val field8: Button = findViewById(R.id.field_2_2);

        var checkRow0 = false
        var checkRow1 = false
        var checkRow2 = false
        var checkCol0 = false
        var checkCol1 = false
        var checkCol2 = false
        var checkDiag0 = false
        var checkDiag1 = false

        val boardTiles =
            arrayOf(field0, field1, field2, field3, field4, field5, field6, field7, field8);


        fun playerChange(num: Int): Int {
            return 1 - num
        }

        fun resetBoard(emptyBoardView: Array<Button>, emptySaveMove: Array<Int>) {
            for (n in 0..8) {
                emptyBoardView[n].text = "-"
                emptySaveMove[n] = 2
                checkRow0 = false
                checkRow1 = false
                checkRow2 = false
                checkCol0 = false
                checkCol1 = false
                checkCol2 = false
                checkDiag0 = false
                checkDiag1 = false
            }
        }

        resetBoard(boardTiles, saveMove)

        fun finishBoard(result: Array<Button>) {
            for (n in 0..8) {
                result[n].setTextColor("#ffffff".toColorInt())
                result[n].setBackgroundColor("#EC0C0C".toColorInt())
                result[n].isEnabled = false
            }
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

        var saveMove2 = arrayOf(


        "0", "1", "2",
        "3", "4", "5",
        "6", "7", "8"
        )





        fun computerMove(result: Array<Int>, moveTile: Int,activeTile:Int): Int {
            val i = activeTile
            var newMoveTile = moveTile

            fun saveTile(value: Int,tile:Int):Int{
                var newTile = tile
                if (saveMove[value] == 2) {
                    newTile = value
                } else {
                    newTile = moveTile
                }
                return newTile
            }

            fun checkForUse(first: Int, second:Int,value:Int): Boolean{
                if (result[first] == i && result[second] == i){
                    newMoveTile = saveTile(value,newMoveTile)
                    return true
                    }
                return false
            }

            if(!checkRow0){
                if(checkForUse(1,2,0)){
                    checkRow0 = true}
                if (checkForUse(0,2,1)){
                    checkRow0 = true}
                if (checkForUse(0,1,2)){
                    checkRow0 = true}}
            if(!checkRow1){
                if (checkForUse(4,5,3)){
                    checkRow1 = true}
                if (checkForUse(3,5,4)){
                    checkRow1 = true}
                if (checkForUse(3,4,5)){
                    checkRow1 = true}}
            if(!checkRow2){
                if(checkForUse(7,8,6)){
                    checkRow2=true}
                if(checkForUse(6,8,7)){
                    checkRow2=true}
                if(checkForUse(6,7,8)){
                    checkRow2=true}}
            if(!checkCol0){
                if (checkForUse(3,6,0)){
                    checkCol0 = true}
                if (checkForUse(6,0,3)){
                    checkCol0 = true}
                if (checkForUse(0,3,6)){
                    checkCol1=true}}
            if(!checkCol1){
                if (checkForUse(4,7,1)){
                    checkCol1 = true}
                if (checkForUse(1,7,4)){
                    checkCol1 = true}
                if(checkForUse(1,4,7)){
                    checkCol1=true}}
            if(!checkCol2){
                if (checkForUse(5,8,2)){
                    checkCol2 = true}
                if (checkForUse(2,8,5)){
                    checkCol2 = true}
                if(checkForUse(2,5,8)){
                    checkCol2=true}}
            if(!checkDiag0){
                if (checkForUse(4,8,0)){
                    checkDiag0 = true}
                if (checkForUse(8,0,4)){
                    checkDiag0 = true}
                if(checkForUse(0,4,8)){
                    checkDiag0=true}}
            if(!checkDiag1){
                if (checkForUse(6,4,2)){
                    checkDiag1 = true}
                if (checkForUse(2,6,4)){
                    checkDiag1 = true}
                if(checkForUse(2,4,6)){
                    checkDiag1=true}}

            return newMoveTile
        }
        fun playerMove(moveTile: Int, saveMove: Array<Int>, boardTiles: Array<Button>) {
            var newMoveTile = moveTile
            if(activePlayer==1){
            newMoveTile = computerMove(saveMove, moveTile,activePlayer)
            newMoveTile = computerMove(saveMove, newMoveTile,1-activePlayer)
            }
            boardTiles[newMoveTile].setText(move[activePlayer].toString());
            boardTiles[newMoveTile].setTextColor("#ffffff".toColorInt())
            boardTiles[newMoveTile].setBackgroundColor("#EC0C0C".toColorInt())
            boardTiles[newMoveTile].isEnabled = false
            saveMove[newMoveTile] = activePlayer
            countMove += 1
            if (drawCheck(boardTiles) && !matchCheck(boardTiles, move[activePlayer])) {
                finishBoard(boardTiles)
                val intent = Intent(this, MainWinner::class.java);
                startActivity(intent);
            }
            if (matchCheck(boardTiles, move[activePlayer])) {
                finishBoard(boardTiles)
                val intent = Intent(this, MainWinner::class.java);
                intent.putExtra(
                    "winner", move[activePlayer].toString(),
                )
                startActivity(intent);
            }
            activePlayer = playerChange(activePlayer);
        }

        fun randomTile(saveMove: Array<Int>): Int {
            while (true) {
                val nextMove = Random.nextInt(0, 8)
                if (saveMove[nextMove] == 2) {
                    return nextMove
                }
            }
        }

        for (n in 0..8) {
            boardTiles[n].setOnClickListener() {
                if (player) {
                    playerMove(n, saveMove, boardTiles)
                } else {
                    if (activePlayer == 0) {
                        playerMove(n, saveMove, boardTiles)
                    }
                    if (activePlayer == 1 && !matchCheck(boardTiles, move[0]) && !drawCheck(
                            boardTiles
                        )
                    ) {
                        val nextMove = randomTile(saveMove)
                        playerMove(nextMove, saveMove, boardTiles)
                    }
                }
            }
        }
    }
}






