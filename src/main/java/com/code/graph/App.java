package com.code.graph;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseException;
import com.github.javaparser.ast.CompilationUnit;
import java.io.*;
import java.util.*;

import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import org.apache.commons.lang3.StringEscapeUtils;

/**
 * Team 1
 * Code Graph
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException, ParseException {

        String status = "Status: Testing method visitor.";
        System.out.println(status);

        // creates an input stream for the file to be parsed
        FileInputStream in = new FileInputStream("/Users/Beto/Desktop/Factorial.java");//If you guys are going to make changes, comment this out and add your own file. before pushing make sure to leave this original one and comment the other out.!
        CompilationUnit cu;
        try {
            // parse the file
            cu = JavaParser.parse(in);
        } finally {
            in.close();
        }

        /* prints the resulting compilation unit to default system output
        // System.out.println("Printing out contents of class");
        // System.out.println(cu.toString()); */

        System.out.println("Visiting Methods in Factorial.java");
        new MethodVisitor().visit(cu, null);

    }

    /**
     * Simple visitor implementation for visiting MethodDeclaration nodes.
     */
    private static class MethodVisitor extends VoidVisitorAdapter {

        @Override
        public void visit(MethodDeclaration n, Object arg) {
            // here you can access the attributes of the method.
            // this method will be called for all methods in this
            // CompilationUnit, including inner class methods
            System.out.println(n.getName());//Gets method names.
            System.out.println(n.getBody());//Gets contents of method.

            //Test to see if we can get contents of method and set them in a block.
            System.out.print("Statements are: ");
            BlockStmt block = new BlockStmt();
            block = n.getBody();
            System.out.println(block.getStmts());
            //Ok so, in Java blocks are executed as a single statement. That's why if you run this, you can see the statement separated by commas and a block is seen as a statement.
            //What's a block in java? https://docs.oracle.com/javase/tutorial/java/nutsandbolts/expressions.html

            super.visit(n, arg);//Makes a note of the methods visited by marking them through VoidVisitorAdapter


        }
    }

}
