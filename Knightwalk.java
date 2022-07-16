import java.util.Arrays;

/**
 * Knightwalk
 */
public class Knightwalk {
 
    // Задаем размер шахматной доски N × N
    public static final int N = 5;
 
    // При помощи массивов опишем все восемь возможных движений коня из какой-либо точки

    public static final int[] row = { 2, 1, -1, -2, -2, -1, 1, 2, 2 };
    public static final int[] col = { 1, 2, 2, 1, -1, -2, -2, -1, 1 };

    // Проверяем, находится ли конь в предлах шахматной доски
    public static boolean isOnBoard(int x, int y)
    {
        if (x < 0 || y < 0 || x >= N || y >= N) {
            return false;
        } else {
            return true;
        } 
    }
    // Функция для вывода в консоль координат перемещения коня по шахматной доске
    private static void printTrack(int[][] visitedField)
    {
        for (var field: visitedField) {
            System.out.println(Arrays.toString(field));
        }
        System.out.println();
    }

    // Рекурсивная функция для выполнения обхода рыцаря
    public static void knightTour(int[][] visitedField, int x, int y, int pos)
    {
        // Отмечаем текущее поле как посещенное
        visitedField[x][y] = pos;

        // если все квадраты посещены, выводим решение
        if (pos >= N*N)
        {
            printTrack(visitedField);
            // откат перед возвратом
            visitedField[x][y] = 1;
            //return;
        }

        // проверка всех восьми возможных движений коня
        // и повторяться для каждого допустимого движения
        for (int k = 0; k < 8; k++)
        {
            // получаем новую позицию коня из текущей позиции на шахматной доске
            int newX = x + row[k];
            int newY = y + col[k];

            // если новая позиция действительна и еще не посещена
            if (isOnBoard(newX, newY) && visitedField[newX][newY] == 0) {
                knightTour(visitedField, newX, newY, pos + 1);
            }
        }

        // вернуться из текущего квадрата и удалить его из текущего пути
        visitedField[x][y] = 0;
        
    }

    public static void main(String[] args)
    {
        // `visited[][]` служит двум целям:
        // 1. Он отслеживает поля, задействованные в обходе коня.
        // 2. Хранит порядок посещения квадратов.
        int[][] visitedField = new int[N][N];
        int pos = 1;

        // начинаем обход конем с углового квадрата `(0, 0)`
        knightTour(visitedField, 0, 0, pos);
    }
}
    
