
package com.example.elinaweight.ui
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.elinaweight.data.SeedData
import com.example.elinaweight.data.WeightEntry
import com.example.elinaweight.util.DateUtil
@Composable fun InputScreen(items:List<WeightEntry>, onSave:(WeightEntry)->Unit){ var date by remember{ mutableStateOf(TextFieldValue("")) }; var weight by remember{ mutableStateOf(TextFieldValue("")) }; var averaged by remember{ mutableStateOf(false) }
  Column(Modifier.padding(16.dp)){ Text("Додати/оновити вимір", style=MaterialTheme.typography.titleMedium); Spacer(Modifier.height(8.dp))
    OutlinedTextField(date,{date=it}, label={ Text("Дата (YYYY-MM-DD)") }); Spacer(Modifier.height(8.dp)); OutlinedTextField(weight,{weight=it}, label={ Text("Вага, г") }); Spacer(Modifier.height(8.dp))
    Row(verticalAlignment=Alignment.CenterVertically){ Checkbox(averaged, onCheckedChange={ averaged=it }); Text("Осереднене значення (*)") }
    Spacer(Modifier.height(12.dp)); Button(onClick={ val iso=date.text.trim(); val w=weight.text.trim().toIntOrNull()?:return@Button; val week=DateUtil.weekFromDob(iso, SeedData.dob); onSave(WeightEntry(dateIso=iso, week=week, weightGrams=w, isAveraged=averaged)); date=TextFieldValue(""); weight=TextFieldValue(""); averaged=false }){ Text("Зберегти") }
    Spacer(Modifier.height(24.dp)); Text("Виміри (останні):", style=MaterialTheme.typography.titleSmall); Spacer(Modifier.height(8.dp)); items.takeLast(10).reversed().forEach{ Text("Тиж ${it.week} • ${it.dateIso} • ${it.weightGrams} г ${if(it.isAveraged) "(*)" else ""}") }
  }
}
