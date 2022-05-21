MARKDOWNPARSE=MarkdownParse.java
if [ -f "$MARKDOWNPARSE" ]
then
    echo "$MARKDOWNPARSE exists."
else
    echo "$MARKDOWNPARSE does not exist."
fi

make test