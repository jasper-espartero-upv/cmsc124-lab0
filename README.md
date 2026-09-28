# TDscript

## Creators

- Jasper S. Espartero (jasper-espartero-upv)
<<<<<<< HEAD
- Carlo Joshua E. De Lemos (cjdelemos)

## Overview

**TDscript** is a domain-specific programming language (DSL) designed to be readable for users familiar with the basic concepts of tower defense games. Its terminology is based on common tower defense elements such as towers, enemies, waves, upgrades, abilities, projectiles, and maps.
=======
- Carlo Joshua E. De Lewmos (cjdelemos)

## Overview

**TDscript** is a domain-specific programming language (DSL) designed to be as readable as possible even for non-programmers, as long as they are familiar with the basic concepts of tower defense games. The terminology used in the language is based 
on common tower defense elements such as towers, enemies, waves, upgrades, 
and abilities, allowing users to configure and implement 
>>>>>>> 7a9f415b1822efbad45e6f82486028cc8436465c

## Host language and build

- Host language: Kotlin 2.0.20
<<<<<<< HEAD
- Version metadata: `build.gradle.kts`
=======
- Version metadata: build.gradle.kts
>>>>>>> 7a9f415b1822efbad45e6f82486028cc8436465c
- Build: `./build.sh`
- JDK version: 21

## Running it

<<<<<<< HEAD
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
=======

| Command                   | What it does                                      |
|---------------------------|---------------------------------------------------|
| `./run <file>`            | [Executes a program. Available from Lab 4.]       |
| `./run --tokenize <file>` | [Prints the token stream.]                        |
| `./run --parse <file>`    | [Prints the parsed tree.]                         |
| `./run --eval <file>`     | [Evaluates each expression and prints its value.] |
| `./run`                   | [Starts the REPL.]                                |


Exit codes: 0 [when], 65 [when], 70 [when].

## File extension

`[.tds]` 

## Lexical structure

### Generic Keywords

| Keyword  | Purpose                                                                         |
|----------|---------------------------------------------------------------------------------|
| var      | Declares a variable and optionally assigns it an initial value.                 |
| if       | Executes a block of code when a condition is true.                              |
| else     | Executes a block of code when the preceding `if` condition is false.            |
| while    | Repeatedly executes a block of code while a condition is true.                  |
| for      | Repeatedly executes a block of code according to a loop condition or iteration. |
| function | Declares a user-defined function with parameters and a body.                    |
| return   | Ends the execution of a function and optionally provides a return value.        |
| true     | Represents the boolean value `true`.                                            |
| false    | Represents the boolean value `false`.                                           |

### Generic Keywords

| Keyword    | Purpose                                                       |
|------------|---------------------------------------------------------------|
| tower      | Defines a tower and its properties and behavior.              |
| wave       | Defines a wave of enemies and their spawn configuration.      |
| enemy      | Defines an enemy and its properties and behavior.             |
| upgrade    | Defines an upgrade that modifies or enhances a game element.  |
| ability    | Defines a special ability that can be used by a game element. |
| projectile | Defines a projectile and its properties and behavior.         |
| map        | Defines a game map and its configuration.                     |



### Operators


| Operator | Category   | Operands | Associativity | Precedence |
|----------|------------|----------|---------------|------------|
| =        | assignment | binary   | right         | 1          |
| +        | arithmetic | binary   | left          | 2          |
| -        | arithmetic | binary   | left          | 2          |
| *        | arithmetic | binary   | left          | 3          |
| /        | arithmetic | binary   | left          | 3          |
| !        | logical    | unary    | right         | 4          |
| ==       | comparison | binary   | left          | 5          |
| !=       | comparison | binary   | left          | 5          |
| <        | comparison | binary   | left          | 5          |
| >        | comparison | binary   | left          | 5          |
| <=       | comparison | binary   | left          | 5          |
| >=       | comparison | binary   | left          | 5          |
| &&       | logical    | binary   | left          | 6          |
| \|\|     | logical    | binary   | left          | 7          |


### Literals


| Kind    | Syntax                                                                          | Produces        |
|---------|---------------------------------------------------------------------------------|-----------------|
| number  | integer: 42 , float: 3.14                                                       | a numeric value |
| string  | "Hello, World!", \n for newline, \" for literal quote, \\ for literal backslash | a string value  |
| boolean | true, false                                                                     | a boolean value |
| null    | null                                                                            | a null value    |


