
package com.example.elinaweight.ui
import android.graphics.Color
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView
import com.example.elinaweight.data.WeightEntry
import com.example.elinaweight.util.DateUtil
import com.example.elinaweight.util.WhoUtil
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.LimitLine
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import kotlinx.datetime.LocalDate
@Composable fun WeightLineChart(entries: List<WeightEntry>, dob: LocalDate, birthWeight: Int){ AndroidView(factory={ ctx-> LineChart(ctx).apply{ layoutParams=android.widget.LinearLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT); description.isEnabled=false; setNoDataText("Немає даних"); axisRight.isEnabled=false; legend.isEnabled=true; legend.textSize=12f; xAxis.position=XAxis.XAxisPosition.BOTTOM; xAxis.textSize=10f; xAxis.granularity=1f; xAxis.valueFormatter=object: ValueFormatter(){ override fun getFormattedValue(v:Float):String{ val days=v.toInt(); val date=dob.plus(days, kotlinx.datetime.DateTimeUnit.DAY); val week=(days/7f).toInt(); val dm=date.dayOfMonth.toString().padStart(2,'0'); val mm=date.monthNumber.toString().padStart(2,'0'); return "Тиж $week
$dm.$mm" } }; axisLeft.setDrawGridLines(true) } }, update={ chart-> if(entries.isEmpty()) return@AndroidView; val sorted=entries.sortedBy{it.week}; val xs=sorted.map{ DateUtil.xFromDate(it.dateIso, dob) }; val ys=sorted.map{ it.weightGrams.toFloat() };
  val actual=LineDataSet(xs.zip(ys).mapIndexed{ i,(x,y)-> Entry(x,y,sorted[i]) }, "Фактична вага").apply{ color=Color.parseColor("#1f77b4"); setDrawCircles(true); circleRadius=3.8f; setCircleColor(color); lineWidth=2.2f; setDrawValues(true); valueTextSize=9f; valueFormatter=object: ValueFormatter(){ override fun getPointLabel(e:Entry?):String{ val idx=sorted.indexOf(e?.data as? WeightEntry); if(idx<=0) return "+0"; val gain=sorted[idx].weightGrams - sorted[idx-1].weightGrams; val sign= if(gain>0) "+" else ""; return "$sign$gain" } } }
  val below=LineDataSet(sorted.mapIndexedNotNull{ i,it-> if(it.isAveraged) null else Entry(xs[i], ys[i]-60f, it) }, "Вага (реальні)").apply{ color=Color.TRANSPARENT; setDrawCircles(false); lineWidth=0f; setDrawValues(true); valueTextSize=9f; valueTextColor=Color.parseColor("#1f77b4"); valueFormatter=object: ValueFormatter(){ override fun getPointLabel(e:Entry?):String{ val we=e?.data as? WeightEntry ?: return ""; return we.weightGrams.toString() } } }
  val who=WhoUtil.whoMinSeries(sorted.map{it.dateIso}, birthWeight, dob); val whoSet=LineDataSet(xs.zip(who).map{(x,y)-> Entry(x, y.toFloat())}, "Мін. норма ВОЗ").apply{ color=Color.GRAY; lineWidth=2f; setDrawCircles(false); enableDashedLine(15f,10f,0f); setDrawValues(false) }
  val avg=LineDataSet(sorted.mapIndexedNotNull{ i,it-> if(it.isAveraged) Entry(xs[i], ys[i]) else null }, "Осереднені (*)").apply{ color=Color.TRANSPARENT; setDrawCircles(true); setCircleColor(Color.parseColor("#ff7f0e")); circleRadius=4.0f; setDrawValues(false) }
  chart.xAxis.removeAllLimitLines(); val last= kotlinx.datetime.LocalDate.parse(sorted.last().dateIso); val fifths=DateUtil.fifthsFromDobTo(last, dob); fifths.forEachIndexed{ idx, d-> val x=dob.daysUntil(d).toFloat(); val ll=LimitLine(x, "${idx} міс"); ll.lineColor=Color.RED; ll.lineWidth=1.2f; ll.textColor=Color.RED; ll.textSize=9f; chart.xAxis.addLimitLine(ll) }
  chart.data=LineData(listOf(whoSet, actual, below, avg)); chart.invalidate()
}) }
