import model.App
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt

class AppAnalyzer {

    // region functions findAppDevelopedByGoogle

    /**
     * @param googleApp This is a list of App object
     * @return This method returns a list of App object specific count
     * @author omar izziddeen
     * @version 1.0
     * @since 2022-05-26
     * @see App
     * @see AppAnalyzer
     *
     */

    fun findAppDevelopedByGoogle(googleApp: MutableList<App>): Int {
        var count = 0
        googleApp.forEach {
            if (it.company.contains("Google")) {
                count++
            }
        }
        return count
    }

    /**
     * Fried Chicken Clan fixes
     */
    fun numberOfAppsDevelopedByGoogle(appsList: MutableList<App>)
            = appsList.filter { it.company.contains("Google") }.size

    // endregion

    // region findPercentageOfMedicalApps
    fun findPercentageOfMedicalApps(googleApp: MutableList<App>): Double {
        var countofmedicalapp = 0
        var countofallapps = 0
        if (googleApp.size == 0)
            return  0.0
        googleApp.forEach {
            if (it.category.contains("Medical")) {
                countofmedicalapp++
            }
            countofallapps++
        }
        return (((countofmedicalapp * 1.0 / countofallapps) * 100) * 10).roundToInt() / 10.0
    }

    /**
     * Fried Chicken Clan fixes
     */
    fun medicalAppsPercentage(appsList: MutableList<App>): Double {
        if(appsList.isEmpty()) return 0.0
        var counter = 0
        appsList.forEach {
            if(it.category == "Medical") counter++
        }
        return String.format("%.2f", counter.toDouble() / appsList.size * 100).toDouble()
    }

    // endregion

    // region findOldestApp

    fun findOldestApp(googleApp: MutableList<App>): String? {
        return if (googleApp.isEmpty()) {
            null
        } else {
            var oldestApp = googleApp[0]
            googleApp.forEach {
                if (it.updated < oldestApp.updated) {
                    oldestApp = it
                }
            }
            oldestApp.appName
        }
    }

    /**
     * Fried Chicken Clan fixes
     */
    fun getOldestApp(appsList: List<App>): App? {
        return if (appsList.isEmpty()) {
            null
        } else {
            var oldestApp = appsList[0]
            appsList.forEach {
                if (it.updated < oldestApp.updated) {
                    oldestApp = it
                }
            }
            oldestApp
        }
    }
    //endregion

    // region findPercentageOfAppRunningOnAndroid9AndUp
    fun findPercentageOfAppRunningOnAndroid9AndUp(App_perc: MutableList<App>): Double {
        var count = 0
        if (App_perc.size == 0)
            return 0.0
        App_perc.forEach {
            if (it.requiresAndroid.contains("9 and up")) {
                count++
            }
        }
        return (((count * 1.0 / App_perc.size) * 100) * 10).roundToInt() / 10.0
    }

    /**
     * Fried Chicken Clan fixes
     */
    fun percentageOfAndroid9AndUp(appsList: MutableList<App>): Double{
        if(appsList.isEmpty()) return 0.0
        var counter = 0
        appsList.forEach {
            if(it.requiresAndroid == "9 and up") counter++
        }
        return String.format("%.2f", counter.toDouble() / appsList.size * 100).toDouble()
    }


    //endregion

    // region findLargest10App
    fun findLargest10App(listOfApp: MutableList<App>): MutableList<String>? {
        val listOfAppName: MutableList<String> = mutableListOf()
        if (listOfApp.size > 9) {
            listOfApp.sortedByDescending { it.size }.subList(0, 10).forEach {
                listOfAppName.add(it.appName)
            }
        } else {
            listOfApp.sortedByDescending { it.size }.forEach {
                listOfAppName.add(it.appName)
            }
        }
        if (listOfAppName.size == 0)
            return null
        return listOfAppName
    }

    /**
     * Fried Chicken Clan fixes
     */
    fun getLargest10Apps(appList: List<App>): MutableList<App>? {
        if (appList.isEmpty()) return null

        val largest10AppsList = mutableListOf<App>()
        if (appList.size > 9) {
            appList.sortedByDescending { it.size }.subList(0, 10).forEach {
                largest10AppsList.add(it)
            }
        } else {
            appList.sortedByDescending { it.size }.forEach {
                largest10AppsList.add(it)
            }
        }
        return largest10AppsList
    }
// endregion

    fun findTop10InstalledApps(listOfApp: MutableList<App>): MutableList<String>? {
        val listOfAppName: MutableList<String> = mutableListOf()
        if (listOfApp.size > 9) {
            listOfApp.sortedByDescending { it.installs }.subList(0, 10).forEach {
                listOfAppName.add(it.appName)
            }
        } else {
            listOfApp.sortedByDescending { it.installs }.forEach {
                listOfAppName.add(it.appName)
            }
        }
        if (listOfAppName.size == 0)
            return null
        return listOfAppName
    }



    /**
     * an extension function to convert the string of different sizes to a unique unit.
     */
    // "923.56K"
    // "123.56M"
    // "1.56G"
    fun String.convertSizeStringToMega(): Double?{
        var size = 0.0
        // 1. check if it is (Varies with device) return 0
        if (this == "Varies with device") return size
        // 2. check if megabytes then remove it and convert to double
        else if (this.contains("M")) {
            size = this.replace("M", "").toDouble()
            // 3. check if kilobytes then remove it and convert to megabyte double
        } else if (this.contains("K")) {
            val num = (this.replace("K", "").toDouble() / 1024.0)
            size = String.format("%.4f",num).toDouble()
            // 4. check if gigabyte then remove it and convert to megabyte double
        } else if (this.contains("G")) {
            size = this.replace("G", "").toDouble() * 1024.0
        }
        return size
    }
}
