package core;

import core.factory.*;
import exception.IncorrectFields;
import org.apache.commons.lang.StringUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static core.Attributes.AUTHOR;
import static core.Attributes.EDITOR;
import static core.Attributes.NONE;

public class BitTexCore {

    public static BitTexDatabase parser(String path) {

        BitTexDatabase bitTexDatabase = new BitTexDatabase();

        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            int numberOfLine = 0;

            while ((line = reader.readLine()) != null) {

                Pattern pattern = Pattern.compile("^@([a-zA-Z]*)\\{([^,]*),*");

                Matcher matcher = pattern.matcher(line);

                if (matcher.matches()) {
                    Record type = Record.fromString(matcher.group(1));
                    String key = matcher.group(2);

                    switch (type) {
                        case STRING:
                            String[] arr = key.split("=");
                            bitTexDatabase.putString(arr[0].trim(), arr[1].substring(1, arr[1].length() - 1));
                            break;
                        case COMMENT:
                            break;
                        case PREAMBLE:
                            break;
                        default:
                            Map<Attributes, List<String>> attributes = new HashMap<>();
                            while ((line = reader.readLine()) != null && !line.equals("}")) {
                                Pattern attributesPattern = Pattern.compile("\\s*([a-zA-Z]*)\\s?[=]\\s?([^,]*),?");
                                Matcher attributesMatcher = attributesPattern.matcher(line);

                                if (attributesMatcher.matches()) {
                                    Attributes attribute = Attributes.fromString(attributesMatcher.group(1));
                                    List<String> attributeValue = parseAttributeValue(attribute, attributesMatcher.group(2), bitTexDatabase, numberOfLine);

                                    if (attribute != NONE) {
                                        attributes.put(attribute, attributeValue);
                                    }
                                }
                            }
                            addToDatabase(bitTexDatabase, type, attributes, key);
                            break;
                    }
                }
                numberOfLine++;
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return bitTexDatabase;
    }

    public static List<String> parseAttributeValue(Attributes attribute, String value, BitTexDatabase bitTexDatabase, int numberOfLine) {

        List<String> listValues = new LinkedList<>();
        StringJoiner sb = new StringJoiner(" ");

        for (String word : value.split("#")) {

            word = word.trim();

            if ((word.charAt(0) == '"' && word.charAt(word.length() - 1) == '"') ||
                    (word.charAt(0) == '{' && word.charAt(word.length() - 1) == '}')) {
                word = word.substring(1, word.length() - 1);
                if (attribute == AUTHOR || attribute == EDITOR) {
                    for (String author : word.split("and")) {
                        listValues.add(author.trim());
                    }
                } else {
                    sb.add(word);
                }
            } else {
                if (bitTexDatabase.getValuString(word) != null) {
                    sb.add(bitTexDatabase.getValuString(word));
                } else if (bitTexDatabase.getMonth(word) != null) {
                    sb.add(bitTexDatabase.getMonth(word));
                } else {
                    if (!StringUtils.isNumeric(word)) throw new IncorrectFields("Invalid variable: " + word + " in line: " + numberOfLine);
                    else sb.add(word);
                }
            }
        }

        if (attribute != AUTHOR && attribute != EDITOR) {
            listValues.add(sb.toString());
        }

        return listValues;
    }


    private static void addToDatabase(BitTexDatabase bitTexDatabase, Record record, Map<Attributes, List<String>> attributes, String key) {
        switch (record) {
            case ARTICLE:
                BibTeXEntity article = ArticleFactory.createRecord(attributes, key);
                bitTexDatabase.put(article);
                break;
            case BOOK:
                BibTeXEntity book = BookFactory.createRecord(attributes, key);
                bitTexDatabase.put(book);
                break;
            case BOOKLET:
                BibTeXEntity booklet = BookletFactory.createRecord(attributes, key);
                bitTexDatabase.put(booklet);
                break;
            case CONFERENCE:
                BibTeXEntity conference = ConferenceFactory.createRecord(attributes, key);
                bitTexDatabase.put(conference);
                break;
            case INBOOK:
                BibTeXEntity inBook = InBookFactory.createRecord(attributes, key);
                bitTexDatabase.put(inBook);
                break;
            case INCOLLECTION:
                BibTeXEntity inCollection = InCollectionFactory.createRecord(attributes, key);
                bitTexDatabase.put(inCollection);
                break;
            case INPROCEEDINGS:
                BibTeXEntity inProceedings = InProceedingsFactory.createRecord(attributes, key);
                bitTexDatabase.put(inProceedings);
                break;
            case MANUAL:
                BibTeXEntity manual = ManualFactory.createRecord(attributes, key);
                bitTexDatabase.put(manual);
                break;
            case MASTERSTHESIS:
                BibTeXEntity masterThesis = MasterThesisFactory.createRecord(attributes, key);
                bitTexDatabase.put(masterThesis);
                break;
            case MISC:
                BibTeXEntity misc = MiscFactory.createRecord(attributes, key);
                bitTexDatabase.put(misc);
                break;
            case PHDTHESIS:
                BibTeXEntity phdThesis = PhdThesisFactory.createRecord(attributes, key);
                bitTexDatabase.put(phdThesis);
                break;
            case PROCEEDINGS:
                BibTeXEntity proceedings = ProceedingsFactory.createRecord(attributes, key);
                bitTexDatabase.put(proceedings);
                break;
            case TECHREPORT:
                BibTeXEntity techReport = TechReportFactory.createRecord(attributes, key);
                bitTexDatabase.put(techReport);
                break;
            case UNPUBLISHED:
                BibTeXEntity unpublished = UnpublishedFactory.createRecord(attributes, key);
                bitTexDatabase.put(unpublished);
                break;
            default:
                break;
        }
    }


}