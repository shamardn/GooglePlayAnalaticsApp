import model.App
import java.io.File
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class CSVParser {
    private val file = File("src/main/google_play.csv")
    fun parseCSV(): MutableList<App> {
        val apps = mutableListOf<App>()
        file.forEachLine {
            val lineList = it.split(",")
            apps.add(
                App(
                    appName = lineList[0],
                    company = lineList[1],
                    category = lineList[2],
                    updated = stringToDate(lineList[3]),
                    size = megaByteConverter(lineList[4]),
                    installs = stringToLongNum(lineList[5]),
                    requiresAndroid = arrangeRequiresAndroidData(lineList[7]),
                )
            )
        }
        return apps
    }

    // convert from String to Date
    private fun stringToDate(value: String): LocalDate {
        return LocalDate.parse(value, DateTimeFormatter.ofPattern("MMMM d yyyy"))
    }


    // convert from String to Long Number
    private fun stringToLongNum(value: String): Long {
        return value.toLong()
    }

    // remove Varies with device from data
    private fun arrangeRequiresAndroidData(value: String): String {
        if (value == "Varies with device") return value.replace("Varies with device", "")
        return value
    }


    // convert from GB or KB to MB
    fun megaByteConverter(value: String): Double {
        var size = 0.0
        // 1. check if it is (Varies with device) return 0
        if (value == "Varies with device") return size
        // 2. check if megabytes then remove it and convert to double
        if (value.contains("M")) {
            size = value.replace("M", "").toDouble()
            // 3. check if kilobytes then remove it and convert to megabyte double
        } else if (value.contains("k")) {
            size = value.replace("k", "").toDouble() / 1024.0
            // 4. check if gigabyte then remove it and convert to megabyte double
        } else if (value.contains("G")) {
            size = value.replace("G", "").toDouble() * 1024.0
        }
        return size
    }
}