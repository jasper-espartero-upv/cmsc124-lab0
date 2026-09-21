enum class TokenType {
    LEFT_PAREN,
    RIGHT_PAREN,
    LEFT_BRACE,
    RIGHT_BRACE,

    PLUS,
    MINUS,
    STAR,
    SLASH,

    EQUAL, EQUAL_EQUAL,
    BANG, BANG_EQUAL,
    LESS, LESS_EQUAL,
    GREATER, GREATER_EQUAL,

    COLON,
    COMMA,
    DOT,

    VAR,
    IF,
    ELSE,
    WHILE,
    FOR,
    FUNCTION,
    RETURN,
    TRUE,
    FALSE,

    TOWER,
    WAVE,
    ENEMY,
    UPGRADE,
    ABILITY,
    PROJECTILE,
    MAP,

    NUMBER,
    IDENTIFIER,
    STRING,

    EOF
}