/**
 * @kind graph
 */

import python

from ControlFlowNode entry,ControlFlowNode node,ControlFlowNode node2
where entry = node.getAPredecessor() and node2 = node.getASuccessor()
select entry.toString(),node.toString(),node2.toString()