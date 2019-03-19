package core;

import core.visualize.VisualVisitor;


/**
 * Basic entity of the recrord
 */
public interface BibTeXEntity {

    /**
     *
     * @param visitor that visits all records fields
     */
    void accept(VisualVisitor visitor);

}
