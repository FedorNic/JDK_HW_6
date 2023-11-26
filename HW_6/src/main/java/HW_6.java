import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * В качестве домашнего задания студентам будет предложена задача реализовать Java приложение для демонстрации
 * парадокса Монти Холла (Парадокс Монти Холла — Википедия) и наглядно убедиться в верности парадокса
 * (запустить игру в цикле на 1000 и вывести итоговый счет).
 * Студенту необходимо:
 * Создать свой Java Maven/Gradle проект;
 * Реализовать прикладную задачу - приложение для демонстрации парадокса Монти Холла;
 * Можно добавить любые библиотеки которые считают необходимыми
 * Результаты должны быть сохранены в HashMap (шаг цикла -> результат)
 * Необходимо вывести статистику по результату - количество позитивных и негативных результатов, процент от общего
 * количества шагов цикла.
 * <p>
 * Урок 6. Управление проектом: сборщики проектов
 * В качестве задачи предлагаю вам реализовать код для демонстрации парадокса Монти Холла
 * (Парадокс Монти Холла — Википедия) и наглядно убедиться в верности парадокса
 * (запустить игру в цикле на 1000 и вывести итоговый счет).
 * Необходимо:
 * Создать свой Java Maven или Gradle проект;
 * Самостоятельно реализовать прикладную задачу;
 * Сохранить результат в HashMap<шаг теста, результат>;
 * Вывести на экран статистику по победам и поражениям.
 */

public class HW_6 {
    static Random random;
    static Map<Integer, Boolean> results1;       // Статистика игрока БЕЗ изменения выбора (<шаг теста, результат>)
    static Map<Integer, Boolean> results2;       // Статистика игрока с изменением выбора (<шаг теста, результат>)
    static int doorsCount;                      // Количество дверей
    static int attempts;                         // Количество попыток

    static {
        random = new Random();
        results1 = new HashMap<>();
        results2 = new HashMap<>();
        doorsCount = 3;
        attempts = 1000;
    }

    public static void main(String[] args) {

        for (int i = 0; i < attempts; i++) {
            trial(i);
        }

        // Статистика для первого игрока
        int win = 0;
        for (Map.Entry<Integer, Boolean> entry : results1.entrySet()) {
            if (entry.getValue()) {
                win++;
            }
        }
        System.out.println("\n Парадокс Монти-Холла");
        System.out.println("--------------------------");
        System.out.println("\n Статистика выигрышей для игрока, не меняющего свой выбор:");
        System.out.println(" Количество побед: " + win + " раз из " + attempts + " попыток.");
        System.out.println("--------------------------");

        // Статистика для второго игрока
        win = 0;
        for (Map.Entry<Integer, Boolean> entry : results2.entrySet()) {
            if (entry.getValue()) {
                win++;
            }
        }
        System.out.println("\n Статистика выигрышей для игрока, изменяющего свой выбор:");
        System.out.println(" Количество побед: " + win + " раз из " + attempts + " попыток.");
        System.out.println("--------------------------");
    }

    private static void trial(int numRound) {
        int success = random.nextInt(doorsCount);
        int firstChoice = random.nextInt(doorsCount);
        int freeOpenDoor = -1;
        int secondChoice = -1;

        for (int i = 0; i < doorsCount; i++) {
            if (i != success && i != firstChoice) {
                freeOpenDoor = i;
            }
        }
        // Игрок не изменяет свой выбор
        for (int i = 0; i < doorsCount; i++) {
            if (i != freeOpenDoor && i != firstChoice) {
                secondChoice = firstChoice;
            }
        }
        results1.put(numRound, success == secondChoice);   // Статистика для первого игрока

        // Игрок изменяет свой выбор
        for (int i = 0; i < doorsCount; i++) {
            if (i != freeOpenDoor && i != firstChoice) {
                secondChoice = i;
            }
        }
        results2.put(numRound, success == secondChoice);   // Статистика для второго игрока
    }
}