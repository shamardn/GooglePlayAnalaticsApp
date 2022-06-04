

fun main() {
    val csvTest = CSVParser()

    val appAnalyzer = AppAnalyzer()
    val googlePlayAppList = csvTest.parseCSV()

    val listString = "march 7 2019"

//    with(AppAnalyzer()){
//        println(listString.convertDateStringToDateObject())
//    }
}





