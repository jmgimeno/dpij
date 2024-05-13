package app.visitor;

/*
 * Copyright (c) 2001, 2005. Steven J. Metsker.
 * 
 * Steve Metsker makes no representations or warranties about
 * the fitness of this software for any particular purpose, 
 * including the implied warranty of merchantability.
 *
 * Please use this software as you wish with the sole
 * restriction that you may not claim that you wrote it.
 */

import com.oozinoz.machine.*;
import java.util.*;

/**
 * This class uses the visitor mechanics of the machine hierarchy to add a
 * behavior that finds a particular machine within a machine composite.
 * 
 * @author Steven J. Metsker
 * @see app.visitor.ShowFindVisitor
 */
public class FindVisitor implements MachineVisitor {
    private int soughtId;

    private MachineComponent found;

    public FindVisitor(int soughtId) {
        this.soughtId = soughtId;
    }

    /**
     * @param mc the composite to look within
     * @param id the id of the machine to find
     * @return a machine with the given id, within the given machine composite
     */
    public static MachineComponent find(MachineComponent mc, int id) {
        FindVisitor visitor = new FindVisitor(id);
        mc.accept(visitor);
        return visitor.found;
    }

    /**
     * Check a particular machine to see if it's the one that is sought.
     */
    public void visit(Machine m) {
        if (found == null && m.getId() == soughtId) 
            found = m;
    }

    /**
     * Check if the provided composite is the sought machine component. If not,
     * check this composite's children.
     */
    public void visit(MachineComposite mc) {
        if (found == null && mc.getId() == soughtId) {
            found = mc;
            return;
        }
        Iterator<MachineComponent> iter = mc.getComponents().iterator();
        while (found == null && iter.hasNext()) 
            iter.next().accept(this);
    }
}
