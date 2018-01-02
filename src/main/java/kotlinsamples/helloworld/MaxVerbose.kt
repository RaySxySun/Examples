package kotlinsamples.helloworld

/**
 * Created by ray on 18-1-2.
 */

fun max_verbose(a: Int, b: Int):Int {
    return if (a > b) a else b
}

fun main(args: Array<String>) {
    println(max_verbose(1, 2))
}
