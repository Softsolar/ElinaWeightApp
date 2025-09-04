
package com.example.elinaweight.ui
import androidx.compose.runtime.Composable
import com.example.elinaweight.data.SeedData
import com.example.elinaweight.data.WeightEntry
@Composable fun ChartScreen(entries: List<WeightEntry>){ WeightLineChart(entries=entries, dob=SeedData.dob, birthWeight=SeedData.birthWeight) }
