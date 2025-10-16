package org.aburavov.otus.java.basic.hw13;

import org.aburavov.otus.java.basic.hw13.network.Client;
import org.aburavov.otus.java.basic.hw13.protocol.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class DemoClient {
    private static final String exitCommand = "/exit";

    private static class ExitRequested extends RuntimeException {
    }

    public void Run(int port) {
        Client client = new Client("localhost", port);

        try {
            client.connect();
            client.send("REQUEST_TYPE:GET_OPERATION_LIST");

            Map<Parameter, String> getOperationsResp = Mapper.decode(client.receive());
            if (!getOperationsResp.get(Parameter.STATUS).equalsIgnoreCase(Status.SUCCESS.toString())) {
                System.out.println("INVALID SERVER RESPONSE: " + getOperationsResp.get(Parameter.ERROR));
                return;
            }
            List<Operation> availableOperations = parseOperations(getOperationsResp.get(Parameter.RESULT));

            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("EXAMPLE_CLIENT; INPUT /exit TO EXIT");
                System.out.println("AVAILABLE OPERATIONS: " + availableOperations.stream().map(Operation::getSymbol).toList());

                Operation operation = readOperation(scanner, "PLEASE INPUT YOUR OPERATION", availableOperations);

                int number1 = readInt(scanner, "PLEASE TYPE NUMBER 1");
                int number2 = readInt(scanner, "PLEASE TYPE NUMBER 2");

                System.out.println("CALCULATING " + number1 + " " + operation.getSymbol() + " " + number2);

                String request = "REQUEST_TYPE:CALCULATE;OPERATION:" + operation + ";NUMBERS:" + number1 + "," + number2;
                client.send(request);

                Map<Parameter, String> calculateResp = Mapper.decode(client.receive());
                if (!calculateResp.get(Parameter.STATUS).equalsIgnoreCase(Status.SUCCESS.toString())) {
                    System.out.println("INVALID SERVER RESPONSE: " + calculateResp.get(Parameter.ERROR));
                    continue;
                }

                System.out.println("RESULT: " + calculateResp.get(Parameter.RESULT) + "\n\n");
            }
        } catch (ExitRequested e) {
            System.out.println("EXIT");
        } catch (IOException | ProtocolException e) {
            e.printStackTrace();
        } finally {
            client.disconnect();
        }
    }

    private static Operation readOperation(Scanner sc, String prompt, List<Operation> availableOperations) {
        while (true) {
            System.out.println(prompt);
            String line = readLineOrExit(sc);

            for (Operation availableOperation : availableOperations) {
                if (availableOperation.getSymbol().equals(line)) {
                    return availableOperation;
                }
            }

            System.out.println("WRONG OPERATION, TRY AGAIN");
        }
    }

    private static int readInt(Scanner sc, String prompt) {
        while (true) {
            System.out.println(prompt);
            String line = readLineOrExit(sc);

            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("NOT A NUMBER, TRY AGAIN");
            }
        }
    }

    private static String readLineOrExit(Scanner sc) {
        if (!sc.hasNextLine()) throw new ExitRequested(); // EOF (Ctrl+D/Ctrl+Z)
        String line = sc.nextLine().trim();
        if (line.equalsIgnoreCase(exitCommand)) throw new ExitRequested();
        return line;
    }


    private List<Operation> parseOperations(String input) {
        String[] splitted = input.split(",");
        List<Operation> result = new ArrayList<>();
        for (String str : splitted) {
            result.add(Operation.valueOf(str));
        }
        return result;
    }
}
