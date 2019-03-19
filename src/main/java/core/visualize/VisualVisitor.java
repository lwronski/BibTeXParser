package core.visualize;

import core.records.*;

public interface VisualVisitor {

    void visit(Article article);

    void visit(Book book);

    void visit(Booklet booklet);

    void visit(Conference conference);

    void visit(InBook inBook);

    void visit(InCollection inCollection);

    void visit(InProceedings inProceedings);

    void visit(Manual manual);

    void visit(MastersThesis mastersThesis);

    void visit(Misc misc);

    void visit(PhdThesis phdThesis);

    void visit(Proceedings proceedings);

    void visit(TechReport techReport);

    void visit(Unpublished unpublished);

}
