package backend.academy.input;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.io.PrintStream;
import java.util.Optional;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class InputValidTest {

    private InputValid mazeInput;
    private PrintStream printStream;
    private Scanner scanner;

    @BeforeEach
    void setUp() {
        printStream = Mockito.mock(PrintStream.class);
        scanner = Mockito.mock(Scanner.class);
        mazeInput = new InputValid(scanner, printStream); // Конструктор с зависимостями
    }


    @Test
    void testInputTypeGenerateMaze_DFS() {
        when(scanner.nextLine()).thenReturn("1"); // Эмулируем ввод "1"

        mazeInput.inputTypeGenerateMaze();

        assertEquals(InputValid.TypeGenerate.DFS, mazeInput.typeGenerateMaze());
    }

    @Test
    void testInputTypeGenerateMaze_Kruskal() {
        when(scanner.nextLine()).thenReturn("2"); // Эмулируем ввод "2"

        mazeInput.inputTypeGenerateMaze();

        assertEquals(InputValid.TypeGenerate.Kruskal, mazeInput.typeGenerateMaze());
    }

    @Test
    void testInputTypeSolveMaze_DFS() {
        when(scanner.nextLine()).thenReturn("1"); // Эмулируем ввод "1"

        mazeInput.inputTypeSolveMaze();

        assertEquals(InputValid.TypeSolve.DFS, mazeInput.typeSolveMaze());
    }

    @Test
    void testInputTypeSolveMaze_BFS() {
        when(scanner.nextLine()).thenReturn("2"); // Эмулируем ввод "2"

        mazeInput.inputTypeSolveMaze();

        assertEquals(InputValid.TypeSolve.BFS, mazeInput.typeSolveMaze());
    }

}
