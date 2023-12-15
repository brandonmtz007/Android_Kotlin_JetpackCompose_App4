package com.example.proyectojetpackcompostlista5f2023

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.remember
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.proyectojetpackcompostlista5f2023.ui.theme.ProyectoJetPackCompostLista5F2023Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProyectoJetPackCompostLista5F2023Theme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    TaskList()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskList() {
    var task by remember { mutableStateOf(TextFieldValue()) }
    val tasks = remember { mutableStateListOf<String>() }

    Column {
        BasicTextField(
            value = task,
            onValueChange = {
                task = it
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    if (task.text.isNotEmpty()) {
                        tasks.add(task.text)
                        task = TextFieldValue()
                    }
                }
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                if (task.text.isNotEmpty()) {
                    tasks.add(task.text)
                    task = TextFieldValue()
                }
            },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Agregar")
        }

        LazyColumn {
            items(tasks) { task ->
                Card(modifier = Modifier.padding(5.dp)) {
                    Column(modifier = Modifier.padding(5.dp)) {
                        Text(text = task)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ProyectoJetPackCompostLista5F2023Theme {
        TaskList()
    }
}