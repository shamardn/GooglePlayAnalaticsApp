import model.App
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import kotlin.test.assertNull

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class FriedChickenTest{

    private lateinit var appAnalyzer: AppAnalyzer

    @BeforeAll
    fun setup() {
        appAnalyzer = AppAnalyzer()
    }

    // region test functions for numberOfAppsDevelopedByGoogle function
    @Test
    fun should_ReturnNumber1_When_HaveListWith1GoogleApp() {
        // given list contain one "Google" word
        val appList = mutableListOf<App>()
        appList.add(
            App(
                "Jewel Blast : Temple", "Google", "Puzzle",
                LocalDate.parse("April 11 2022", DateTimeFormatter.ofPattern("MMMM d yyyy")), 50.0, 1000,
                "4.4 and up"
            )
        )
        // when calculate number of Apps
        val numberOfApps = appAnalyzer.numberOfAppsDevelopedByGoogle(appList)
        // then check the result
        assertEquals(1, numberOfApps)
    }

    @Test
    fun should_ReturnZero_When_HaveListWithNoAnyGoogleApp() {
        // given list that doesn't contain the word Google
        val appList= mutableListOf<App>()
        appList.add(
            App(
                "Jewel Blast : Temple", "Hutch Games", "Puzzle",
                LocalDate.parse("April 11 2022", DateTimeFormatter.ofPattern("MMMM d yyyy")), 50.0, 1000,
                "4.4 and up"
            )
        )
        // when calculate number of Apps
        val numberOfApps = appAnalyzer.numberOfAppsDevelopedByGoogle(appList)
        // then check the result
       assertEquals(0, numberOfApps)
    }

    @Test
    fun should_ReturnZero_When_HaveEmptyCompanyValue() {
        // given empty company value
        val appList = mutableListOf<App>()
        appList.add(
            App(
                "Jewel Blast : Temple", "", "Puzzle",
                LocalDate.parse("April 11 2022", DateTimeFormatter.ofPattern("MMMM d yyyy")), 50.0, 1000,
                "4.4 and up"
            )
        )
        // when calculate number of Apps
        val numberOfApps = appAnalyzer.numberOfAppsDevelopedByGoogle(appList)
        // then check the result
       assertEquals(0, numberOfApps)
    }

    @Test
    fun should_ReturnZero_When_HaveEmptyAppsList(){
        // given empty apps list
        val appList = mutableListOf<App>()

        // when calculate number of Apps
        val numberOfApps = appAnalyzer.numberOfAppsDevelopedByGoogle(appList)

        // then check the result
       assertEquals(0, numberOfApps)
    }

    // endregion

    // region test functions for medicalAppsPercentage function
    @Test
    fun should_ReturnHundredPercent_When_HaveListOfMedicalAppsOnly() {
        // given list of apps with 100% medical apps
        val appList = mutableListOf<App>()
        appList.add(
            App(
                "Medical Mnemonics", "Regular Rate and Rhythm Software", "Medical",
                LocalDate.parse("May 19 2011", DateTimeFormatter.ofPattern("MMMM d yyyy")), 0.362305, 1000,
                "1.6 and up"
            )
        )
        // when calculate percentage
        val percentage = appAnalyzer.medicalAppsPercentage(appList)
        // then check the result
       assertEquals(100.0, percentage)
    }

    @Test
    fun should_Return33point33Percent_When_MedicalAppsAreOneThirdOfAppsList() {
        // given list of apps with 33.33% medical apps
        val appList = mutableListOf<App>()
        appList.add(
            App(
                "Medical Mnemonics", "Regular Rate and Rhythm Software", "Medical",
                LocalDate.parse("May 19 2011", DateTimeFormatter.ofPattern("MMMM d yyyy")), 0.362305, 1000,
                "1.6 and up"
            )
        )
        appList.add(
            App(
                "FOX 4 Dallas-Fort Worth: Weather", "Fox Television Stations Inc.",
                "Weather", LocalDate.parse("March 27 2022", DateTimeFormatter.ofPattern("MMMM d yyyy")),
                51.0, 2000, "8.0 and up"
            )
        )
        appList.add(
            App(
                "Eyes : Nonogram","GAMEFOX", "Puzzle",
                LocalDate.parse("May 13 2022", DateTimeFormatter.ofPattern("MMMM d yyyy")),
                51.0,1500,"5.0 and up")
        )
        // when calculate percentage
        val percentage = appAnalyzer.medicalAppsPercentage(appList)
        // then check the result
        assertEquals(33.33, percentage)
    }

    @Test
    fun should_ReturnZero_When_NoMedicalAppInTheAppsList() {
        // given list of apps with zero medical apps
        val appList = mutableListOf<App>()
        appList.add(
            App(
                "Medical Mnemonics", "Regular Rate and Rhythm Software", "Photography",
                LocalDate.parse("May 19 2011", DateTimeFormatter.ofPattern("MMMM d yyyy")), 0.362305, 1000,
                "1.6 and up"
            )
        )
        appList.add(
            App(
                "FOX 4 Dallas-Fort Worth: Weather", "Fox Television Stations Inc.",
                "Weather", LocalDate.parse("March 27 2022", DateTimeFormatter.ofPattern("MMMM d yyyy")),
                51.0, 2000, "8.0 and up"
            )
        )
        // when calculate percentage
        val percentage = appAnalyzer.medicalAppsPercentage(appList)
        // then check the result
       assertEquals(0.0, percentage)
    }

    @Test
    fun should_ReturnZero_When_HaveEmptyList() {
        // given empty list
        val appList = mutableListOf<App>()

        // when calculate percentage
        val percentage = appAnalyzer.medicalAppsPercentage(appList)

        // then check the result
       assertEquals(0.0, percentage)
    }

    // endregion

    // region test functions for getOldestApp function
    @Test
    fun should_ReturnFirstApp_When_ListHasOnlyOneApp() {
        // given list has one app
        val appList = mutableListOf<App>()
         appList.add(
            App(
                "Medical Mnemonics", "Regular Rate and Rhythm Software", "Medical",
                LocalDate.parse("May 19 2011", DateTimeFormatter.ofPattern("MMMM d yyyy")), 0.362305, 1000,
                "1.6 and up"
            )
        )
        // when
        val oldestApp = appAnalyzer.getOldestApp(appList)
        // then check the result
       assertEquals(appList[0], oldestApp)
    }

    @Test
    fun should_ReturnOldestApp_When_HaveListWithMultiApps() {
        // given list of apps
        val appList = mutableListOf<App>()
        appList.add(
            App(
                "Jewel Blast : Temple", "", "Puzzle",
                LocalDate.parse("April 11 2022", DateTimeFormatter.ofPattern("MMMM d yyyy")), 50.0, 1000,
                "4.4 and up"
            )
        )
        appList.add(
            App(
                "AD Books", "Daovude", "Libraries & Demo",
                LocalDate.parse("January 10 2021", DateTimeFormatter.ofPattern("MMMM d yyyy")),
                30.0, 5, "5.0 and up"
            )
        )
        appList.add(
            App(
                "myAudi", "Audi", "Auto & Vehicles",
                LocalDate.parse("May 10 2022", DateTimeFormatter.ofPattern("MMMM d yyyy")),
                53.0, 1250, "8.0 and up"
            )
        )
        appList[1]

        // when
        val oldestApp = appAnalyzer.getOldestApp(appList)
        // then check the result
       assertEquals(appList[1], oldestApp)
    }

    @Test
    fun should_ReturnNull_When_HaveEmptyList() {
        // given empty list
        val appList = mutableListOf<App>()
        // when
        val oldestApp = appAnalyzer.getOldestApp(appList)
        // then check the result
        assertNull(oldestApp)
    }

    // endregion

    // region test functions for percentageOfAndroid9AndUp function
    @Test
    fun should_ReturnZero_When_ListHasNotAppRequiredAndroid9AndUp() {
        //given
        val appList = mutableListOf<App>()
        appList.add(
            App(
                "Jewel Blast : Temple", "", "Puzzle",
                LocalDate.parse("April 11 2022", DateTimeFormatter.ofPattern("MMMM d yyyy")), 50.0, 1000,
                "7 and up"
            )
        )

        //when
        val percentage = appAnalyzer.percentageOfAndroid9AndUp(appList)

        //then
        assertEquals(0.0, percentage)
    }

    @Test
    fun should_Return_33point33_When_ListHaveAppsRequiredAndroid9AndUp() {
        //given
        val appList = mutableListOf<App>()
        appList.add(
            App(
                "Jewel Blast : Temple", "", "Puzzle",
                LocalDate.parse("April 11 2022", DateTimeFormatter.ofPattern("MMMM d yyyy")), 50.0, 1000,
                "9 and up"
            )
        )
        appList.add(
            App(
                "Jewel Blast : Temple", "SUPERBOX.Inc", "Puzzle",
                LocalDate.parse("April 11 2022", DateTimeFormatter.ofPattern("MMMM d yyyy")), 0.02, 1000,
                "4.4 and up"
            )
        )
        appList.add(
            App(
                "FOX 4 Dallas-Fort Worth: Weather", "Fox Television Stations Inc.",
                "Weather", LocalDate.parse("March 27 2022", DateTimeFormatter.ofPattern("MMMM d yyyy")),
                98.1, 2000, "8.0 and up"
            )
        )
        //when
        val percentage = appAnalyzer.percentageOfAndroid9AndUp(appList)

        //then
       assertEquals(33.33, percentage)
    }

    @Test
    fun should_ReturnZero_When_ListIsEmpty() {
        //given
        val appList = mutableListOf<App>()

        //when
        val percentage = appAnalyzer.percentageOfAndroid9AndUp(appList)

        //then
        assertEquals(0.0, percentage)
    }

    // endregion

    // region test functions for getLargest10Apps function
    @Test
    fun should_ReturnLargest10Apps_When_TheListOfAppsContainsMoreThan9Apps(){
        // given
        val appList = mutableListOf<App>()
        appList.add(
            App(
                "Jewel Blast : Temple", "SUPERBOX.Inc", "Puzzle",
                LocalDate.parse("April 11 2022", DateTimeFormatter.ofPattern("MMMM d yyyy")), 0.02, 1000,
                "4.4 and up"
            )
        )
        appList.add(
            App(
                "FOX 4 Dallas-Fort Worth: Weather", "Fox Television Stations Inc.",
                "Weather", LocalDate.parse("March 27 2022", DateTimeFormatter.ofPattern("MMMM d yyyy")),
                0.11, 2000, "8.0 and up"
            )
        )
        appList.add(
            App(
                "Eyes : Nonogram", "GAMEFOX", "Puzzle",
                LocalDate.parse("May 13 2022", DateTimeFormatter.ofPattern("MMMM d yyyy")),
                0.23, 1500, "5.0 and up"
            )
        )
        appList.add(
            App(
                "Real Drift Car Racing", "Real Games srls", "Racing",
                LocalDate.parse("March 26 2021", DateTimeFormatter.ofPattern("MMMM d yyyy")),
                0.74, 500, "4.1 and up"
            )
        )
        appList.add(
            App(
                "Slice: Pizza Delivery-Pick Up", "Slice Pizza App", "Food & Drink",
                LocalDate.parse("May 13 2022", DateTimeFormatter.ofPattern("MMMM d yyyy")),
                2.2, 2500, "7.0 and up"
            )
        )
        appList.add(
            App(
                "Angel Saga: Hero Action RPG", "Alchemist Games Inc.", "Action",
                LocalDate.parse("April 1 2020", DateTimeFormatter.ofPattern("MMMM d yyyy")),
                58.0, 200, "5.0 and up"
            )
        )
        appList.add(
            App(
                "Crazy Pusher", "Borg Studio", "Casino",
                LocalDate.parse("February 2 2019", DateTimeFormatter.ofPattern("MMMM d yyyy")),
                112.8, 3000, "4.1 and up"
            )
        )
        appList.add(
            App(
                "Baby Game for 2 3 4 Year Old", "IDZ Digital Private Limited", "Educational",
                LocalDate.parse("December 10 2022", DateTimeFormatter.ofPattern("MMMM d yyyy")),
                532.0, 2800, "5.2 and up"
            )
        )
        appList.add(
            App(
                "Garage Master - games for kids", "KIN GO GAMES FOR KIDS AND TODDLERS", "Educational",
                LocalDate.parse("March 15 2022", DateTimeFormatter.ofPattern("MMMM d yyyy")),
                691.3, 2600, "4.1 and up"
            )
        )
        appList.add(
            App(
                "Manta: Comics & Graphic Novels", "RIDI Corporation", "Comics",
                LocalDate.parse("May 16 2022", DateTimeFormatter.ofPattern("MMMM d yyyy")),
                1150.2, 2300, "5.0 and up"
            )
        )
        appList.add(
            App(
                "Sago Mini School (Kids 2-5)", "Sago Mini", "Education",
                LocalDate.parse("February 24 2022", DateTimeFormatter.ofPattern("MMMM d yyyy")),
                1552.0, 800, "4.4 and up"
            )
        )

        val listOfLargest10Apps = mutableListOf<App>()
        listOfLargest10Apps.add(appList[10])
        listOfLargest10Apps.add(appList[9])
        listOfLargest10Apps.add(appList[8])
        listOfLargest10Apps.add(appList[7])
        listOfLargest10Apps.add(appList[6])
        listOfLargest10Apps.add(appList[5])
        listOfLargest10Apps.add(appList[4])
        listOfLargest10Apps.add(appList[3])
        listOfLargest10Apps.add(appList[2])
        listOfLargest10Apps.add(appList[1])

        // when fined the largest10 app of the list
        val result = appAnalyzer.getLargest10Apps(appList)
        // then
        assertEquals(listOfLargest10Apps, result)
    }

    @Test
    fun should_ReturnAllAppNameOfList_When_TheListOfAppsIsCorrectAndContainsLessThan9Apps() {
        // given list of google play apps have 5 element
        val appList: MutableList<App> = mutableListOf()
        appList.add(
            App(
                "Slice: Pizza Delivery-Pick Up", "Slice Pizza App", "Food & Drink",
                LocalDate.parse("May 13 2022", DateTimeFormatter.ofPattern("MMMM d yyyy")),
                23.2, 2500, "7.0 and up"
            )
        )
        appList.add(
            App(
                "Crazy Pusher", "Borg Studio", "Casino",
                LocalDate.parse("March 25 2022", DateTimeFormatter.ofPattern("MMMM d yyyy")),
                1.3, 3000, "4.1 and up"
            )
        )
        appList.add(
            App(
                "Baby Game for 2 3 4 Year Old", "IDZ Digital Private Limited", "Educational",
                LocalDate.parse("March 9 2022", DateTimeFormatter.ofPattern("MMMM d yyyy")),
                0.52, 2800, "5.2 and up"
            )
        )
        appList.add(
            App(
                "Garage Master - games for kids", "KIN GO GAMES FOR KIDS AND TODDLERS", "Educational",
                LocalDate.parse("March 15 2022", DateTimeFormatter.ofPattern("MMMM d yyyy")),
                52.0, 2600, "4.1 and up"
            )
        )
        appList.add(
            App(
                "Dinosaur Airport:Game for kids", "Yateland - Learning Games For Kids", "Educational",
                LocalDate.parse("May 18 2022", DateTimeFormatter.ofPattern("MMMM d yyyy")),
                762.3, 4000, "4.1 and up"
            )
        )

        // when fined the largest10 app of list
        val result = appAnalyzer.findLargest10App(appList)
        // then
        assertEquals(
            mutableListOf(
                "Dinosaur Airport:Game for kids",
                "Garage Master - games for kids",
                "Slice: Pizza Delivery-Pick Up",
                "Crazy Pusher",
                "Baby Game for 2 3 4 Year Old"
            ), result
        )
    }

    @Test
    fun should_ReturnNullValueAndNoLargestApp_When_TheListOfAppsIsEmpty() {
        // given empty list of google play apps
        val appList : MutableList<App> = mutableListOf()

        // when find the top installed apps name
        val result = appAnalyzer.findLargest10App(appList)
        // then
        Assertions.assertNull(result)
    }

    // endregion

//    // region test functions for findTop10InstalledApps function
//    @Test
//    fun should_ReturnTop10InstalledAppName_When_TheListOfAppsIsCorrectAndContainsMoreThan9Apps() {
//        // given list of google play apps have 15 element
//        val appList : MutableList<App> = mutableListOf()
//        appList.add(App("Jewel Blast : Temple", "SUPERBOX.Inc", "Puzzle",
//            LocalDate.parse("April 11 2022", DateTimeFormatter.ofPattern("MMMM d yyyy")), 50.0,1000,
//            "4.4 and up"))
//        appList.add(App("FOX 4 Dallas-Fort Worth: Weather", "Fox Television Stations Inc.",
//            "Weather", LocalDate.parse("March 27 2022", DateTimeFormatter.ofPattern("MMMM d yyyy")),
//            51.0,2000,"8.0 and up"))
//        appList.add(App("Eyes : Nonogram","GAMEFOX", "Puzzle",
//            LocalDate.parse("May 13 2022", DateTimeFormatter.ofPattern("MMMM d yyyy")),
//            51.0,1500,"5.0 and up"))
//        appList.add(App("Real Drift Car Racing","Real Games srls","Racing",
//            LocalDate.parse("March 26 2021", DateTimeFormatter.ofPattern("MMMM d yyyy")),
//            51.0,500,"4.1 and up"))
//        appList.add(App("Slice: Pizza Delivery-Pick Up","Slice Pizza App","Food & Drink",
//            LocalDate.parse("May 13 2022", DateTimeFormatter.ofPattern("MMMM d yyyy")),
//            51.0,2500,"7.0 and up"))
//        appList.add(App("Angel Saga: Hero Action RPG","Alchemist Games Inc.","Action",
//            LocalDate.parse("May 13 2022", DateTimeFormatter.ofPattern("MMMM d yyyy")),
//            58.0,200,"5.0 and up"))
//        appList.add(App("Crazy Pusher","Borg Studio","Casino",
//            LocalDate.parse("March 25 2022", DateTimeFormatter.ofPattern("MMMM d yyyy")),
//            52.0,3000,"4.1 and up"))
//        appList.add(App("Baby Game for 2 3 4 Year Old","IDZ Digital Private Limited","Educational",
//            LocalDate.parse("March 9 2022", DateTimeFormatter.ofPattern("MMMM d yyyy")),
//            52.0,2800,"5.2 and up"))
//        appList.add(App("Garage Master - games for kids","KIN GO GAMES FOR KIDS AND TODDLERS","Educational",
//            LocalDate.parse("March 15 2022", DateTimeFormatter.ofPattern("MMMM d yyyy")),
//            52.0,2600,"4.1 and up"))
//        appList.add(App("Manta: Comics & Graphic Novels","RIDI Corporation","Comics",
//            LocalDate.parse("May 16 2022", DateTimeFormatter.ofPattern("MMMM d yyyy")),
//            52.0,2300,"5.0 and up"))
//        appList.add(App("Sago Mini School (Kids 2-5)","Sago Mini","Education",
//            LocalDate.parse("February 24 2022", DateTimeFormatter.ofPattern("MMMM d yyyy")),
//            52.0,800,"4.4 and up"))
//        appList.add(App("Rolling Luck: Win Real Money","Shape Keeper Ltd","Casino",
//            LocalDate.parse("May 18 2022", DateTimeFormatter.ofPattern("MMMM d yyyy")),
//            52.0,1100,"5.0 and up"))
//        appList.add(App("StyleSeat: Book Hair & Beauty","Styleseat","Beauty",
//            LocalDate.parse("May 18 2022", DateTimeFormatter.ofPattern("MMMM d yyyy")),
//            54.0,2300,"5.1 and up"))
//        appList.add(App("Dinosaur Airport:Game for kids","Yateland - Learning Games For Kids","Educational",
//            LocalDate.parse("May 18 2022", DateTimeFormatter.ofPattern("MMMM d yyyy")),
//            21.0,4000,"4.1 and up"))
//        appList.add(App("myAudi","Audi","Auto & Vehicles",
//            LocalDate.parse("May 10 2022", DateTimeFormatter.ofPattern("MMMM d yyyy")),
//            53.0,1250,"8.0 and up"))
//
//        // when find the top 10 installed apps name
//        val result = appAnalyzer.findTop10InstalledApps(appList)
//        // then
//        Assertions.assertEquals(mutableListOf("Dinosaur Airport:Game for kids",
//            "Crazy Pusher",
//            "Baby Game for 2 3 4 Year Old",
//            "Garage Master - games for kids",
//            "Slice: Pizza Delivery-Pick Up",
//            "Manta: Comics & Graphic Novels",
//            "StyleSeat: Book Hair & Beauty",
//            "FOX 4 Dallas-Fort Worth: Weather",
//            "Eyes : Nonogram",
//            "myAudi"), result)
//    }
//
//    @Test
//    fun should_ReturnAllElementAppName_When_TheListOfAppsIsCorrectAndContainsLiseThan10Apps() {
//        // given list of google play apps have 5 element
//        val appList : MutableList<App> = mutableListOf()
//        appList.add(App("Slice: Pizza Delivery-Pick Up","Slice Pizza App","Food & Drink",
//            LocalDate.parse("May 13 2022", DateTimeFormatter.ofPattern("MMMM d yyyy")),
//            51.0,2500,"7.0 and up"))
//        appList.add(App("Crazy Pusher","Borg Studio","Casino",
//            LocalDate.parse("March 25 2022", DateTimeFormatter.ofPattern("MMMM d yyyy")),
//            52.0,3000,"4.1 and up"))
//        appList.add(App("Baby Game for 2 3 4 Year Old","IDZ Digital Private Limited","Educational",
//            LocalDate.parse("March 9 2022", DateTimeFormatter.ofPattern("MMMM d yyyy")),
//            52.0,2800,"5.2 and up"))
//        appList.add(App("Garage Master - games for kids","KIN GO GAMES FOR KIDS AND TODDLERS","Educational",
//            LocalDate.parse("March 15 2022", DateTimeFormatter.ofPattern("MMMM d yyyy")),
//            52.0,2600,"4.1 and up"))
//        appList.add(App("Dinosaur Airport:Game for kids","Yateland - Learning Games For Kids","Educational",
//            LocalDate.parse("May 18 2022", DateTimeFormatter.ofPattern("MMMM d yyyy")),
//            21.0,4000,"4.1 and up"))
//
//        // when find the top installed apps name
//        val result = appAnalyzer.findTop10InstalledApps(appList)
//        // then
//        Assertions.assertEquals(mutableListOf("Dinosaur Airport:Game for kids",
//            "Crazy Pusher",
//            "Baby Game for 2 3 4 Year Old",
//            "Garage Master - games for kids",
//            "Slice: Pizza Delivery-Pick Up"), result)
//    }
//
//    @Test
//    fun should_ReturnNullValueAndNoTopInstalled_When_TheListOfAppsIsEmpty() {
//        // given empty list of google play apps
//        val appList : MutableList<App> = mutableListOf()
//
//        // when find the top installed apps name
//        val result = appAnalyzer.findTop10InstalledApps(appList)
//        // then
//        Assertions.assertNull(result)
//    }
//
//    // endregion

}