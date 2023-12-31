package com.repro.keyboardbug

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.repro.keyboardbug.ui.theme.RepoKeyboardBugTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var entryState = remember { mutableStateOf(false) }
            RepoKeyboardBugTheme {
                Scaffold(
                    bottomBar = { Navigation(entryState) }
                ) { padding ->
                    Content(entryState, Modifier.padding(padding))
                }
            }
        }
    }

}

@Composable
private fun Content(entryState: MutableState<Boolean>, modifier: Modifier = Modifier) {
    var showText by entryState
    Column(modifier.padding(16.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Click to toggle text entry", Modifier.weight(1f), style = MaterialTheme.typography.bodyLarge)
            Button({ showText = !showText }) {
                Text(if (showText) "Hide" else "Show", style = MaterialTheme.typography.bodyLarge)
            }
        }
        LongList(Modifier.weight(1f))
        if (showText) {
            DataEntry(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            )
        }
    }
}

@Composable
private fun LongList(modifier: Modifier = Modifier) {
    LazyColumn(
        modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primaryContainer), contentPadding = PaddingValues(vertical = 8.dp)
    ) {
        items(100) {
            Text("Item ${it + 1}")
        }
    }
}

@Composable
private fun DataEntry(modifier: Modifier = Modifier) {
    var text by remember { mutableStateOf("") }
    TextField(
        value = text,
        onValueChange = { text = it },
        modifier = modifier,
        placeholder = { Text("Enter some text here") },
        textStyle = MaterialTheme.typography.bodyLarge,
    )
}

@Composable
private fun Navigation(entryState: State<Boolean>) {
    if (entryState.value) return
    var selectedItem by remember { mutableStateOf(0) }
    BottomAppBar {
        NavigationBar {
            NavigationBarItem(selected = selectedItem == 0, onClick = { selectedItem = 0 }, icon = { Icon(Icons.Default.Home, "Home") })
            NavigationBarItem(selected = selectedItem == 1, onClick = { selectedItem = 1 }, icon = { Icon(Icons.Default.Phone, "Phone") })
            NavigationBarItem(selected = selectedItem == 2, onClick = { selectedItem = 2 }, icon = { Icon(Icons.Default.Email, "Email") })
        }
    }
}
