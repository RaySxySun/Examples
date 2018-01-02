package kotlinsamples.helloworld

/**
 * Created by ray on 18-1-2.
 */

fun main(args: Array<String>) {
    val name = if (args.size > 0) args[0] else "Kotlin"
//    println("Hello, $name!")
    println("Hello, ${if (args.size > 0) args[0] else "Kotlin"}!")
}
