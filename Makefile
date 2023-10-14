SRC_DIR = src/main/chess
TEST_DIR = src/test/chess

BIN_DIR = bin

SOURCES = $(wildcard $(SRC_DIR)/*.java)
TESTS = $(wildcard $(TEST_DIR)/*.java)

JAVAC = javac
JAVAC_FLAGS = -d $(BIN_DIR)

MAIN_CLASS = chess.PlayChess
CUSTOM_CLASS ?= MAIN_CLASS
CUSTOM_TEST ?= 

all: $(BIN_DIR) compile

compile_tests:
	$(JAVAC) -cp src/main $(JAVAC_FLAGS) $(TESTS)

compile:
	$(JAVAC) $(JAVAC_FLAGS) $(SOURCES)

$(BIN_DIR):
	mkdir -p $(BIN_DIR)

run:
	java -cp $(BIN_DIR) $(MAIN_CLASS)

run_custom:
	java -cp $(BIN_DIR) chess.$(CUSTOM_CLASS)

run_test:
	java -cp $(BIN_DIR) src.test.chess.$(CUSTOM_TEST)

clean:
	rm -rf $(BIN_DIR)

.PHONY: all clean compile
 