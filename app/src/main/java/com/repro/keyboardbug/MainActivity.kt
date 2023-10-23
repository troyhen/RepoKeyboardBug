package com.repro.keyboardbug

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.repro.keyboardbug.ui.theme.RepoKeyboardBugTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var showText by remember { mutableStateOf(false) }
            RepoKeyboardBugTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp), color = MaterialTheme.colorScheme.background
                ) {
                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text("Click to toggle text entry", Modifier.weight(1f), style = MaterialTheme.typography.bodyLarge)
                            Button({ showText = !showText }) {
                                Text(if (showText) "Hide" else "Show", style = MaterialTheme.typography.bodyLarge)
                            }
                        }
                        LongList(Modifier.weight(1f))
                        if (showText) {
                            DataEntry(Modifier.fillMaxWidth().padding(top = 16.dp))
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun LongList(modifier: Modifier = Modifier) {
    LazyColumn(modifier.fillMaxWidth().background(MaterialTheme.colorScheme.primaryContainer), contentPadding = PaddingValues(vertical = 8.dp)) {
        items(100) {
            Text("Item ${it + 1}")
        }
    }
}

@Composable
private fun DataEntry(modifier: Modifier = Modifier) {
    var text by remember { mutableStateOf("") }
    BasicTextField(value = text, onValueChange = { text = it }, modifier, textStyle = MaterialTheme.typography.bodyLarge)
}
