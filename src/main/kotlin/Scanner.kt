class Scanner(private val source: String) {

    private val tokens = mutableListOf<Token>()
    private var start = 0
    private var current = 0
    private var line = 1

    fun scanTokens(): List<Token> {
        while (!isAtEnd()) {
            start = current
            scanToken()
        }

        tokens.add(Token(TokenType.EOF, "", null, line))
        return tokens
    }

    private fun scanToken() {
        val c = advance()

        when (c) {
            '(' -> addToken(TokenType.LEFT_PAREN)
            ')' -> addToken(TokenType.RIGHT_PAREN)
            '{' -> addToken(TokenType.LEFT_BRACE)
            '}' -> addToken(TokenType.RIGHT_BRACE)

            '+' -> addToken(TokenType.PLUS)
            '-' -> addToken(TokenType.MINUS)
            '*' -> addToken(TokenType.STAR)
            '/' -> addToken(TokenType.SLASH)

            '=' -> addToken(TokenType.EQUAL)
            '!' -> addToken(TokenType.BANG)
            '<' -> addToken(TokenType.LESS)
            '>' -> addToken(TokenType.GREATER)

            ':' -> addToken(TokenType.COLON)
            ',' -> addToken(TokenType.COMMA)
            '.' -> addToken(TokenType.DOT)
        }
    }

    private fun advance(): Char {
        val c = source[current]
        current++
        return c
    }

    private fun isAtEnd(): Boolean {
        return current >= source.length
    }

    private fun addToken(type: TokenType) {
        val text = source.substring(start, current)
        tokens.add(Token(type, text, null, line))
    }
}