package org.parking.lot.abhigyan.model;

import org.parking.lot.abhigyan.exceptions.InvalidCommandException;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Command {
    private final static String SPACE = " ";
    private String commandName;
    private List<String> params;

    public String getCommandName() {
        return commandName;
    }

    public List<String> getParams() {
        return params;
    }

    public Command(final String inputLine) {
        final List<String> tokenList = Arrays.stream(inputLine.trim().split(SPACE))
                .map(String::trim)
                .filter(token -> (!token.isEmpty()))
                .collect(Collectors.toList());
        if(tokenList.isEmpty()) {
            throw new InvalidCommandException();
        }
        commandName = tokenList.get(0).toLowerCase();
        tokenList.remove(0);
        params = tokenList;
    }
}
