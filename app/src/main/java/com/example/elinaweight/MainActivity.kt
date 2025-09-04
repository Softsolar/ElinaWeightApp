
package com.example.elinaweight
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.elinaweight.data.*
import com.example.elinaweight.ui.ChartScreen
import com.example.elinaweight.ui.InputScreen
import kotlinx.coroutines.launch
class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) { super.onCreate(savedInstanceState)
    val db = AppDatabase.get(this); val repo = WeightRepository(db.dao())
    setContent {
      MaterialTheme { val scope = rememberCoroutineScope(); var tab by remember { mutableStateOf(0) }; var items by remember { mutableStateOf(listOf<WeightEntry>()) }
        LaunchedEffect(Unit) { items = repo.all() }
        Scaffold(topBar = { TopAppBar(title = { Text("Вага Еліни — по тижнях") }) }) { padding ->
          Column(Modifier.fillMaxSize().padding(padding)) {
            TabRow(selectedTabIndex = tab) { listOf("Ввести дані", "Графік").forEachIndexed { i, label -> Tab(selected = tab==i, onClick={tab=i}, text={ Text(label) }) } }
            when (tab) { 0 -> InputScreen(items){ entry -> scope.launch { repo.save(entry); items = repo.all() } }; 1 -> ChartScreen(items) }
          }
        }
      }
    }
  }
}
