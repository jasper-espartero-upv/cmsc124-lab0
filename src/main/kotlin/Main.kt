
import java.io.File

fun main(args: Array<String>) {
    if (args.isEmpty()) {
        println("Usage: ./run [--tokenize | --parse | --eval] <path>")
        return
    }

    val mode: String
    val path: String

    // Check if a mode is specified.
    if (args[0] == "--tokenize" ||
        args[0] == "--parse" ||
        args[0] == "--eval"
    ) {
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
        // Default mode and --tokenize print the tokens.
        "default", "--tokenize" -> {
            for (token in tokens) {
                println(token)
            }

            if (scanner.hadError) {
                kotlin.system.exitProcess(65)
            }
        }

        // --parse prints the AST representation of each expression.
        "--parse" -> {
            if (scanner.hadError) {
                kotlin.system.exitProcess(65)
            }

            val parser = Parser(tokens)
            val expressions = parser.parse()
            val printer = AstPrinter()

            for (expression in expressions) {
                println(printer.print(expression))
            }
        }

        // --eval evaluates each expression and prints its result.
        "--eval" -> {
            if (scanner.hadError) {
                kotlin.system.exitProcess(65)
            }

            val parser = Parser(tokens)
            val expressions = parser.parse()
            val evaluator = Evaluator()

            for (expression in expressions) {
                try {
                    val result = evaluator.evaluate(expression)
                    println(evaluator.stringify(result))
                } catch (error: EvaluationError) {
                    System.err.println(
                        "[line ${error.line}] RuntimeError: ${error.message}"
                    )
                    kotlin.system.exitProcess(70)
                }
            }
        }
    }
}
