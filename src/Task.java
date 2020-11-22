import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.concurrent.Callable;

public class Task implements Callable<Long>{

    String pathToFile;
    StringBuilder stringBuilder = new StringBuilder();

    public Task(String pathToFile){
     this.pathToFile = pathToFile;
    }

    @Override
    public Long call() {

        long start = System.currentTimeMillis();

        PrintWriter printWriter = null;
        try {
            printWriter = new PrintWriter(pathToFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        char letters[] = {'У', 'К', 'Е', 'Н', 'Х', 'В', 'А', 'Р', 'О', 'С', 'М', 'Т'};

        for(int regionCode = 1; regionCode < 50; regionCode++) {

            StringBuilder stringBuilder = new StringBuilder();

            for (int number = 1; number < 1000; number++) {
                for (char firstLetter : letters) {
                    for (char secondLetter : letters) {
                        for (char thirdLetter : letters) {

                            stringBuilder.append(firstLetter);
                            stringBuilder.append(padNumber(number, 3));
                            stringBuilder.append(secondLetter);
                            stringBuilder.append(thirdLetter);
                            stringBuilder.append(padNumber(regionCode, 2));
                            stringBuilder.append("\n");
                        }
                    }
                }
            }
            printWriter.write(stringBuilder.toString());
        }
        printWriter.flush();
        printWriter.close();

        return System.currentTimeMillis() - start;
    }

    private String padNumber(int number, int numberLength)
    {
        stringBuilder.setLength(0);
        stringBuilder.append(number);
        int padSize = numberLength - stringBuilder.length();

        for(int i = 0; i < padSize; i++) {
            stringBuilder.insert(0, "0");
        }

        return stringBuilder.toString();
    }
}
