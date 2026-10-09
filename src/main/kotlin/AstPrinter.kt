class AstPrinter {

    fun print(expr: Expr): String {
        return when (expr) {

            is Expr.Literal -> {
                expr.value.toString()
            }

            is Expr.Grouping -> {
                parenthesize("group", expr.expression)
            }

            is Expr.Unary -> {
                parenthesize(expr.operator.lexeme, expr.right)
            }

            is Expr.Binary -> {
                parenthesize(
                    expr.operator.lexeme,
                    expr.left,
                    expr.right
                )
            }

            is Expr.Variable -> {
                expr.name.lexeme
            }

            is Expr.Assign -> {
                parenthesize(
                    "=",
                    Expr.Variable(expr.name),
                    expr.value
                )
            }
        }
    }

    private fun parenthesize(name: String, vararg exprs: Expr): String {
        val result = StringBuilder()

        result.append("(")
        result.append(name)

        for (expr in exprs) {
            result.append(" ")
            result.append(print(expr))
        }

        result.append(")")

        return result.toString()
    }
}