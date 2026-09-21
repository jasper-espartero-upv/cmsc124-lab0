# TDscript

## Creators

- Jasper S. Espartero (jasper-espartero-upv)
- Carlo Joshua E. De Lewmos (cjdelemos)

## Overview

**TDscript** is a domain-specific programming language (DSL) designed to be as readable as possible even for non-programmers, as long as they are familiar with the basic concepts of tower defense games. The terminology used in the language is based 
on common tower defense elements such as towers, enemies, waves, upgrades, 
and abilities, allowing users to configure and implement 

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

## Token output format

```
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


```
[specific tests]...
```

Run locally with:

```bash
curl -sSL https://raw.githubusercontent.com/WhiteLicorice/cmsc-124-harness/v1.1/run_tests.py -o run_tests.py
./build.sh
python3 run_tests.py tests/lab1
```

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
