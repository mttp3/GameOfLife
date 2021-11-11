package com.mttp.gameoflife

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.Modifier
import com.mttp.gameoflife.ui.theme.GameOfLifeTheme
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlin.math.min

class MainActivity : ComponentActivity() {

//    private var grid = GameGrid(100, 100)
//
//    init {
//        grid.randomize()
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GameOfLifeTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text("Game Of Life", fontSize = 30.sp)

                        Spacer(Modifier.size(10.dp))

                        var animate by remember { mutableStateOf(false) }
                        //var grid = remember { mutableStateOf( GameGrid(100, 100) ) }
                        val (grid, setGrid) = remember { mutableStateOf( GameGrid(100, 100)) }

                        Row(Modifier.align(Alignment.End)) {
                            GotoEditButton()
                            Spacer(Modifier.size(10.dp))
                        }

                        Spacer(Modifier.size(20.dp))

                        AnimatedGrid(grid, animate)

                        Spacer(Modifier.size(20.dp))

                        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                            Button(onClick = { setGrid( grid.evolve() ) }) {
                                Text("Evolve")
                            }
                            Button(onClick = { animate = true } ) {
                                Text("Start")
                            }
                            Button(onClick = { animate = false } ) {
                                Text("Stop")
                            }
                            Button(onClick = { setGrid( grid.randomize() ) } ) {
                                Text("Randomize")
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun GotoEditButton() {
    val context = LocalContext.current
    Button(onClick = {
        val intent = Intent(context, EditGridActivity::class.java)
        context.startActivity(intent)
    }) {
        Text(text = "Grid Editor")
    }
}

@Composable
fun AnimatedGrid(grid: GameGrid, animate: Boolean) {
    var generation = grid.generation
    if (animate) {
        var state = produceState(grid.generation) {
            while(true) {
                grid.evolve()
                value = grid.generation
                delay(100L)
            }
        }
        generation = state.value
    }
    var displayGrid = grid
    DrawGrid(displayGrid)
    Spacer(Modifier.size(10.dp))
    Text("Generation: $generation")
}

@Composable
fun DrawGrid(grid: GameGrid) {
    Column {
        Canvas(
            Modifier.layout {
                measurable, constraints ->
                    val canvasSize = constraints.maxWidth
                    val placeable = measurable.measure(constraints.copy(
                        minWidth = canvasSize,
                        maxWidth = canvasSize,
                        minHeight = canvasSize,
                        maxHeight = canvasSize,
                    ))
                    layout( canvasSize, canvasSize ) {
                        placeable.place(0, 0)
                    }
            }
            .background(Color.LightGray)
            .padding(5.dp)
        ) {
            val canvasWidth = size.width
            val canvasHeight = size.height

            val step = min(canvasWidth / grid.width, canvasHeight / grid.height)
            val size = Size(step, step)
            var xCurrent = 0.0f
            var yCurrent = 0.0f

            for (row in grid.cell) {
                for (element in row) {
                    val color = if (element == 0) Color.White else Color.Black

                    drawRect(color, topLeft = Offset(xCurrent, yCurrent), size)
                    yCurrent += step
                }
                xCurrent += step
                yCurrent = 0.0f
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    GameOfLifeTheme {
//        Greeting("Android")
//    }
//}