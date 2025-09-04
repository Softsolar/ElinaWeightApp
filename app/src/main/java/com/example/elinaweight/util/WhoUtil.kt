
package com.example.elinaweight.util
import com.example.elinaweight.data.SeedData
import kotlinx.datetime.LocalDate
object WhoUtil { fun whoMinSeries(datesIso:List<String>, birthWeight:Int=SeedData.birthWeight, dob:LocalDate=SeedData.dob):List<Int>{ var acc=birthWeight.toDouble(); var prevWeek=0; val out=mutableListOf<Int>(); for(iso in datesIso){ val week=DateUtil.weekFromDob(iso,dob); val dt=week-prevWeek; repeat(dt){ val w=prevWeek+it+1; val gain= when{ w<13->150.0; w<26->100.0; else->85.0 }; acc+=gain }; out+=acc.toInt(); prevWeek=week } return out } }
