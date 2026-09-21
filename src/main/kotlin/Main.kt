import java.io.File

fun main(args: Array<String>) {
    if (args.isEmpty()) {
        println("Usage: ./run [--tokenize | --parse] <path>")
        return
    }

    val mode: String
    val path: String

    // check if mode is specified
    if (args[0] == "--tokenize" || args[0] == "--parse") {
        // check if too many arguments
        if (args.size < 2) {
            println("Usage: ./run ${args[0]} <path>")
            return
        }


        mode = args[0]
        path = args[1]
    } else {
        mode = "default"
        path = args[0]
    }

    val source = File(path).readText()

    val scanner = Scanner(source)
    val tokens = scanner.scanTokens()

    when (mode) {
        "default", "--tokenize" -> {
            for (token in tokens) {
                println(token)
            }
        }

        "--parse" -> {
            // Parser goes here later
        }
    }
}