### Identifiers

- Start characters: Letters (A-Z, a-z)
- Continue characters: Letters (A-Z, a-z), Numbers (0-9), Underscore ('_')
- Case-sensitive: Yes
- [Reserved patterns, length limits, or other restrictions.]

### Comments

- Line comments: //
- Block comments: not supported
- Nesting: supported
- [Harness note: comment_prefix in tests/lab*/manifest.json is set to the
  token above.]

## Whitespace and termination

- Whitespace significant: [yes or no, and where]
- Statement terminator: [e.g. semicolon, newline, none]
- Block delimiters: [e.g. braces, indentation]
- Grouping delimiters: [e.g. parentheses]
>>>>>>> 7a9f415b1822efbad45e6f82486028cc8436465c

## Token output format

```
<<<<<<< HEAD
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
=======
[one line of real --tokenize output]
```

[What each field means. Frozen as of Lab 1; changes are recorded in the
changelog.]

## Grammar

```
[Your complete context-free grammar, current as of the latest activity.
Unambiguous, with precedence and associativity encoded in rule structure.]
```

## Parse output format

```
[one line of real --parse output, e.g. (+ 1.0 (* 2.0 3.0))]
```

- Groupings print as: [form]
- Numbers print as: [form]

## Semantics

### Values and types

[What runtime values exist, and how they are represented in the host
language.]

### Value printing

- Numbers: integer: 1, float: 1.0
- Nil: null
- Strings: without quotes

### Truthiness

[The complete rule. Which values are false in a condition; everything else is
true.]

### Operator semantics

- Arithmetic: [accepted operand types]
- `+` on strings: [concatenation, error, or coercion]
- Mixed types: [what happens]
- Comparison: [accepted operand types]
- Equality across types: [false, or an error]
- Division by zero: [value produced, or runtime error]

### Scope and bindings

- Redeclaration in the same scope: [allowed or an error]
- Uninitialized variable holds: [value]
- Shadowing: [behavior]
- Undefined name: [static error with exit 65, or runtime error with exit 70]

### Control flow and functions

- Logical operators return: [booleans, or the operand]
- Dangling else binds to: [which if]
- Closure capture of a loop variable: [per iteration, or shared]
- Function with no return statement produces: [value]
- Arity mismatch: [message and exit code]

## Native functions


| Name | Arguments | Returns | Notes |
|---|---|---|---|
| [name] | [count and types] | [type] | [caveats] |


## Errors and diagnostics

Message format:

```
[one real static error]
[one real runtime error]
```

>>>>>>> 7a9f415b1822efbad45e6f82486028cc8436465c

| Failure | Exit code |
|---|---|
| [lexical error] | 65 |
| [syntax error] | 65 |
| [runtime error] | 70 |

<<<<<<< HEAD
=======

>>>>>>> 7a9f415b1822efbad45e6f82486028cc8436465c
## Testing conventions


| Folder | Activity | Mode | Flag |
|---|---|---|---|
| tests/lab1 | Scanner | sidecar | `--tokenize` |
| tests/lab2 | Parser | sidecar | `--parse` |
| tests/lab3 | Evaluator | inline | `--eval` |
| tests/lab4 | Context | inline | none |
| tests/lab5 | Functions | inline | none |

<<<<<<< HEAD
Run the Lab 1 tests locally with:
=======

```
[specific tests]...
```

Run locally with:
>>>>>>> 7a9f415b1822efbad45e6f82486028cc8436465c

```bash
curl -sSL https://raw.githubusercontent.com/WhiteLicorice/cmsc-124-harness/v1.1/run_tests.py -o run_tests.py
./build.sh
python3 run_tests.py tests/lab1
```

<<<<<<< HEAD
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
=======
## Sample code

```
[a short program]
```

Output:

```
[its output]
```

## Design rationale



[Why the language is the way it is. Cover the choices that surprised you, the
features you cut, and the decisions you reversed. Specific reasons, not
approval of your own work.]

## Known limitations

- [What doesn't work, what is unimplemented, where behavior is worse than you
  would like.]

## Changelog


| Activity | What changed in the language |
|---|---|
| Lab 1 | [entry] |
>>>>>>> 7a9f415b1822efbad45e6f82486028cc8436465c
