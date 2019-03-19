import core.BitTexCore;
import core.BitTexDatabase;
import core.ResolverArgument;
import core.visualize.DefaultVisualVisitor;


/**
 * Program arguments:
 * <p>
 * -p path to file BibTex
 * -a finding publications of the given author
 * -t finding record of the given type of record
 * <p>
 * Examples:
 * <p>
 * -p ./src/main/java/xampl.bib
 * <p>
 * -p ./src/main/java/xampl.bib -a L[eslie],A.,Aamport
 * <p>
 * -p ./src/main/java/xampl.bib -t book -a Donald,E.,Knuth
 * <p>
 * -p ./src/main/java/xampl.bib -t inproceedings -a Alfred,V.,Oaho Jeffrey,D.,Ullman
 */


public class Main {
    public static void main(String[] args) {

        ResolverArgument resolverArgument = new ResolverArgument(args);
        BitTexDatabase bitTexDatabase;
        BitTexCore bitTexCore = new BitTexCore();

        bitTexDatabase = bitTexCore.parser(resolverArgument.getFilePath());

        DefaultVisualVisitor defaultVisualVisitor = new DefaultVisualVisitor();

        defaultVisualVisitor.visualizeDatabase(bitTexDatabase, resolverArgument.getRecords(), resolverArgument.getAuthors());
    }
}
