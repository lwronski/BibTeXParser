package core.visualize;

import core.Attributes;
import core.BibTeXEntity;
import core.BitTexDatabase;
import core.Record;
import core.records.*;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class DefaultVisualVisitor implements VisualVisitor {

    private static final int LENGTH_TABLE = 100;

    public void visualizeDatabase(BitTexDatabase bitTexDatabase) {
        bitTexDatabase.getElements().forEach(e -> e.accept(this));
    }

    public void visualizeDatabase(BitTexDatabase bitTexDatabase, List<Record> records, List<String> authors) {
        bitTexDatabase.getElements().forEach(e -> {
            Record record = Record.fromString(e.getClass().getSimpleName().toUpperCase());
            if ((records.isEmpty() || records.contains(record))
                    && (authors.isEmpty() || checkAuthors(e, authors)))
                e.accept(this);

        });
    }

    public boolean checkAuthors(BibTeXEntity record, List<String> authorsArguments) {
        List<String> authorsRecord = ((RecordField) record).getRecords().get(Attributes.AUTHOR);

        if (authorsRecord == null) return false;

        for (String author : authorsArguments) {

            Pattern p = Pattern.compile(".*" + author + ".*");

            for (String s : authorsRecord) {
                if (p.matcher(s).matches()) {
                    return true;
                }
            }
        }
        return false;
    }

    public void printRecord(RecordField record) {
        for (int i = 0; i < LENGTH_TABLE; i++)
            System.out.print("-");
        System.out.println();

        String printed = "|" + record.getClass().getSimpleName().toUpperCase() + "(" + record.getKey() + ")";
        System.out.print(printed);
        printWhitespaces(printed.length());
        System.out.println("|");

        for (int i = 0; i < LENGTH_TABLE; i++)
            System.out.print("-");
        System.out.println();


        for (Map.Entry<Attributes, List<String>> entry : record.getRecords().entrySet()) {
            printed = "|" + entry.getKey().toString().toLowerCase();
            System.out.print(printed);
            printWhitespaces(printed.length() + 70);
            System.out.print("| ");

            int counter = 0;

            for (String value : entry.getValue()) {

                if (counter > 0) {
                    System.out.print("|");
                    printWhitespaces(71);
                    System.out.print("|");
                }

                System.out.print(value);
                printWhitespaces(value.length() + 31);
                if (counter > 0) System.out.print(" ");
                System.out.println("|");

                counter++;
            }

            for (int i = 0; i < LENGTH_TABLE; i++)
                System.out.print("-");
            System.out.println();
        }

        System.out.print("\n\n\n");

    }

    private void printWhitespaces(int length) {
        for (int i = 0; i < LENGTH_TABLE - length - 1; i++)
            System.out.print(" ");
    }


    @Override
    public void visit(Article article) {
        printRecord(article);
    }

    @Override
    public void visit(Book book) {
        printRecord(book);
    }

    @Override
    public void visit(Booklet booklet) {
        printRecord(booklet);
    }

    @Override
    public void visit(Conference conference) {
        printRecord(conference);

    }

    @Override
    public void visit(InBook inBook) {
        printRecord(inBook);

    }

    @Override
    public void visit(InCollection inCollection) {
        printRecord(inCollection);

    }

    @Override
    public void visit(InProceedings inProceedings) {
        printRecord(inProceedings);
    }

    @Override
    public void visit(Manual manual) {
        printRecord(manual);
    }

    @Override
    public void visit(MastersThesis mastersThesis) {
        printRecord(mastersThesis);
    }

    @Override
    public void visit(Misc misc) {
        printRecord(misc);
    }

    @Override
    public void visit(PhdThesis phdThesis) {
        printRecord(phdThesis);
    }

    @Override
    public void visit(Proceedings proceedings) {
        printRecord(proceedings);
    }

    @Override
    public void visit(TechReport techReport) {
        printRecord(techReport);
    }

    @Override
    public void visit(Unpublished unpublished) {
        printRecord(unpublished);
    }


}
