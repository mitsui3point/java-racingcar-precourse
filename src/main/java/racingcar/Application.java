package racingcar;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;
import racingcar.constant.*;

import java.util.*;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        String[] names = getValidNames();
        int lastRoundIndex = getValidLastRoundIndex();

        LinkedHashMap<String, List<CarStatus>> cars = new LinkedHashMap<>();
        for (String name : names) {
            List<CarStatus> carForwards = new ArrayList<>();
            for (int roundIndex = 0; roundIndex < lastRoundIndex; roundIndex++) {
                carForwards.add(
                        new Car(
                                Randoms.pickNumberInRange(
                                        Conditions.MIN_RANDOM_NUMBER,
                                        Conditions.MAX_RANDOM_NUMBER
                                )
                        ).getStatus()
                );
            }
            cars.put(name, carForwards);
        }

        List<LinkedHashMap<String, String>> results = new ArrayList<>();
        LinkedHashMap<String, String> result = new LinkedHashMap<>();
        for (int roundIndex = 0; roundIndex < lastRoundIndex; roundIndex++) {
            for (Map.Entry<String, List<CarStatus>> round : cars.entrySet()) { // pobi, woni
                String printCar = Result.PRINT_STOP;
                String carName = round.getKey();
                List<CarStatus> carStatuses = round.getValue();

                if (CarStatus.isForward(carStatuses.get(roundIndex))) {
                    printCar = Result.PRINT_FORWARD;
                }
                if (result.containsKey(carName)) {
                    result.put(carName, result.get(carName).concat(printCar));
                }
                if (!result.containsKey(carName)) {
                    result.put(carName, printCar);
                }
            }
            results.add(new LinkedHashMap<>(result));
        }

        for (LinkedHashMap<String, String> printResult: results) {
            for (Map.Entry<String, String> printRoundResult:
                    printResult.entrySet()) {
                System.out.println(printRoundResult.getKey() + " : " + printRoundResult.getValue());
            }
        }

        int maxForwards = 0;
        Set<Map.Entry<String, String>> finalRound = results.get(results.size() - 1).entrySet();
        for (Map.Entry<String, String> finalResult : finalRound) {
            if (maxForwards < finalResult.getValue().length()) {
                maxForwards = finalResult.getValue().length();
            }
        }
        System.out.print("최종 우승자 : ");
        String winners = "";
        for (Map.Entry<String, String> finalResult : finalRound) {
            if (maxForwards == finalResult.getValue().length()) {
                winners = winners.concat(", ").concat(finalResult.getKey());
            }
        }
        winners = winners.replaceFirst(", ", "");
        System.out.println(winners);
    }

    private static int getValidLastRoundIndex() {
        int lastRoundIndex = 0;

        System.out.println("시도할 횟수는 몇회인가요?");
        lastRoundIndex = Integer.parseInt(Console.readLine());

        try {
            if (!(lastRoundIndex > Conditions.MIN_ROUND_LENGTH)) {
                System.out.println(ErrorMessage.ROUND_INDEX_LESS_THAN_ZERO);
                throw new IllegalArgumentException(ErrorMessage.ROUND_INDEX_LESS_THAN_ZERO);
            }
        } catch (IllegalArgumentException e) {
            return getValidLastRoundIndex();
        }
        return lastRoundIndex;
    }

    private static String[] getValidNames() {
        String namesText = "";

        System.out.println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)");
        namesText = Console.readLine();

        try {
            if (!(namesText.length() > 0 &&
                    namesText.split(Regex.COMMA).length > 0)) {
                System.out.println(ErrorMessage.CAR_NAME_LENGTH_IS_OUT_OF_RANGE);
                throw new IllegalArgumentException(ErrorMessage.CAR_NAME_LENGTH_IS_OUT_OF_RANGE);
            }
            for (String name : namesText.split(Regex.COMMA)) {
                if (!(name.length() > Conditions.MIN_NAMES_LENGTH &&
                        name.length() <= Conditions.MAX_NAME_LENGTH)) {
                    System.out.println(ErrorMessage.CAR_NAME_LENGTH_IS_OUT_OF_RANGE);
                    throw new IllegalArgumentException(ErrorMessage.CAR_NAME_LENGTH_IS_OUT_OF_RANGE);
                }
            }
        } catch (IllegalArgumentException e) {
            return getValidNames();
        }

        return namesText.split(Regex.COMMA);
    }
}
