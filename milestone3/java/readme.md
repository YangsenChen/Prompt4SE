1


['1', '4', '6', '7', '13', '14', '18', '19', '28', '30', '31', '33', '36', '38', '39', '45', '49']

```mermaid
graph LR
    input_a(a) --> abs_b
    input_b(b) --> abs_b
    abs_b["abs(b)"] --> iterations
    iterations --> range_iterations
    range_iterations["range(iterations)"] --> gen_expr
    gen_expr["(a for _ in range(iterations))"] --> sum_gen_expr
    sum_gen_expr["sum(gen_expr)"] --> positive_result
    input_b --> b_comparison
    b_comparison["b >= 0"] --> cond_expr
    positive_result --> cond_expr
    cond_expr["result = positive_result if b_comparison else -positive_result"] --> output_result
    output_result[result] -.-> output

```

```mermaid
graph LR
A[Class clazz] --> B[Logger logger]
B --> C[logger == null]
C --> D["Logger.getLogger(clazz.getName())"]
D --> E["logger.setLevel(Level.ALL)"]
D --> F["loggers.put(clazz, logger)"]
C --> G[return logger]
F --> G
E --> G

```

```mermaid
graph LR
A[Class clazz] --> B["loggers.containsKey(clazz)"]
B --> C["Logger logger = loggers.get(clazz)"]
B --> D["Logger.getLogger(clazz.getName())"]
D --> E["logger.setLevel(Level.ALL)"]
D --> F["loggers.put(clazz, logger)"]
C --> G[return logger]
F --> G
E --> G

```

<plugin>
  <groupId>org.pitest</groupId>
  <artifactId>pitest-maven</artifactId>
  <version>1.13.0</version>
</plugin>

mvn test-compile org.pitest:pitest-maven:mutationCoverage

first mark the line number for each line of code, like below:

public class App //line-1:
{ // line-2:
public static void main( String[] args ) // line-3:
    { // line-4:
    System.*out*.println( "Hello World!" );// line-5:
	} //line-6:
} // line-7:

ok, reasoning this code line by line, starting from the main method,  when the parse method is called inside the main method, jump into it and keep reasoning the parse method line by line, then jump out and keep  going on. You should perform like a Java Debugger and try to estimate  each internal variable

output in this format:

main: line-xx: (what it does, and what may be the variable value here)
....
main: line-yy : jump into parse
parse: line-xx: ...
parse: line-yy: jump back to main method
