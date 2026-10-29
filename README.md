# TDscript

## Creators

- Jasper S. Espartero (jasper-espartero-upv)
- Carlo Joshua E. De Lemos (cjdelemos)

## Overview

**TDscript** is a domain-specific programming language (DSL) designed to be as readable as possible even for non-programmers, as long as they are familiar with the basic concepts of tower defense games. The terminology used in the language is based on common tower defense elements such as towers, enemies, waves, upgrades, and abilities, allowing users to configure and implement 

## Host language and build

- Host language: Kotlin 2.0.20
- Version metadata: build.gradle.kts
- Build: `./build.sh`
- JDK version: 21

## Running it

| Command                   | What it does                                      |
|---------------------------|---------------------------------------------------|
| `./run <file>`            | [Executes a program. Available from Lab 4.]       |
| `./run --tokenize <file>` | [Prints the token stream.]                        |
| `./run --parse <file>`    | [Prints the parsed tree.]                         |
| `./run --eval <file>`     | [Evaluates each expression and prints its value.] |
| `./run`                   | [Starts the REPL.]                                |

## Grammar

The order of precedence is as shown, from loosest to tightest:

program        -> declaration* EOF  

declaration    -> varDecl  
                | funDecl  
                | entityDecl  
                | statement  

entityDecl     -> ( "tower" | "wave" | "enemy" | "upgrade"  
                  | "ability" | "projectile" | "map" ) IDENTIFIER block  

varDecl        -> "var" IDENTIFIER ( "=" expression )?  

funDecl        -> "function" IDENTIFIER "(" parameters? ")" block  
parameters     -> IDENTIFIER ( "," IDENTIFIER )*  

statement     -> printStmt
                | exprStmt
                | ifStmt
                | whileStmt
                | forStmt
                | returnStmt
                | block

printStmt     -> "print" expression NEWLINE
exprStmt      -> expression NEWLINE 
ifStmt         -> "if" "(" expression ")" statement ( "else" statement )?  
whileStmt      -> "while" "(" expression ")" statement  
forStmt        -> "for" "(" ( varDecl | exprStmt )? ","? expression? ","? expression? ")" statement  
returnStmt     -> "return" expression?  
block          -> "{" declaration* "}"  

expression     -> assignment  
assignment     -> IDENTIFIER "=" assignment | equality  
equality       -> comparison ( ("!=" | "==") comparison )*  
comparison     -> term ( (">" | ">=" | "<" | "<=") term )*  
term           -> factor ( ("+" | "-") factor )*  
factor         -> unary ( ("*" | "/") unary )*  
unary          -> ("!" | "-") unary | primary  
primary        -> NUMBER | STRING | IDENTIFIER | "null" | "true" | "false" | "(" expression ")"  


Exit codes: 0 [when], 65 [when], 70 [when].

## File extension

`.tds`

## Lexical structure

### Keywords

| Keyword      | Purpose                                                                  |
|--------------|--------------------------------------------------------------------------|
| `var`        | Declares a variable.                                                     |
| `if`         | Executes a block when a condition is true.                               |
| `else`       | Executes a block when the preceding `if` condition is false.             |
| `while`      | Repeatedly executes a block while a condition is true.                   |
| `for`        | Repeatedly executes a block according to a loop condition or iteration.  |
| `function`   | Declares a user-defined function.                                        |
| `return`     | Ends the execution of a function and optionally provides a return value. |
| `true`       | Represents the boolean value `true`.                                     |
| `false`      | Represents the boolean value `false`.                                    |
| `null`       | Represents the null value.                                               |
| `tower`      | Defines a tower.                                                         |
| `wave`       | Defines a wave.                                                          |
| `enemy`      | Defines an enemy.                                                        |
| `upgrade`    | Defines an upgrade.                                                      |
| `ability`    | Defines an ability.                                                      |
| `projectile` | Defines a projectile.                                                    |
| `map`        | Defines a map.                                                           |

### Operators

| Operator | Category                   | Operands | Associativity | Precedence |
| -------- |----------------------------| -------- | ------------- | ---------- |
| `=`      | assignment                 | binary   | right         | 1          |
| `+`      | arithmetic / concatenation | binary   | left          | 2          |
| `-`      | arithmetic                 | binary   | left          | 2          |
| `*`      | arithmetic                 | binary   | left          | 3          |
| `/`      | arithmetic                 | binary   | left          | 3          |
| `!`      | logical                    | unary    | right         | 4          |
| `==`     | comparison                 | binary   | left          | 5          |
| `!=`     | comparison                 | binary   | left          | 5          |
| `<`      | comparison                 | binary   | left          | 5          |
| `>`      | comparison                 | binary   | left          | 5          |
| `<=`     | comparison                 | binary   | left          | 5          |
| `>=`     | comparison                 | binary   | left          | 5          |

### Literals

| Kind    | Syntax            | Produces      |
|---------|-------------------|---------------|
| Number  | `42`, `3.14`      | Numeric value |
| String  | `"Hello, World!"` | String value  |
| Boolean | `true`, `false`   | Boolean value |
| Null    | 'null'            | Null Value    |

Numbers are scanned as `Double` values. A decimal point is treated as part of a number only when it is followed by a digit.

Strings may span multiple lines. The scanner reports an error when a string reaches the end of the source without a closing quotation mark.

### Identifiers

- Start characters: Letters (`A-Z`, `a-z`) or `_`
- Continue characters: Letters (`A-Z`, `a-z`), numbers (`0-9`), or `_`
- Case-sensitive: Yes
- Keywords are recognized when their complete text matches a reserved keyword.
- Identifiers may contain underscores and numbers after the first character.

