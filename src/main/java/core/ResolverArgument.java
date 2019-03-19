package core;

import org.apache.commons.lang.ArrayUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ResolverArgument {

    private static String help = "**\n" +
            " * Program arguments:\n" +
            " *      -p path to file BibTex\n" +
            " *      -a finding publications of the given author\n" +
            " *      -t finding record of the given type of record\n" +
            " *      -h help";

    private static List<String> options = Arrays.asList("-p", "-a", "-t");

    private String[] args;
    private String path;
    private List<Record> records;
    private List<String> authors;

    public ResolverArgument(String[] args) {
        if (ArrayUtils.isEmpty(args)) {
            System.out.println(help);
            System.exit(0);
        }

        this.args = args;
        this.records = new ArrayList<>();
        this.authors = new ArrayList<>();
        resolve(args);
    }

    private void resolve(String[] args) {
        try {
            for (int i = 0; i < args.length; i++) {
                switch (args[i]) {
                    case "-p":
                        path = args[i + 1];
                        break;
                    case "-t":
                        for (; i < args.length - 1; i++) {
                            if (!options.contains(args[i + 1])) {
                                records.add(Record.fromString(args[i + 1].toUpperCase()));
                            } else break;
                        }
                        break;
                    case "-a":
                        for (; i < args.length - 1; i++) {
                            if (!options.contains(args[i + 1])) {
                                authors.add(args[i + 1].replace(",", " "));
                            } else break;
                        }
                        break;
                }

            }
        } catch (RuntimeException e) {
            System.out.println(help);
            throw e;
        }
    }

    public String getFilePath() {
        return path;
    }

    public List<Record> getRecords() {
        return records;
    }

    public List<String> getAuthors() {
        return authors;
    }
}
