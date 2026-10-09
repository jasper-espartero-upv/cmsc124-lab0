class Parser(private val tokens: List<Token>) {
    private var current = 0
    private var hadError = false

    private class ParseError : RuntimeException()

    fun parse(): List<Expr> {
        val expressions = mutableListOf<Expr>()

        while (!isAtEnd()) {
            try {
                expressions.add(expression())
            } catch (error: ParseError) {
                synchronize()
            }
        }

        if (hadError) {
            kotlin.system.exitProcess(65)
        }

        return expressions
    }

    // expression -> assignment ;
    private fun expression(): Expr {
        return assignment()
    }

    // assignment -> IDENTIFIER "=" assignment | equality ;
    private fun assignment(): Expr {
        val expr = equality()

        if (match(TokenType.EQUAL)) {
            val equals = previous()
            val value = assignment()

            if (expr is Expr.Variable) {
                return Expr.Assign(expr.name, value)
            }

            error(equals, "Invalid assignment target.")
        }

        return expr
    }

    // equality -> comparison ( ("!=" | "==") comparison )* ;
    private fun equality(): Expr {
        var expr = comparison()

        while (match(TokenType.BANG_EQUAL, TokenType.EQUAL_EQUAL)) {
            val operator = previous()
            val right = comparison()
            expr = Expr.Binary(expr, operator, right)
        }

        return expr
    }

    // comparison -> term ( (">" | ">=" | "<" | "<=") term )* ;
    private fun comparison(): Expr {
        var expr = term()

        while (match(TokenType.GREATER, TokenType.GREATER_EQUAL, TokenType.LESS, TokenType.LESS_EQUAL)) {
            val operator = previous()
            val right = term()
            expr = Expr.Binary(expr, operator, right)
        }

        return expr
    }

    // term -> factor ( ("+" | "-") factor )* ;
    private fun term(): Expr {
        var expr = factor()

        while (match(TokenType.MINUS, TokenType.PLUS)) {
            val operator = previous()
            val right = factor()
            expr = Expr.Binary(expr, operator, right)
        }

        return expr
    }

    // factor -> unary ( ("*" | "/") unary )* ;
    private fun factor(): Expr {
        var expr = unary()

        while (match(TokenType.SLASH, TokenType.STAR)) {
            val operator = previous()
            val right = unary()
            expr = Expr.Binary(expr, operator, right)
        }

        return expr
    }

    // unary -> ("!" | "-") unary | primary ;
    private fun unary(): Expr {
        if (match(TokenType.BANG, TokenType.MINUS)) {
            val operator = previous()
            val right = unary()
            return Expr.Unary(operator, right)
        }

        return primary()
    }

    // primary -> NUMBER | STRING | IDENTIFIER | "true" | "false" | "(" expression ")" ;
    private fun primary(): Expr {
        if (match(TokenType.FALSE)) return Expr.Literal(false)
        if (match(TokenType.TRUE)) return Expr.Literal(true)

        if (match(TokenType.NUMBER, TokenType.STRING)) {
            return Expr.Literal(previous().literal)
        }

        if (match(TokenType.IDENTIFIER)) {
            return Expr.Variable(previous())
        }

        if (match(TokenType.LEFT_PAREN)) {
            val expr = expression()
            consume(TokenType.RIGHT_PAREN, "Expect ')' after expression.")
            return Expr.Grouping(expr)
        }

        throw error(peek(), "Expect expression.")
    }

    // Helpers & Error Handling

    private fun peek(): Token = tokens[current]

    private fun previous(): Token = tokens[current - 1]

    private fun advance(): Token {
        if (!isAtEnd()) current++
        return previous()
    }

    private fun check(type: TokenType): Boolean {
        if (isAtEnd()) return false
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
        if (check(type)) return advance()
        throw error(peek(), message)
    }

    private fun isAtEnd(): Boolean = peek().type == TokenType.EOF

    private fun error(token: Token, message: String): ParseError {
        hadError = true
        if (token.type == TokenType.EOF) {
            System.err.println("[line ${token.line}] Error at end: $message")
        } else {
            System.err.println("[line ${token.line}] Error at '${token.lexeme}': $message")
        }
        return ParseError()
    }

    private fun synchronize() {
        advance()

        while (!isAtEnd()) {
            // Discard tokens until reaching a boundary or start of next expression
            advance()
        }
    }
}