### Comments

- Line comments: `//`
- Block comments: `/* ... */`
- Nesting: Not supported
- Both line and block comments are ignored by the scanner.
- Unterminated block comments produce a lexical error.

## Whitespace and termination

- No; spaces, carriage returns (\r), and tabs (\t) are ignored as whitespace. Newlines (\n) increment line numbers but do not produce tokens. Newlines are ignored as tokens but are tracked for error line numbers.
- Statement terminator: Newline. Semicolons are not required to terminate statements.

TDscript uses the `print` statement to evaluate an expression and display its result.

Example:

```text
print 2 + 3
print "Hello, TDscript!"
```

Output:

```text
5
Hello, TDscript!
```
- Block delimiters: `{` and `}`
- Grouping delimiters: `(` and `)`

The scanner recognizes the following punctuation:

- `:`
- `,`
- `.`

## Token output format

```
Token(type=IDENTIFIER, lexeme=hello, literal=null, line=1)
```
- type=IDENTIFIER: The category/enum classification assigned by the scanner (identifies hello as a variable or function name, not a keyword).

- lexeme=hello: The exact slice of raw source code text read by the scanner.

- literal=null: The evaluated runtime value (used for parsed values like 10.0 or "text"; non-literals are null).

- line=1: The 1-based line number in the source file where this token was encountered.

## Evaluation Semantics

### Runtime Values

TDscript represents runtime values using Kotlin's `Any?` type. Numbers are represented as `Double`, strings as `String`, booleans as `Boolean`, and the null value as `null`.

The evaluator checks operand types at runtime to ensure that operations follow TDscript's evaluation rules. Operations involving unsupported operand types produce runtime errors.

### Value Printing

- **Numbers:** Printed without a trailing `.0` when the fractional part is zero; otherwise, printed using a consistent decimal representation.
- **Strings:** Printed without quotation marks.
- **Booleans:** Printed as `true` or `false`.
- **Null:** Printed as `null`.

These formatting rules apply consistently to evaluated values and string conversion.

### Conditions and Truthiness

TDscript uses strict boolean evaluation. Conditions in `if`, `while`, and other conditional contexts must evaluate to a boolean. Values of other types produce a runtime error.

The `!` operator also accepts boolean operands only. Applying logical negation to a non-boolean value produces a runtime error.

### Addition and String Concatenation

- Adding two numbers performs numeric addition.
- If either operand is a string, both operands are converted to their string representations and concatenated.
- If neither operand is a string, both operands must be numbers. Otherwise, evaluation produces a runtime error.

String conversion follows TDscript's value-printing rules.

### Equality

Values of the same type are compared by value. Values of different types are considered unequal, and two null values compare equal.

The `!=` operator returns the inverse of `==`.

### Evaluation Order

Binary expressions evaluate their left operand before their right operand. The operator is applied after both operands have been evaluated.

### Invalid Operations and Runtime Errors

Arithmetic and comparison operations require operands of the appropriate types. Negating a non-number, applying logical negation to a non-boolean, or performing another unsupported operation produces a runtime error.

Runtime errors include a source line number and are reported to standard error. In file mode, a runtime error terminates evaluation with exit code `70`. In the REPL, the error is reported and the prompt remains available for further input.

### Division by Zero

Division by zero produces a runtime error.

## Errors and diagnostics

Lexical errors are printed using the following format:

```text
[line <line>] Error: <message>
```

For example:

```text
[line 1] Error: Unexpected character: @
```

The scanner exits with code `65` when at least one lexical error is encountered.

| Failure | Exit code |
|---|---|
| [lexical error] | 65 |
| [syntax error] | 65 |
| [runtime error] | 70 |

## Testing conventions


| Folder | Activity | Mode | Flag |
|---|---|---|---|
| tests/lab1 | Scanner | sidecar | `--tokenize` |
| tests/lab2 | Parser | sidecar | `--parse` |
| tests/lab3 | Evaluator | inline | `--eval` |
| tests/lab4 | Context | inline | none |
| tests/lab5 | Functions | inline | none |

Run the Lab 1 tests locally with:

```bash
curl -sSL https://raw.githubusercontent.com/WhiteLicorice/cmsc-124-harness/v1.1/run_tests.py -o run_tests.py
./build.sh
python3 run_tests.py tests/lab1
```

## Design rationale

TDscript uses tower defense terminology as part of its language vocabulary so that its syntax can reflect concepts familiar to its intended users. Keywords such as `tower`, `wave`, `enemy`, `upgrade`, `ability`, `projectile`, and `map` are reserved for this purpose.

The scanner follows a conventional tokenization approach where whitespace is ignored, comments are skipped, and identifiers are checked against the language's reserved keywords.

## Known limitations

* Only the lexical analysis required for Lab 1 is currently implemented.
* Parsing and evaluation are not yet implemented.
* Operator precedence and associativity are documented but are not yet enforced by a parser.
* Statement termination rules are not yet defined.
* Block comments cannot be nested.

## Changelog

| Activity | What changed in the language                                                                                                                          |
| -------- | ----------------------------------------------------------------------------------------------------------------------------------------------------- |
| Lab 1    | Defined TDscript's lexical structure, including keywords, operators, literals, identifiers, comments, whitespace handling, and token output behavior. Updated lab0 test expected token output files (`*.expected`) to match the exact string format emitted by the Scanner. |
