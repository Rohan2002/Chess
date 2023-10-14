# Chess CLI Tool

Build a CLI tool to simulate 2 players playing the game of chess.

## Steps to compile
1. Run ```make``` to compile project files
2. Run ```make run``` to run the default class file which is PlayChess.class. It will be located in the bin folder.
3. Run ```make && make run_custom CUSTOM_CLASS=Board``` will run the main method in the src file (if defined). This is not the ideal approach. Use the ```test/chess``` directory to store test files.
4. Run ```make compile_tests && make run_test CUSTOM_TEST=TestBoard``` to run a test java file in the 
```test/chess``` directory.

Authors: Rohan Deshpande