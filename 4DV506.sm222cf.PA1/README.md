### PART I : Testing the Grammar

- `ANTLR 4.7.1` is used for both grammar and test program. the `ANTLR 4.7.1` jar file exists in the `lib` folder.
- test programs does not contain errorful programs. all should pass. I hope :)
- antlr4.antlrToolPath=<PATH TO PROJECT>/4DV506.sm222cf.PA1/lib/antlr-4.7.1-complete.jar
- install the [ANSI Escape in Console](https://marketplace.eclipse.org/content/ansi-escape-console#group-details) plugin for Eclipse to see the console outputs in color.

### Compile

- to compile and run you should link the generated files in `4DV506.sm222cf.Grammar` project to `4DV506.sm222cf.PA1`
- import everything to _Eclipse_, go to Grammar project open `MiniJava.g4` and Build it by pressing `Run`
- right click on `4DV506.sm222cf.PA1` project, go to
  `Build Path -> Configure BUild Path -> Source.`
- click the link source and Browse to `4DV506.sm222cf.Grammar/target/generated-sources/antlr4`
  preferebly select the `antlr4` folder.
- now you have access to genereated files via `antlr4`
- got to Main class of the project `4DV506.sm222cf.PA1` give one of the testFiles names (or uncomment the defiend lines to loop over all test files)
- click `run` and see
