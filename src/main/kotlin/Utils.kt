
fun readLines(filePath: String) : List<String> =
    object {}.javaClass
        .getResourceAsStream("/$filePath")
        ?.bufferedReader()
        ?.readLines()
        ?: error("Could not read from $filePath")
