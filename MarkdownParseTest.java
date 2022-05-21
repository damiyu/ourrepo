import static org.junit.Assert.*;
import org.junit.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class MarkdownParseTest {

    List<String> emptyString;

    @Before
    public void setUp() {
        emptyString = new ArrayList<>();
    }

    @Test
    public void addition() {
        assertEquals(2, 1 + 1);
    }
    
    @Test
    public void testGetLinks() throws IOException {
        List<String> testFileLinks = List.of("https://something.com", 
                                            "some-thing.html");
        Path testFile = Path.of("test-file.md");
        String testFileContent = Files.readString(testFile);
        assertEquals(MarkdownParse.getLinks(testFileContent), testFileLinks);
    }

    @Test
    public void testBrackets() throws IOException {
        List<String> testFileLinks = new ArrayList<>();
        Path testFile = Path.of("test-brackets.md");
        String testFileContent = Files.readString(testFile);
        assertEquals(MarkdownParse.getLinks(testFileContent), testFileLinks);
    }

    @Test
    public void testNewLine() throws IOException {
        List<String> testFileLinks = List.of("https://google.com");
        Path testFile = Path.of("test-extra-line.md");
        String testFileContent = Files.readString(testFile);
        assertEquals(MarkdownParse.getLinks(testFileContent), testFileLinks);
    }

    @Test
    public void testParentheses() throws IOException {
        List<String> testFileLinks = List.of("http://en.wikipedia.org/wiki/PC_Tools_(Central_Point_Software)");
        Path testFile = Path.of("test-parentheses-in-link.md");
        String testFileContent = Files.readString(testFile);
        assertEquals(MarkdownParse.getLinks(testFileContent), testFileLinks);
    }
    
    @Test
    public void testFile1() throws IOException {
        List<String> testFileLinks = List.of("https://something.com", "some-thing.html");
        Path testFile = Path.of("test-files/test-file.md");
        String testFileContent = Files.readString(testFile);
        assertEquals(MarkdownParse.getLinks(testFileContent), testFileLinks);
    }

    @Test
    public void testFile2() throws IOException {
        List<String> testFileLinks = List.of("https://something.com", "some-page.html");
        Path testFile = Path.of("test-files/test-file2.md");
        String testFileContent = Files.readString(testFile);
        assertEquals(MarkdownParse.getLinks(testFileContent), testFileLinks);
    }

    @Test
    public void testFile3() throws IOException {
        Path testFile = Path.of("test-files/test-file3.md");
        String testFileContent = Files.readString(testFile);
        assertEquals(MarkdownParse.getLinks(testFileContent), emptyString);
    }

    @Test
    public void testFile4() throws IOException {
        Path testFile = Path.of("test-files/test-file4.md");
        String testFileContent = Files.readString(testFile);
        assertEquals(MarkdownParse.getLinks(testFileContent), emptyString);
    }

    @Test
    public void testFile5() throws IOException {
        Path testFile = Path.of("test-files/test-file5.md");
        String testFileContent = Files.readString(testFile);
        assertEquals(MarkdownParse.getLinks(testFileContent), emptyString);
    }
    
    @Test
    public void testFile6() throws IOException {
        List<String> testFileLinks = List.of("page.com");
        Path testFile = Path.of("test-files/test-file6.md");
        String testFileContent = Files.readString(testFile);
        assertEquals(MarkdownParse.getLinks(testFileContent), testFileLinks);
    }

    @Test
    public void testFile7() throws IOException {
        Path testFile = Path.of("test-files/test-file7.md");
        String testFileContent = Files.readString(testFile);
        assertEquals(MarkdownParse.getLinks(testFileContent), emptyString);
    }
    
    @Test
    public void testFile8() throws IOException {
        List<String> testFileLinks = List.of("a link on the first line");
        Path testFile = Path.of("test-files/test-file8.md");
        String testFileContent = Files.readString(testFile);
        assertEquals(MarkdownParse.getLinks(testFileContent), testFileLinks);
    }
    
    @Test
   public void snippetOneTest() throws IOException {
    Path fileName = Path.of("snippet1.md");
    String content = Files.readString(fileName);
    ArrayList<String> links = MarkdownParse.getLinks(content);

    ArrayList<String> expected = new ArrayList<String>();
    expected.add("`google.com");
    expected.add("google.com");
    expected.add("ucsd.edu");

    assertEquals(expected, links);
   }

   @Test
   public void snippetTWoTest() throws IOException {
    Path fileName = Path.of("snippet2.md");
    String content = Files.readString(fileName);
    ArrayList<String> links = MarkdownParse.getLinks(content);

    ArrayList<String> expected = new ArrayList<String>();
    expected.add("a.com");
    expected.add("a.com(())");
    expected.add("example.com");

    assertEquals(expected, links);
   }

   @Test
   public void snippetThreeTest() throws IOException {
    Path fileName = Path.of("snippet3.md");
    String content = Files.readString(fileName);
    ArrayList<String> links = MarkdownParse.getLinks(content);

    ArrayList<String> expected = new ArrayList<String>();
    expected.add("https://www.twitter.com");
    expected.add("https://sites.google.com/eng.ucsd.edu/cse-15l-spring-2022/schedule");
    expected.add("https://cse.ucsd.edu/");

    assertEquals(expected, links);
   }
}