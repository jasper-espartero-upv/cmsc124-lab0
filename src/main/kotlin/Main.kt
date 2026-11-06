
import java.io.File

fun main(args: Array<String>) {
    if (args.isEmpty()) {
        val evaluator = Evaluator()

        while (true) {
            print("> ")
            val line = readLine() ?: break

            if (line.isBlank()) {
                continue
            }

            val scanner = Scanner(line)
            val tokens = scanner.scanTokens()

            if (scanner.hadError) {
                continue
            }

            val statements = parser.parse()

            for (statement in statements) {
                try {
                    evaluator.execute(statement)
                } catch (error: EvaluationError) {
                    System.err.println(
                        "[line ${error.line}] RuntimeError: ${error.message}"
                    )
                }
            }
        }

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
            val statements = parser.parse()
            val printer = AstPrinter()

            for (statement in statements) {
                println(printer.print(statement))
            }
        }

        // --eval evaluates each expression and prints its result.
        "--eval" -> {
            if (scanner.hadError) {
                kotlin.system.exitProcess(65)
            }

            val parser = Parser(tokens)
            val statements = parser.parse()
            val evaluator = Evaluator()

            for (statement in statements) {
                try {
                    evaluator.execute(statement)
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
