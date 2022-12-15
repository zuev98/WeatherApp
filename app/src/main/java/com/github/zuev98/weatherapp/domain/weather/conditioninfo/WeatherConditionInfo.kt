package com.github.zuev98.weatherapp.domain.weather.conditioninfo

import androidx.annotation.DrawableRes
import com.github.zuev98.weatherapp.R

//Icons and conditions corresponding to the code received from the server
sealed class WeatherConditionInfo(
    val condition: String,
    @DrawableRes val iconRes: Int
) {
    object Sunny : WeatherConditionInfo("Sunny", R.drawable.d113)
    object Clear : WeatherConditionInfo("Clear", R.drawable.n113)

    object PartlyCloudyDay : WeatherConditionInfo("Partly cloudy", R.drawable.d116)
    object PartlyCloudyNight : WeatherConditionInfo("Partly cloudy", R.drawable.n116)

    object CloudyDay : WeatherConditionInfo("Cloudy", R.drawable.d119)
    object CloudyNight : WeatherConditionInfo("Cloudy", R.drawable.n119)

    object OvercastDay : WeatherConditionInfo("Overcast", R.drawable.d122)
    object OvercastNight : WeatherConditionInfo("Overcast", R.drawable.n122)

    object MistDay : WeatherConditionInfo("Mist", R.drawable.d143)
    object MistNight : WeatherConditionInfo("Mist", R.drawable.n143)

    object PatchyRainPossibleDay : WeatherConditionInfo("Patchy rain possible", R.drawable.d176)
    object PatchyRainPossibleNight : WeatherConditionInfo("Patchy rain possible", R.drawable.n176)

    object PatchySnowPossibleDay : WeatherConditionInfo("Patchy snow possible", R.drawable.d179)
    object PatchySnowPossibleNight : WeatherConditionInfo("Patchy snow possible", R.drawable.n179)

    object PatchySleetPossibleDay : WeatherConditionInfo("Patchy sleet possible", R.drawable.d182)
    object PatchySleetPossibleNight : WeatherConditionInfo("Patchy sleet possible", R.drawable.n182)

    object PatchyFreezingDrizzlePossibleDay :
        WeatherConditionInfo("Patchy freezing drizzle possible", R.drawable.d185)
    object PatchyFreezingDrizzlePossibleNight :
        WeatherConditionInfo("Patchy freezing drizzle possible", R.drawable.n185)

    object ThunderyOutbreaksPossibleDay :
        WeatherConditionInfo("Thundery outbreaks possible", R.drawable.d200)
    object ThunderyOutbreaksPossibleNight :
        WeatherConditionInfo("Thundery outbreaks possible", R.drawable.n200)

    object BlowingSnowDay : WeatherConditionInfo("Blowing snow", R.drawable.d227)
    object BlowingSnowNight : WeatherConditionInfo("Blowing snow", R.drawable.n227)

    object BlizzardDay : WeatherConditionInfo("Blizzard", R.drawable.d230)
    object BlizzardNight : WeatherConditionInfo("Blizzard", R.drawable.n230)

    object FogDay : WeatherConditionInfo("Fog", R.drawable.d248)
    object FogNight : WeatherConditionInfo("Fog", R.drawable.n248)

    object FreezingFogDay : WeatherConditionInfo("Freezing fog", R.drawable.d260)
    object FreezingFogNight : WeatherConditionInfo("Freezing fog", R.drawable.n260)

    object PatchyLightDrizzleDay : WeatherConditionInfo("Patchy light drizzle", R.drawable.d263)
    object PatchyLightDrizzleNight : WeatherConditionInfo("Patchy light drizzle", R.drawable.n263)

    object LightDrizzleDay : WeatherConditionInfo("Light drizzle", R.drawable.d266)
    object LightDrizzleNight : WeatherConditionInfo("Light drizzle", R.drawable.n266)

    object FreezingDrizzleDay : WeatherConditionInfo("Freezing drizzle", R.drawable.d281)
    object FreezingDrizzleNight : WeatherConditionInfo("Freezing drizzle", R.drawable.n281)

    object HeavyFreezingDrizzleDay : WeatherConditionInfo("Heavy freezing drizzle", R.drawable.d284)
    object HeavyFreezingDrizzleNight : WeatherConditionInfo("Heavy freezing drizzle", R.drawable.n284)

    object PatchyLightRainDay : WeatherConditionInfo("Patchy light rain", R.drawable.d293)
    object PatchyLightRainNight : WeatherConditionInfo("Patchy light rain", R.drawable.n293)

    object LightRainDay : WeatherConditionInfo("Light rain", R.drawable.d296)
    object LightRainNight : WeatherConditionInfo("Light rain", R.drawable.n296)

    object ModerateRainAtTimesDay : WeatherConditionInfo("Moderate rain at times", R.drawable.d299)
    object ModerateRainAtTimesNight : WeatherConditionInfo("Moderate rain at times", R.drawable.n299)

    object ModerateRainDay : WeatherConditionInfo("Moderate rain", R.drawable.d302)
    object ModerateRainNight : WeatherConditionInfo("Moderate rain", R.drawable.n302)

    object HeavyRainAtTimesDay : WeatherConditionInfo("Heavy rain at times", R.drawable.d305)
    object HeavyRainAtTimesNight : WeatherConditionInfo("Heavy rain at times", R.drawable.n305)

    object HeavyRainDay : WeatherConditionInfo("Heavy rain", R.drawable.d308)
    object HeavyRainNight : WeatherConditionInfo("Heavy rain", R.drawable.n308)

    object LightFreezingRainDay : WeatherConditionInfo("Light freezing rain", R.drawable.d311)
    object LightFreezingRainNight : WeatherConditionInfo("Light freezing rain", R.drawable.n311)

    object ModerateOrHeavyFreezingRainDay :
        WeatherConditionInfo("Moderate or heavy freezing rain", R.drawable.d314)
    object ModerateOrHeavyFreezingRainNight :
        WeatherConditionInfo("Moderate or heavy freezing rain", R.drawable.n314)

    object LightSleetDay : WeatherConditionInfo("Light sleet", R.drawable.d317)
    object LightSleetNight : WeatherConditionInfo("Light sleet", R.drawable.n317)

    object ModerateOrHeavySleetDay : WeatherConditionInfo("Moderate or heavy sleet", R.drawable.d320)
    object ModerateOrHeavySleetNight : WeatherConditionInfo("Moderate or heavy sleet", R.drawable.n320)

    object PatchyLightSnowDay : WeatherConditionInfo("Patchy light snow", R.drawable.d323)
    object PatchyLightSnowNight : WeatherConditionInfo("Patchy light snow", R.drawable.n323)

    object LightSnowDay : WeatherConditionInfo("Light snow", R.drawable.d326)
    object LightSnowNight : WeatherConditionInfo("Light snow", R.drawable.n326)

    object PatchyModerateSnowDay : WeatherConditionInfo("Patchy moderate snow", R.drawable.d329)
    object PatchyModerateSnowNight : WeatherConditionInfo("Patchy moderate snow", R.drawable.n329)

    object ModerateSnowDay : WeatherConditionInfo("Moderate snow", R.drawable.d332)
    object ModerateSnowNight : WeatherConditionInfo("Moderate snow", R.drawable.n332)

    object PatchyHeavySnowDay : WeatherConditionInfo("Patchy heavy snow", R.drawable.d335)
    object PatchyHeavySnowNight : WeatherConditionInfo("Patchy heavy snow", R.drawable.n335)

    object HeavySnowDay : WeatherConditionInfo("Heavy snow", R.drawable.d338)
    object HeavySnowNight : WeatherConditionInfo("Heavy snow", R.drawable.n338)

    object IcePelletsDay : WeatherConditionInfo("Ice pellets", R.drawable.d350)
    object IcePelletsNight : WeatherConditionInfo("Ice pellets", R.drawable.n350)

    object LightRainShowerDay : WeatherConditionInfo("Light rain shower", R.drawable.d353)
    object LightRainShowerNight : WeatherConditionInfo("Light rain shower", R.drawable.n353)

    object ModerateOrHeavyRainShowerDay :
        WeatherConditionInfo("Moderate or heavy rain shower", R.drawable.d356)
    object ModerateOrHeavyRainShowerNight :
        WeatherConditionInfo("Moderate or heavy rain shower", R.drawable.n356)

    object TorrentialRainShowerDay : WeatherConditionInfo("Torrential rain shower", R.drawable.d359)
    object TorrentialRainShowerNight : WeatherConditionInfo("Torrential rain shower", R.drawable.n359)

    object LightSleetShowersDay : WeatherConditionInfo("Light sleet showers", R.drawable.d362)
    object LightSleetShowersNight : WeatherConditionInfo("Light sleet showers", R.drawable.n362)

    object ModerateOrHeavySleetShowersDay :
        WeatherConditionInfo("Moderate or heavy sleet showers", R.drawable.d365)
    object ModerateOrHeavySleetShowersNight :
        WeatherConditionInfo("Moderate or heavy sleet showers", R.drawable.n365)

    object LightSnowShowersDay : WeatherConditionInfo("Light snow showers", R.drawable.d368)
    object LightSnowShowersNight : WeatherConditionInfo("Light snow showers", R.drawable.n368)

    object ModerateOrHeavySnowShowersDay :
        WeatherConditionInfo("Moderate or heavy snow showers", R.drawable.d371)
    object ModerateOrHeavySnowShowersNight :
        WeatherConditionInfo("Moderate or heavy snow showers", R.drawable.n371)

    object LightShowersOfIcePelletsDay :
        WeatherConditionInfo("Light showers of ice pellets", R.drawable.d374)
    object LightShowersOfIcePelletsNight :
        WeatherConditionInfo("Light showers of ice pellets", R.drawable.n374)

    object ModerateOrHeavyShowersOfIcePelletsDay :
        WeatherConditionInfo("Moderate or heavy showers of ice pellets", R.drawable.d377)
    object ModerateOrHeavyShowersOfIcePelletsNight :
        WeatherConditionInfo("Moderate or heavy showers of ice pellets", R.drawable.n377)

    object PatchyLightRainWithThunderDay :
        WeatherConditionInfo("Patchy light rain with thunder", R.drawable.d386)
    object PatchyLightRainWithThunderNight :
        WeatherConditionInfo("Patchy light rain with thunder", R.drawable.n386)

    object ModerateOrHeavyRainWithThunderDay :
        WeatherConditionInfo("Moderate or heavy rain with thunder", R.drawable.d389)
    object ModerateOrHeavyRainWithThunderNight :
        WeatherConditionInfo("Moderate or heavy rain with thunder", R.drawable.n389)

    object PatchyLightSnowWithThunderDay :
        WeatherConditionInfo("Patchy light snow with thunder", R.drawable.d392)
    object PatchyLightSnowWithThunderNight :
        WeatherConditionInfo("Patchy light snow with thunder", R.drawable.n392)

    object ModerateOrHeavySnowWithThunderDay :
        WeatherConditionInfo("Moderate or heavy snow with thunder", R.drawable.d395)
    object ModerateOrHeavySnowWithThunderNight :
        WeatherConditionInfo("Moderate or heavy snow with thunder", R.drawable.n395)

    companion object {
        fun getWeatherConditionInfo(code: Int, isDay: Int = 1): WeatherConditionInfo {
            return when(code) {
                1000 -> if (isDay == 1) Sunny else Clear
                1003 -> if (isDay == 1) PartlyCloudyDay else PartlyCloudyNight
                1006 -> if (isDay == 1) CloudyDay else CloudyNight
                1009 -> if (isDay == 1) OvercastDay else OvercastNight
                1030 -> if (isDay == 1) MistDay else MistNight
                1063 -> if (isDay == 1) PatchyRainPossibleDay else PatchyRainPossibleNight
                1066 -> if (isDay == 1) PatchySnowPossibleDay else PatchySnowPossibleNight
                1069 -> if (isDay == 1) PatchySleetPossibleDay else PatchySleetPossibleNight
                1072 ->
                    if (isDay == 1) PatchyFreezingDrizzlePossibleDay
                    else PatchyFreezingDrizzlePossibleNight
                1087 ->
                    if (isDay == 1) ThunderyOutbreaksPossibleDay else ThunderyOutbreaksPossibleNight
                1114 -> if (isDay == 1) BlowingSnowDay else BlowingSnowNight
                1117 -> if (isDay == 1) BlizzardDay else BlizzardNight
                1135 -> if (isDay == 1) FogDay else FogNight
                1147 -> if (isDay == 1) FreezingFogDay else FreezingFogNight
                1150 -> if (isDay == 1) PatchyLightDrizzleDay else PatchyLightDrizzleNight
                1153 -> if (isDay == 1) LightDrizzleDay else LightDrizzleNight
                1168 -> if (isDay == 1) FreezingDrizzleDay else FreezingDrizzleNight
                1171 -> if (isDay == 1) HeavyFreezingDrizzleDay else HeavyFreezingDrizzleNight
                1180 -> if (isDay == 1) PatchyLightRainDay else PatchyLightRainNight
                1183 -> if (isDay == 1) LightRainDay else LightRainNight
                1186 -> if (isDay == 1) ModerateRainAtTimesDay else ModerateRainAtTimesNight
                1189 -> if (isDay == 1) ModerateRainDay else ModerateRainNight
                1192 -> if (isDay == 1) HeavyRainAtTimesDay else HeavyRainAtTimesNight
                1195 -> if (isDay == 1) HeavyRainDay else HeavyRainNight
                1198 -> if (isDay == 1) LightFreezingRainDay else LightFreezingRainNight
                1201 ->
                    if (isDay == 1) ModerateOrHeavyFreezingRainDay
                    else ModerateOrHeavyFreezingRainNight
                1204 -> if (isDay == 1) LightSleetDay else LightSleetNight
                1207 -> if (isDay == 1) ModerateOrHeavySleetDay else ModerateOrHeavySleetNight
                1210 -> if (isDay == 1) PatchyLightSnowDay else PatchyLightSnowNight
                1213 -> if (isDay == 1) LightSnowDay else LightSnowNight
                1216 -> if (isDay == 1) PatchyModerateSnowDay else PatchyModerateSnowNight
                1219 -> if (isDay == 1) ModerateSnowDay else ModerateSnowNight
                1222 -> if (isDay == 1) PatchyHeavySnowDay else PatchyHeavySnowNight
                1225 -> if (isDay == 1) HeavySnowDay else HeavySnowNight
                1237 -> if (isDay == 1) IcePelletsDay else IcePelletsNight
                1240 -> if (isDay == 1) LightRainShowerDay else LightRainShowerNight
                1246 ->
                    if (isDay == 1) ModerateOrHeavyRainShowerDay else ModerateOrHeavyRainShowerNight
                1243 -> if (isDay == 1) TorrentialRainShowerDay else TorrentialRainShowerNight
                1249 -> if (isDay == 1) LightSleetShowersDay else LightSleetShowersNight
                1252 ->
                    if (isDay == 1) ModerateOrHeavySleetShowersDay
                    else ModerateOrHeavySleetShowersNight
                1255 -> if (isDay == 1) LightSnowShowersDay else LightSnowShowersNight
                1258 ->
                    if (isDay == 1) ModerateOrHeavySnowShowersDay
                    else ModerateOrHeavySnowShowersNight
                1261 ->
                    if (isDay == 1) LightShowersOfIcePelletsDay else LightShowersOfIcePelletsNight
                1264 ->
                    if (isDay == 1) ModerateOrHeavyShowersOfIcePelletsDay
                    else ModerateOrHeavyShowersOfIcePelletsNight
                1273 ->
                    if (isDay == 1) PatchyLightRainWithThunderDay
                    else PatchyLightRainWithThunderNight
                1276 ->
                    if (isDay == 1) ModerateOrHeavyRainWithThunderDay
                    else ModerateOrHeavyRainWithThunderNight
                1279 ->
                    if (isDay == 1) PatchyLightSnowWithThunderDay
                    else PatchyLightSnowWithThunderNight
                1282 ->
                    if (isDay == 1) ModerateOrHeavySnowWithThunderDay
                    else ModerateOrHeavySnowWithThunderNight
                else -> Sunny
            }
        }
    }
}
