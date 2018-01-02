package kotlinsamples.helloworld

/**
 * Created by ray on 18-1-2.
 */
fun getMnemonic(color: Color) =
        when (color) {
            Color.RED -> "Richard"
            Color.ORANGE -> "Of"
            Color.YELLOW -> "York"
            Color.GREEN -> "Gave"
            Color.BLUE -> "Battle"
            Color.INDIGO -> "In"
            Color.VIOLET -> "Vain"
        }

fun getWarmth(color: Color) = when (color) {
    Color.RED, Color.ORANGE, Color.YELLOW -> "warm"
    Color.GREEN -> "neutral"
    Color.VIOLET, Color.INDIGO, Color.BLUE -> "cold"
}

fun main(args: Array<String>) {
    println(getMnemonic(Color.VIOLET))
    println(getWarmth(Color.BLUE))
}
