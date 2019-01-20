### PART III : CodeGeneration and StackMachine

- `ANTLR 4.7.1` is used for both grammar and test program. the `ANTLR 4.7.1` jar file exists in the `lib` folder.
- test programs does not contain errorful programs. all should pass. I hope :)
- antlr4.antlrToolPath=<PATH TO PROJECT>/4DV506.sm222cf.PA2/lib/antlr-4.7.1-complete.jar

### Compile

- to compile and run you should link the generated files in `4DV506.sm222cf.Grammar` project to `4DV506.sm222cf.PA3`
- import everything to _Eclipse_, go to Grammar project open `MiniJava.g4` and Build it by pressing `Run`
- right click on `4DV506.sm222cf.PA3` project, go to
  `Build Path -> Configure BUild Path -> Source.`
- click the link source and Browse to `4DV506.sm222cf.Grammar/target/generated-sources/antlr4`
  preferebly select the `antlr4` folder.
- now you have access to genereated files via `antlr4`
- got to Main class (`CodeGen.java`) of the project `4DV506.sm222cf.PA3` give one of the testFiles names (or uncomment the defiend lines to loop over all test files)
- click `run` and see
- NOTE: you can comment out the `PrintVisitor` or `PrintTable` for not seeing them and just see the Type checking results.

### Executable JAR files.

- in `4DV506.sm222cf.PA3/lib` folder you will find two executable jar files
- `CodeGen.jar` which gets a java sample file and generated the `tjp` object file. (_you can use the existing sample files in `tinyjava_samples` folder at the root of this project_)

```
java -jar CodeGen.jar <FileName.java>    e.g   ../tinyjava_samples/Sum.java
```

- `Intrepreter.jar` which get the `tjp` file and executes the code and prints the final result of execution to console

```
java -jar Intrepreter.jar <FileName.tjp>   e.g   ../tinyjava_samples/Sum.tjp
```
