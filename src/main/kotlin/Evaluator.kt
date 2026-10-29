class Evaluator {

    // Stores variable names and their current values.
    private val environment = mutableMapOf<String, Any?>()

    fun evaluate(expr: Expr): Any? {
        return when (expr) {
            // Return the value directly for literals.
            is Expr.Literal -> expr.value

            // Evaluate the expression inside the parentheses.
            is Expr.Grouping ->
                evaluate(expr.expression)

            // Evaluate unary operators such as - and !.
            is Expr.Unary ->
                evaluateUnary(expr)

            // Evaluate binary operators such as +, -, and ==.
            is Expr.Binary ->
                evaluateBinary(expr)

            // Retrieve the value of an existing variable.
            is Expr.Variable -> {
                val name = expr.name.lexeme

                if (!environment.containsKey(name)) {
                    throw EvaluationError(
                        expr.name.line,
                        "Undefined variable '$name'."
                    )
                }

                environment[name]
            }

            // Evaluate the assigned value and store it.
            is Expr.Assign -> {
                val value = evaluate(expr.value)
                environment[expr.name.lexeme] = value
                value
            }
        }
    }

    private fun evaluateUnary(expr: Expr.Unary): Any? {
        val right = evaluate(expr.right)

        return when (expr.operator.type) {
            // Negate a number.
            TokenType.MINUS -> {
                if (right !is Double) {
                    runtimeError(expr.operator, "Operand must be a number.")
                }
                -right
            }

            // Reverse a boolean value.
            TokenType.BANG -> {
                if (right !is Boolean) {
                    runtimeError(expr.operator, "Operand must be a boolean.")
                }
                !right
            }

            else -> runtimeError(expr.operator, "Unsupported unary operator.")
        }
    }

    private fun evaluateBinary(expr: Expr.Binary): Any? {
        // Evaluate both operands before applying the operator.
        val left = evaluate(expr.left)
        val right = evaluate(expr.right)

        return when (expr.operator.type) {
            // Add numbers or concatenate if either operand is a string.
            TokenType.PLUS -> {
                if (left is String || right is String) {
                    stringify(left) + stringify(right)
                } else {
                    val (l, r) = requireNumbers(expr.operator, left, right)
                    l + r
                }
            }

            TokenType.MINUS -> {
                val (l, r) = requireNumbers(expr.operator, left, right)
                l - r
            }

            TokenType.STAR -> {
                val (l, r) = requireNumbers(expr.operator, left, right)
                l * r
            }

            TokenType.SLASH -> {
                val (l, r) = requireNumbers(expr.operator, left, right)

                if (r == 0.0) {
                    runtimeError(expr.operator, "Division by zero.")
                }

                l / r
            }

            // Comparison operators require numeric operands.
            TokenType.GREATER -> {
                val (l, r) = requireNumbers(expr.operator, left, right)
                l > r
            }

            TokenType.GREATER_EQUAL -> {
                val (l, r) = requireNumbers(expr.operator, left, right)
                l >= r
            }

            TokenType.LESS -> {
                val (l, r) = requireNumbers(expr.operator, left, right)
                l < r
            }

            TokenType.LESS_EQUAL -> {
                val (l, r) = requireNumbers(expr.operator, left, right)
                l <= r
            }

            // Equality compares values; inequality returns the opposite result.
            TokenType.EQUAL_EQUAL ->
                isEqual(left, right)

            TokenType.BANG_EQUAL ->
                !isEqual(left, right)

            else -> runtimeError(expr.operator, "Unsupported binary operator.")
        }
    }

    // Ensures both operands are numbers before performing arithmetic.
    private fun requireNumbers(
        operator: Token,
        left: Any?,
        right: Any?
    ): Pair<Double, Double> {
        if (left !is Double || right !is Double) {
            runtimeError(operator, "Operands must be numbers.")
        }

        return Pair(left, right)
    }

    // Values of different types are unequal; two null values are equal.
    private fun isEqual(left: Any?, right: Any?): Boolean {
        if (left == null && right == null) return true
        if (left == null || right == null) return false
        if (left::class != right::class) return false

        return left == right
    }

    // Converts values to the format used for printing and string concatenation.
    fun stringify(value: Any?): String {
        return when (value) {
            null -> "null"
            is Boolean -> value.toString()
            is String -> value
            is Double -> {
                if (value.isFinite() && value == Math.floor(value)) {
                    value.toLong().toString()
                } else {
                    value.toString()
                }
            }
            else -> value.toString()
        }
    }

    // Reports a runtime error with the operator's source line.
    private fun runtimeError(
        token: Token,
        message: String
    ): Nothing {
        throw EvaluationError(token.line, message)
    }
}

// Carries the source line and message for an evaluation error.
class EvaluationError(
    val line: Int,
    message: String
) : RuntimeException(message)