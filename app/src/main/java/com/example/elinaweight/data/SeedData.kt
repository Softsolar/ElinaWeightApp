
package com.example.elinaweight.data
import kotlinx.datetime.LocalDate
object SeedData { val dob = LocalDate.parse("2025-03-05"); const val birthWeight = 3730
  fun entries(): List<WeightEntry> { val rows = listOf(
    Triple(0,"2025-03-05",3730) to false, Triple(1,"2025-03-12",3830) to true, Triple(2,"2025-03-19",3930) to true, Triple(3,"2025-03-26",4030) to true, Triple(4,"2025-04-02",4130) to true,
    Triple(5,"2025-04-09",4230) to true, Triple(6,"2025-04-16",4330) to true, Triple(7,"2025-04-23",4430) to true, Triple(8,"2025-04-30",4530) to true, Triple(9,"2025-05-07",4630) to true,
    Triple(10,"2025-05-14",4730) to true, Triple(11,"2025-05-21",4830) to true, Triple(12,"2025-05-28",4930) to false, Triple(13,"2025-06-04",5145) to false, Triple(14,"2025-06-11",5250) to false,
    Triple(15,"2025-06-18",5445) to false, Triple(16,"2025-06-25",5625) to false, Triple(17,"2025-07-02",5725) to false, Triple(18,"2025-07-09",5715) to false, Triple(19,"2025-07-16",5850) to false,
    Triple(20,"2025-07-23",5900) to false, Triple(21,"2025-07-30",6070) to false, Triple(22,"2025-08-06",6150) to false, Triple(23,"2025-08-13",6255) to false, Triple(24,"2025-08-20",6245) to false,
    Triple(25,"2025-08-27",6385) to false, Triple(26,"2025-09-01",6505) to false );
    return rows.map{(t,avg)-> val(week,dateStr,weight)=t; WeightEntry(dateIso=dateStr, week=week, weightGrams=weight, isAveraged=avg)} }
}
