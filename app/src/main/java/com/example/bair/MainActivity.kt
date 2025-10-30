    package com.example.bair

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bair.ui.theme.BairTheme

    class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BairTheme {
                Surface {
                    Greeting()
                }
            }
        }
    }
}

@Composable
fun Greeting(modifier: Modifier = Modifier) {
    var input by remember { mutableStateOf("0") }
    var rand by remember { mutableIntStateOf((1..100).random()) }
    var text1 by remember {mutableStateOf("Попробуйте угадать число (1 - 100)")}
    Column(){
        Text(
            text = "Угадай число",
            fontSize = 20.sp,
            textAlign = TextAlign.Justify,
            modifier = Modifier
                .background(Color.Yellow)
                .fillMaxWidth()
                .height(100.dp)
                .padding(top = 30.dp)

        )
        Spacer(modifier.padding(100.dp))
        Text(
            text = text1,
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            modifier = modifier
                .fillMaxWidth()
        )
        Spacer(modifier.padding(30.dp))
        TextField(
            value = input.toString(),
            onValueChange = {input = it},
            modifier = Modifier
                .fillMaxWidth()
        )
        Button( onClick = {
            if (input.toIntOrNull() is Int) {
                val check = checkGame(input.toInt(), rand)
                if (check == "Finished") {
                    text1 = "В точку! Давай сыграем ещё!"
                    rand = (1..100).random()
                    input = ""
                } else if (check == "More") {
                    text1 = "Перелёт!"
                } else if (check == "Less") {
                    text1 = "Недолёт!"
                }
            }
            else
            {
                text1 = "Ошибка: Введите число."
                input = "0"
            }
            },
            modifier = Modifier.fillMaxWidth(), shape = RectangleShape) {
            Text("ВВЕСТИ ЗНАЧЕНИЕ")
        }
    }
}

fun checkGame(input: Int, rand: Int): String {
    if (input == rand)
    {
        return "Finished"
    }

        if (input < rand) {
            return "Less"
        } else if (input > rand) {
            return "More"
        }
    return "Error"
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BairTheme {
        Greeting()
    }
}