CLASSPATH=lib/*:.

test: MarkdownParse.class MarkdownParseTest.class
	java -cp CLASSPATH org.junit.runner.JUnitCore MarkdownParseTest

MarkdownParse.class: MarkdownParse.java
	javac MarkdownParse.java

MarkdownParseTest.class: MarkdownParse.class MarkdownParseTest.java
	javac -cp CLASSPATH MarkdownParseTest.java