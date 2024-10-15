package backend.academy.input;

/**
 * Интерфейс для храния константа и повторяющихся полей
 * */
public interface Constants {
    int MIN_SIZE = 3;
    String REG_GENERATE = "^[12]$";
    String REG_SOLVE = "^[123]$";
    String WARNING_INPUT = "Ошибка! Попробуйте еще раз";
    String INCORRECT_INPUT = "Вы ввели пустую строку или не число";
}
