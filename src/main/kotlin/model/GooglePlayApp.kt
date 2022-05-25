package model

import java.util.Date

class GooglePlayApp(
     var appName: String,
     var company: String,
     var category: String,
     var updated: Date,
     var size: Double,
     var installs: Long,
     var requiresAndroid: Double,
) {
}