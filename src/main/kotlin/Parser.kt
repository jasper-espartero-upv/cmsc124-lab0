class Parser(private val tokens: List<Token>) {
    private var current = 0

    private fun unary(): Expr {
        if (match(TokenType.BANG)) {
            val operator = previous()
            val right = unary()

            return Expr.Unary(operator, right)
        }

        return primary()
    }


    private fun primary(): Expr {
        if (match(TokenType.TRUE)) return Expr.Literal(true)
        if (match(TokenType.FALSE)) return Expr.Literal(false)

        if (match(TokenType.NUMBER, TokenType.STRING)) {
            return Expr.Literal(previous().literal)
        }

        kotlin.system.exitProcess(65)
    }

    private fun peek() : Token {
        return tokens[current]
    }

    private fun previous(): Token {
        return tokens[current - 1]
    }

    private fun advance(): Token {
        val token = tokens[current]

        if (!isAtEnd()) {
            current++
        }

        return token
    }



    private fun check(type: TokenType): Boolean {
        if (isAtEnd()) {
            return false
        }

        return peek().type == type
    }

    private fun match(vararg types: TokenType): Boolean {
        for (type in types) {
            if (check(type)) {
                advance()
                return true
            }
        }

        return false
    }

    private fun consume(type: TokenType, message: String): Token {
        if (check(type)) {
            return advance()
        }

        System.err.println(message)
        kotlin.system.exitProcess(65)
    }

    private fun isAtEnd(): Boolean {
        return peek().type == TokenType.EOF
    }
}