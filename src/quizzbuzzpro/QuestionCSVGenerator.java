package quizzbuzzpro;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class QuestionCSVGenerator {
    public static void main(String[] args) {
        String filename = "questions.csv";

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            // ✅ Java Questions
            bw.write("Java,What is JVM?,Java Virtual Machine,Java Value Manager,Java Vendor Machine,A\n");
            bw.write("Java,Which keyword is used to inherit a class?,extends,implements,inherits,A\n");
            bw.write("Java,Which method is the entry point in Java?,main(),start(),run(),A\n");
            bw.write("Java,Size of int in Java?,2 bytes,4 bytes,8 bytes,B\n");
            bw.write("Java,Can we overload the main method?,Yes,No,Only once,A\n");
            bw.write("Java,Which is not an OOP principle?,Inheritance,Encapsulation,Compilation,C\n");
            bw.write("Java,Which operator is used for object reference?,->,.,::,B\n");
            bw.write("Java,Which collection allows duplicates?,Set,Map,List,C\n");
            bw.write("Java,String is ___?,Immutable,Mutable,Constant,A\n");
            bw.write("Java,What does JVM stand for?,Java Virtual Machine,Java Vendor Model,Java Verified Mode,A\n");

            // ✅ Python Questions
            bw.write("Python,What is the output of 3*'abc'?,abcabcabc,abc3,Error,A\n");
            bw.write("Python,Which data type is immutable?,List,Set,Tuple,C\n");
            bw.write("Python,What does len() return?,Length of string,list index,Sum of list,A\n");
            bw.write("Python,What symbol is used for comment?,#,//,<!--,A\n");
            bw.write("Python,Which keyword is used for function?,def,func,define,A\n");
            bw.write("Python,Which module is used for math operations?,math,cmath,random,A\n");
            bw.write("Python,Which loop is not present in Python?,for,foreach,while,B\n");
            bw.write("Python,Python is ___ typed?,Statically,Dynamically,Manually,B\n");
            bw.write("Python,How to define a dictionary?,{},[],(),A\n");
            bw.write("Python,Which of the following is not keyword?,elif,pass,define,C\n");

            // ✅ SQL Questions
            bw.write("SQL,SQL stands for?,Structured Query Language,Simple Query Logic,System Query Layer,A\n");
            bw.write("SQL,Which clause is used for filtering?,WHERE,SELECT,FROM,A\n");
            bw.write("SQL,Which function gives row count?,SUM(),AVG(),COUNT(),C\n");
            bw.write("SQL,What does PRIMARY KEY mean?,Unique & Not Null,Duplicate Allowed,Nullable,A\n");
            bw.write("SQL,To sort records use?,ORDER BY,GROUP BY,HAVING,A\n");
            bw.write("SQL,Which clause groups rows?,ORDER BY,GROUP BY,WHERE,B\n");
            bw.write("SQL,To get unique values use?,DISTINCT,UNIQUE,ONLY,A\n");
            bw.write("SQL,Which join gives all rows from both?,INNER JOIN,LEFT JOIN,FULL OUTER JOIN,C\n");
            bw.write("SQL,Which keyword updates data?,CHANGE,MODIFY,UPDATE,C\n");
            bw.write("SQL,Which function gives max value?,HIGHEST(),MAX(),GREATEST(),B\n");

            // 🔁 Add more if needed (optional extra)

            System.out.println("✅ questions.csv generated successfully in your project folder!");

        } catch (IOException e) {
            System.out.println("❌ Error writing CSV file: " + e.getMessage());
        }
    }
}
