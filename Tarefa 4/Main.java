import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Set;
import static java.util.stream.Collectors.*;

public class Main {
  public static void main(String[] args) {

    List<String> Lista = Arrays.asList("1", "2", "3", "4");

    List<String> Retorno = Lista.stream()
        .collect(toList());

    System.out.println(Retorno);

    List<Integer> mapList = Arrays.asList(1, 2, 3, 4, 5);

    Map<Integer, List<Integer>> mapResult = mapList.stream().collect(groupingBy(
      in -> in < 5 ? 1 : 2));

    System.out.println(mapResult);

    Set<String> setResult = Lista.stream()
        .collect(toSet());

    System.out.println(setResult);
    
    List<Integer> summaryList = Arrays.asList(1, 2, 3, 4, 5);

    IntSummaryStatistics summaryStatistics = summaryList.stream()
        .collect(summarizingInt(Integer::intValue));

    System.out.println(summaryStatistics);

    List<String> joiningList = Arrays.asList("1", "2", "3", "4");

    String joiningResult = joiningList.stream()
        .collect(joining(", "));

    System.out.println(joiningResult);

  }

}