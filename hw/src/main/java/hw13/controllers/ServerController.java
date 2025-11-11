package hw13.controllers;

import hw13.protocol.*;

import java.util.*;

public class ServerController {
    public Map<Parameter, String> handle(Map<Parameter, String> inputPayload) {
        RequestType requestType = RequestType.valueOf(inputPayload.get(Parameter.REQUEST_TYPE));

        switch (requestType) {
            case RequestType.GET_OPERATION_LIST:
                return handleGetOperations(inputPayload);
            case RequestType.CALCULATE:
                return handleCalculate(inputPayload);
        }

        return Map.of(
                Parameter.REQUEST_TYPE, RequestType.INVALID_REQUEST_RESPONSE.toString(),
                Parameter.STATUS, Status.ERROR.toString(),
                Parameter.ERROR, "invalid REQUEST_TYPE " + requestType
        );
    }

    private Map<Parameter, String> handleCalculate(Map<Parameter, String> inputPayload) {
        System.out.println("ServerController: handleCalculate");

        Operation operation = Operation.valueOf(inputPayload.get(Parameter.OPERATION));
        List<Integer> numbers = parseNumbers(inputPayload.get(Parameter.NUMBERS));

        if (
                numbers.size() < 2 ||
                        ((operation == Operation.DIVIDE || operation == Operation.MINUS) && numbers.size() != 2)
        ) {
            return Map.of(
                    Parameter.REQUEST_TYPE, RequestType.CALCULATE_RESPONSE.toString(),
                    Parameter.STATUS, Status.ERROR.toString(),
                    Parameter.ERROR, "invalid count of numbers"
            );
        }

        if (operation == Operation.DIVIDE && numbers.get(1) == 0) {
            return Map.of(
                    Parameter.REQUEST_TYPE, RequestType.CALCULATE_RESPONSE.toString(),
                    Parameter.STATUS, Status.ERROR.toString(),
                    Parameter.ERROR, "cannot divide to zero"
            );
        }

        int result = calculate(operation, numbers);

        return Map.of(
                Parameter.REQUEST_TYPE, RequestType.CALCULATE_RESPONSE.toString(),
                Parameter.STATUS, Status.SUCCESS.toString(),
                Parameter.RESULT, Integer.toString(result)
        );
    }

    private int calculate(Operation operation, List<Integer> numbers) {
        switch (operation) {
            case PLUS: {
                int sum = 0;
                for (int n : numbers) {
                    sum += n;
                }
                return sum;
            }
            case MULTIPLY: {
                int product = 1;
                for (int n : numbers) {
                    product *= n;
                }
                return product;
            }
            case Operation.DIVIDE:
                return numbers.get(0) / numbers.get(1);
            case Operation.MINUS:
                return numbers.get(0) - numbers.get(1);
            default:
                throw new RuntimeException("Unknown operation");
        }
    }

    private List<Integer> parseNumbers(String input) {
        String[] splitted = input.split(",");
        List<Integer> numbers = new ArrayList<>();
        for (String str : splitted) {
            numbers.add(Integer.parseInt(str));
        }
        return numbers;
    }

    private Map<Parameter, String> handleGetOperations(Map<Parameter, String> inputPayload) {
        System.out.println("ServerController: handleGetOperations");

        String operationsStr = String.join(",",
                Arrays.stream(Operation.values())
                        .map(Enum::name)
                        .toArray(String[]::new));


        return Map.of(
                Parameter.REQUEST_TYPE, RequestType.GET_OPERATION_LIST_RESPONSE.toString(),
                Parameter.STATUS, Status.SUCCESS.toString(),
                Parameter.RESULT, operationsStr
        );
    }
}
