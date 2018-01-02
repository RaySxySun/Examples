package kotlinsamples.helloworld

/**
 * Created by ray on 18-1-2.
 */

//fun max_concise(a: Int, b: Int):Int = if (a > b) a else b
fun max_concise(a: Int, b: Int) = if (a > b) a else b

fun main(args: Array<String>) {
    println(max_concise(1, 2))
}
