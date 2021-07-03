package com.mttp.gameoflife

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mttp.gameoflife.ui.theme.GameOfLifeTheme
import kotlin.math.min

class EditGridActivity : ComponentActivity() {

    private var grid = GameGrid(10, 10)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GameOfLifeTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text("Grid Editor", fontSize = 30.sp)
                        Spacer(Modifier.size(10.dp))

                        Row(Modifier.align(Alignment.End)) {
                            GotoMainButton()
                            Spacer(Modifier.size(10.dp))
                        }

                        Spacer(Modifier.size(10.dp))
                        EditableGrid(grid)
                        Spacer(Modifier.size(10.dp))
                        Row {
                            Button(onClick = {

                            }) {
                                Text(text = "Copy to Game")
                            }
                            Button(onClick = { grid.clear() }) { Text(text = "Clear")}
                        }
                    }
                }
            }
        }
    }

}

@Composable
fun GotoMainButton() {
    val context = LocalContext.current
    Button(onClick = {
        val intent = Intent(context, MainActivity::class.java)
        context.startActivity(intent)
    }) {
        Text(text = "Game of Life")
    }
}

@Composable
fun EditableGrid(grid: GameGrid) {
    BoxWithConstraints() {
        val cellMaxHeight = maxHeight / grid.height
        val cellMaxWidth = maxWidth / grid.width
        val cellDimension = minOf(cellMaxHeight, cellMaxWidth)
        Column {
            repeat(grid.height) { y ->
                Row {
                    repeat(grid.width) { x ->
                        Canvas(
                        Modifier
                            .width(cellDimension)
                            .height(cellDimension)
                            .clickable { grid.cell[x][y] = if (grid.cell[x][y] == 0) 1 else 0 }
                        ) {
                            val color = if (grid.cell[x][y] == 0) Color.Gray else Color.Black
                            drawRect(color)
                        }
                    }
                }
            }
        }
    }
}
