package kotlinsamples.helloworld

/**
 * Created by ray on 18-1-2.
 */
class Person (
    val name: String,
    var isMarried: Boolean
)

class Rectangle(val height: Int, val width: Int) {
    val isSquare: Boolean
        get() {return height == width}
}

fun main(args: Array<String>) {
    val person = Person("Bob", true)
    println(person.name)
    println(person.isMarried)

    val rectangle = Rectangle(12, 10)
    println(rectangle.isSquare)

    val square = Rectangle(10, 10)
    println(square.isSquare)
}
