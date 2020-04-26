package no.strooped.util

fun Int.pluralize(): String {
    val suffix: String = if (this > 10 && ((this % 10) in (1..3)) && ((this / 10) % 10 == 1)) {
        "th"
    } else {
        when (this) {
            1 -> "st"
            2 -> "nd"
            3 -> "rd"
            else -> "th"
        }
    }

    return "$this$suffix place"
}
