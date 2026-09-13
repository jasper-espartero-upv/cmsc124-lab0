fun main(args: Array<String>) {
    val source = "(+){-*/=!:,.<>"

    val scanner = Scanner(source)
    val tokens = scanner.scanTokens()

    for (token in tokens) {
        println(token)
    }
}