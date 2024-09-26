package org.parking.lot.abhigyan.mode;

import org.parking.lot.abhigyan.OutputPrinter;
import org.parking.lot.abhigyan.commands.CommandExecutorFactory;
import org.parking.lot.abhigyan.model.Command;

import java.io.*;

public class FileMode extends Mode {
    private String fileName;

    public FileMode(OutputPrinter outputPrinter, CommandExecutorFactory commandExecutorFactory, String fileName) {
        super(outputPrinter, commandExecutorFactory);
        this.fileName = fileName;
    }

    @Override
    public void process() throws IOException {
        final File file = new File(fileName);
        final BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            outputPrinter.invalidFile();
            return;
        }
        String input = reader.readLine();
        while (input != null) {
            final Command command = new Command(input);
            processCommand(command);
            input = reader.readLine();
        }
    }
}
