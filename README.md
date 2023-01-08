# Simulation of computer file system with file dependencies

## Introduction

This is a simulation of a computer file system with file dependencies. User can create files and directories, and add dependencies between files. The program will then simulate the process of creating the files, and will print out the files by their dependencies.

## How to run

To run the program you need to have Java installed on your computer. You can download it from [here](https://www.java.com/en/download/). After you have installed Java, you can run the program.

## How to use

The program has a command line interface. You can use the following commands:

* `add <file_name>` - creates a file with the given name. Use the \ character to create folders (e.g. `add folder\file` will create a file named `file` in the folder `folder`).
After you have created a file, you can add text and dependencies to it. you can type any text you want, and to add a dependency by the following example: `require '<file_name>`. You can add multiple dependencies to a file. You can't have a dependency to a file that doesn't exist and have a cycled dependency (e.g. file A depends on file B, and file B depends on file A).
*The input will stop by 2 empty lines.*

* `print` - prints the files in order of their folders.

* `exit` - exits the program.

* `clear` - clears the screen.

* `remove <file_name>` - removes the file with the given name. You should give a full file name (e.g. `remove folder\file`).

* `printFC` - prints the files in order of their dependencies. For example, if file A depends on file B, file B will be printed before file A.

* `printC` - prints the files content in order of their dependencies. For example, if file A depends on file B, file B content will be printed before file A content.

## Example

```
add folder\file1
Goodbye world!
require 'folder\file2'

add folder\file2
Hello world!

print
root
    folder
        file2
        file1

printFC
folder\file2
folder\file1

printC

Hello world!
Goodbye world!
require 'folder\file2'
```

## Author

Daniil Shatravka
