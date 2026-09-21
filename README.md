# TDscript

## Creators

- Jasper S. Espartero (jasper-espartero-upv)
- Carlo Joshua E. De Lemos (cjdelemos)

## Overview

**TDscript** is a domain-specific programming language (DSL) designed to be readable for users familiar with the basic concepts of tower defense games. Its terminology is based on common tower defense elements such as towers, enemies, waves, upgrades, abilities, projectiles, and maps.

## Host language and build

- Host language: Kotlin 2.0.20
- Version metadata: `build.gradle.kts`
- Build: `./build.sh`
- JDK version: 21

## Running it

| Command                   | What it does             |
| ------------------------- | ------------------------ |
| `./run --tokenize <file>` | Prints the token stream. |

The tokenizer exits with code `0` when scanning succeeds and `65` when a lexical error is encountered.

## File extension

`.tds`

## Lexical structure

### Keywords

| Keyword      | Purpose                                                                  |
| ------------ | ------------------------------------------------------------------------ |
| `var`        | Declares a variable.                                                     |
| `if`         | Executes a block when a condition is true.                               |
| `else`       | Executes a block when the preceding `if` condition is false.             |
| `while`      | Repeatedly executes a block while a condition is true.                   |
| `for`        | Repeatedly executes a block according to a loop condition or iteration.  |
| `function`   | Declares a user-defined function.                                        |
| `return`     | Ends the execution of a function and optionally provides a return value. |
| `true`       | Represents the boolean value `true`.                                     |
| `false`      | Represents the boolean value `false`.                                    |
| `tower`      | Defines a tower.                                                         |
| `wave`       | Defines a wave.                                                          |
| `enemy`      | Defines an enemy.                                                        |
| `upgrade`    | Defines an upgrade.                                                      |
| `ability`    | Defines an ability.                                                      |
| `projectile` | Defines a projectile.                                                    |
| `map`        | Defines a map.                                                           |

### Operators

| Operator | Category   | Operands | Associativity | Precedence |
| -------- | ---------- | -------- | ------------- | ---------- |
| `=`      | assignment | binary   | right         | 1          |
| `+`      | arithmetic | binary   | left          | 2          |
| `-`      | arithmetic | binary   | left          | 2          |
| `*`      | arithmetic | binary   | left          | 3          |
| `/`      | arithmetic | binary   | left          | 3          |
| `!`      | logical    | unary    | right         | 4          |
| `==`     | comparison | binary   | left          | 5          |
| `!=`     | comparison | binary   | left          | 5          |
| `<`      | comparison | binary   | left          | 5          |
| `>`      | comparison | binary   | left          | 5          |
| `<=`     | comparison | binary   | left          | 5          |
| `>=`     | comparison | binary   | left          | 5          |
| `&&`     | logical    | binary   | left          | 6          |
| `\|\|`   | logical    | binary   | left          | 7          |

### Literals

| Kind    | Syntax            | Produces      |
| ------- | ----------------- | ------------- |
| Number  | `42`, `3.14`      | Numeric value |
| String  | `"Hello, World!"` | String value  |
| Boolean | `true`, `false`   | Boolean value |

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
- Statement terminator: None
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
| Lab 1    | Defined TDscript's lexical structure, including keywords, operators, literals, identifiers, comments, whitespace handling, and token output behavior. |
