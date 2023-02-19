/**
 * @kind graph
 */

import java
import semmle.code.java.ControlFlowGraph
import semmle.code.java.controlflow.BasicBlocks

predicate locateAt(ControlFlowNode b, ControlFlowNode c, string m, string f) {
  b.getEnclosingCallable().getName() = m
  and b.getLocation().getFile().getBaseName() = f
  and c.getEnclosingCallable().getName() = m
  and c.getLocation().getFile().getBaseName() = f
}


from ControlFlowNode b1, ControlFlowNode b2

where locateAt(b1, b2, "write", "Streams.java") and b1.getASuccessor() = b2

select b1, b2