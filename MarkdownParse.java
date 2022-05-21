//https://howtodoinjava.com/java/io/java-read-file-to-string-examples/

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class MarkdownParse {

    public static int getIndexOfLastParen(String markdown, int openParen) {
        int closeParen = markdown.indexOf(")", openParen);
        int lastParen = closeParen + 1;
        while (lastParen < markdown.length()) {
            if (markdown.substring(lastParen, lastParen + 1).equals(" ")) {
                break;
            }
            if (markdown.substring(lastParen, lastParen + 1).equals("\n")) {
                break;
            }
            lastParen = markdown.indexOf(")", lastParen);
            if (lastParen < 0) { break; }
            else { closeParen = lastParen; }
            lastParen = closeParen + 1;
        }
        return closeParen;
    }

    public static ArrayList<String> getLinks(String markdown) {
        ArrayList<String> toReturn = new ArrayList<>();
        // find the next [, then find the ], then find the (, then read link upto next )
        int currentIndex = 0;
        while(currentIndex < markdown.length()) {
            int openBracket = markdown.indexOf("[", currentIndex);
            int closeBracket = markdown.indexOf("]", openBracket);
            int openParen = markdown.indexOf("(", closeBracket);
            int closeParen = getIndexOfLastParen(markdown, openParen);
            if (openBracket < 0 || closeBracket < 0 
                || openParen < 0 || closeParen < 0) { 
                    break;
            }
            if (openParen == closeBracket + 1) {
                toReturn.add(markdown.substring(openParen + 1, closeParen));
                currentIndex = closeParen + 1;
            }
            else {
                currentIndex = closeBracket + 1;
            }
        }
        return toReturn;
    }


    public static void main(String[] args) throws IOException {
        Path fileName = Path.of(args[0]);
        String content = Files.readString(fileName);
        ArrayList<String> links = getLinks(content);
        System.out.println(links);
        // findIndexNewLines();
    }
    
    public static void findIndexNewLines() throws IOException {
        String content = Files.readString(Path.of("newlines.md"));
        for (int i = 0; i < content.length(); i++) {
            if (content.charAt(i) == '\n') {
                System.out.println("charAt: " + i);
            }
            if (content.substring(i, i + 1).equals("\n")) {
                System.out.println("substring: " + i);
            }
        }
    }
}