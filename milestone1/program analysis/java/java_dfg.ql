/**
 * @kind graph
 */
import java
import semmle.code.java.dataflow.DataFlow

class Conf extends DataFlow::Configuration {
  Conf() { this = "qqconf" }

  override predicate isSource(DataFlow::Node n) { any() }

  override predicate isSink(DataFlow::Node n) { any() }
}

predicate locateAt(DataFlow::Node n1, DataFlow::Node n2, string m, string f) {
  n1.getEnclosingCallable().getName() = m
  and n1.getLocation().getFile().getBaseName() = f
  and n2.getEnclosingCallable().getName() = m
  and n2.getLocation().getFile().getBaseName() = f
}

from Conf conf, DataFlow::Node src, DataFlow::Node sink

where locateAt(src, sink, "write", "Streams.java")

and conf.hasFlow(src, sink) and src != sink

select src, sink