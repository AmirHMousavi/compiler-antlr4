### info

- `ANTLR 4.7.1` is used for both grammar and test program. the `ANTLR 4.7.1` jar file exists in the `lib` folder.
- test programs does not contain errorful programs. all should pass. I hope :)
- antlr4.antlrToolPath=<PATH TO PROJECT>/4DV506.sm222cf.PA1/lib/antlr-4.7.1-complete.jar
- install the [ANSI Escape in Console](https://marketplace.eclipse.org/content/ansi-escape-console#group-details) plugin for Eclipse to see the console outputs in color.

### compile

- to compile and run you should link the generated files in `Grammar` project to `PA1`, `PA2`or `PA3` projects
- import everything to Eclipse, go to Grammar project open `MiniJava.g4` and Build it (by pressing Run)
- right click on `PA1`or `PA2` or `PA3` and go to
  `Build Path -> Configure BUild Path -> Source.`
  click the link source and Browse to `Grammar/target/generated-sources/antlr4`
  preferebly select the `antlr4` folder.

- now you have access to genereated files via `antlr4`
- got to Main class of the project (either PA1 or PA2 or PA3) give one of the testFiles names (by default it is `simple.java`)
- click `run` and see

### compiler jar file

- the jar file exists in the `PA2` project. go to `PA2/lib/MJCompiler.jar`
- open a terminal in that folder and run the command

```sh
	java -jar MJCompiler.jar [pathToTestFile]
```

- if you want to use existing test file give the path `../testFiles/name.java`
