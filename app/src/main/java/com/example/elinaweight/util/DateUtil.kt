
package com.example.elinaweight.util
import com.example.elinaweight.data.SeedData
import kotlinx.datetime.*
import kotlin.math.floor
object DateUtil {
  fun parse(iso:String)=LocalDate.parse(iso)
  fun weekFromDob(iso:String, dob:LocalDate=SeedData.dob):Int{ val d=parse(iso); val days=dob.daysUntil(d); return floor(days/7.0).toInt() }
  fun labelWeekDate(iso:String, dob:LocalDate=SeedData.dob):String{ val w=weekFromDob(iso,dob); val d=parse(iso); val dm=d.dayOfMonth.toString().padStart(2,'0'); val mm=d.monthNumber.toString().padStart(2,'0'); return "Тиж $w
$dm.$mm" }
  fun fifthsFromDobTo(endDate:LocalDate, dob:LocalDate=SeedData.dob):List<LocalDate>{ val list=mutableListOf<LocalDate>(); var y=dob.year; var m=dob.monthNumber; while(true){ val d=LocalDate(y,m,5); if(d> endDate) break; if(d>=dob) list+=d; m+=1; if(m>12){ m=1; y+=1 } } return list }
  fun xFromDate(iso:String, dob:LocalDate=SeedData.dob):Float{ val d=parse(iso); val days=dob.daysUntil(d); return days.toFloat() }
}
