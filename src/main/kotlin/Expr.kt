sealed class Node

sealed class Expr : Node() {
    data class Unary(val operator: Token, val right: Expr) : Expr()
    data class Literal(val value: Any?) : Expr()
}