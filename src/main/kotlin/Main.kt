import java.io.File

fun main(args: Array<String>) {
    if (args.isEmpty()) {
        println("Usage: ./run <path>")
        return
    }

    val source = File(args[0]).readText()

    val scanner = Scanner(source)
    val tokens = scanner.scanTokens()

    for (token in tokens) {
        println(token)
    }
}