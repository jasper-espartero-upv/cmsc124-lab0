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

            in '0'..'9' -> number()
            in 'A'..'Z', in 'a'..'z', '_' -> identifier()
        }
    }

    private fun advance(): Char {
        val c = source[current]
        current++
        return c
    }

    private fun peek(): Char {
        if (isAtEnd()) {
            return '\u0000'
        }
        return source[current]
    }

    private fun isAtEnd(): Boolean {
        return current >= source.length
    }

    private fun addToken(type: TokenType) {
        val text = source.substring(start, current)
        tokens.add(Token(type, text, null, line))
    }

    private fun number() {
        while (peek().isDigit()) {
            advance()
        }
        val number = source.substring(start, current)

    }

    private fun identifier() {
        while (peek().isDigit() || peek().isLetter() || peek() == '_'){
            advance()
        }
        val text = source.substring(start, current)
        when (text) {
            "var" -> addToken(TokenType.VAR)
            "if" -> addToken(TokenType.IF)
            "else" -> addToken(TokenType.ELSE)
            "while" -> addToken(TokenType.WHILE)
            "for" -> addToken(TokenType.FOR)
            "function" -> addToken(TokenType.FUNCTION)
            "return" -> addToken(TokenType.RETURN)
            "true" -> addToken(TokenType.TRUE)
            "false" -> addToken(TokenType.FALSE)
            "tower" -> addToken(TokenType.TOWER)
            "wave" -> addToken(TokenType.WAVE)
            "enemy" -> addToken(TokenType.ENEMY)
            "upgrade" -> addToken(TokenType.UPGRADE)
            "ability" -> addToken(TokenType.ABILITY)
            "projectile" -> addToken(TokenType.PROJECTILE)
            "map" -> addToken(TokenType.MAP)
            else -> addToken(TokenType.IDENTIFIER)
        }
    }
}