/**
 * @kind graph
 */

import python
import semmle.python.dataflow.new.DataFlow

class MyAnalysisConfiguration extends DataFlow::Configuration {
  MyAnalysisConfiguration() { this = "MyAnalysisConfiguration" }
}

from DataFlow::Node source, DataFlow::Node sink
where DataFlow::localFlow(source, sink) and source.toString() != sink.toString()
select source.asExpr(),sink.asExpr()


