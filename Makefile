# Define the source directory, where your .java files are located.
SRC_DIR = src/main/chess

# Define the build directory, where the .class files will be generated.
BIN_DIR = bin

# Java source files.
SOURCES = $(wildcard $(SRC_DIR)/*.java)

# Define the Java compiler and flags.
JAVAC = javac
JAVAC_FLAGS = -d $(BIN_DIR)

# Define the main class and target JAR file.
MAIN_CLASS = chess.PlayChess

all: $(BIN_DIR) compile

compile:
	$(JAVAC) $(JAVAC_FLAGS) $(SOURCES)

$(BIN_DIR):
	mkdir -p $(BIN_DIR)

# Run the Java program.
run:
	java -cp $(BIN_DIR) $(MAIN_CLASS)

# Clean up generated files.
clean:
	rm -rf $(BIN_DIR)

.PHONY: all clean
