/*
 * Copyright 2025 Vonage
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;

public final class AggregateSnippets {

    public static void main(String... args) throws Throwable {
        final var repoRoot = Paths.get("").toAbsolutePath();
        final var snippetsSrcRoot = repoRoot.resolve("src/main/java/com/vonage/quickstart");
        final var aggregator = new AggregateSnippets(snippetsSrcRoot);
        aggregator.computeContents();
        aggregator.saveContentsToFile(repoRoot.resolve("SNIPPETS.md"));
    }

    private StringBuilder sb;
    private final Path snippetsSrcRoot;

    public AggregateSnippets(Path snippetsSrcRoot) {
        this.snippetsSrcRoot = Objects.requireNonNull(snippetsSrcRoot);
    }

    private void checkComputed() {
        if (sb == null || sb.isEmpty()) {
            throw new IllegalStateException("Contents not computed yet.");
        }
    }

    public String getContents() {
        checkComputed();
        return sb.toString();
    }

    public void saveContentsToFile(Path destPath) throws IOException {
        Files.writeString(destPath, getContents(), StandardOpenOption.CREATE);
    }

    public void computeContents() throws IOException {
        final String classFileName = getClass().getSimpleName() + ".java";
        sb = new StringBuilder(1 << 17)
                .append("# Vonage Java SDK Code Snippets\n")
                .append("Here are all the snippets in this repository.\n")
                .append("This file was generated by running [").append(classFileName)
                .append("](src/main/java/").append(classFileName)
                .append(") from the root of the repository.")
                .append("\n\n## Contents");

        var allDirs = getAllDirsSorted();

        for (var file : allDirs) {
            var title = toHeadingTitle(file.getName());
            sb.append("\n- [**").append(title).append("**](#")
                    .append(title.toLowerCase().replace(' ', '-')).append(")");
        }
        sb.append("\n\n");

        for (var file : allDirs) {
            appendSnippetContent(file, 2);
        }
    }

    private List<File> getAllDirsSorted() {
        try (var pathStream = Files.list(snippetsSrcRoot)) {
            return pathStream.filter(Files::isDirectory)
                .map(Path::toFile)
                .sorted((f1, f2) -> {
                    if (isInitialize(f1)) return -1;
                    if (isInitialize(f2)) return 1;
                    return f1.getName().compareToIgnoreCase(f2.getName());
                })
                .toList();
        }
        catch (IOException ex) {
            System.err.println("Could not read "+snippetsSrcRoot+": " + ex.getMessage());
            return List.of();
        }
    }

    private void appendSnippetContent(File path, int level) throws IOException {
        var fileName = path.getName();
        if (path.isFile()) {
            fileName = fileName.substring(0, fileName.lastIndexOf('.'));
        }
        if (fileName.trim().length() < 3) return;

        sb.append("#".repeat(level)).append(' ').append(toHeadingTitle(fileName)).append('\n');
        if (path.isDirectory()) {
            for (var file : Objects.requireNonNull(path.listFiles())) {
                appendSnippetContent(file, level + 1);
            }
        }
        else if (level > 2 && path.getName().endsWith(".java")) {
            final String fileContent = Files.readString(path.toPath()),
                    clientInitEndStr = ".build();\n\n",
                    clientInitStartStr = "VonageClient client";

            final int clientInitStartIndex = fileContent.indexOf(clientInitStartStr) - 8,
                    clientInitEndIndex = fileContent.indexOf(clientInitEndStr) + 11,
                    endIndex = fileContent.lastIndexOf('}', fileContent.lastIndexOf('}') - 1) - 1,
                    startIndex = Math.min(endIndex, clientInitStartIndex > 0 ?
                            (isInitialize(path.getParentFile()) ? clientInitStartIndex : clientInitEndIndex) :
                            fileContent.indexOf('{', fileContent.indexOf('{') + 1) + 2
                    );

            final String nugget = fileContent.substring(startIndex, endIndex)
                    .stripTrailing().stripIndent().replace("\t", "    ");

            sb.append("\n```java\n").append(nugget).append("\n```\n");
        }
    }

    private static boolean isInitialize(File file) {
        return file.getName().equals("initialize");
    }

    private static String toHeadingTitle(String title) {
        var acronyms = new String[]{
                "jwt", "id", "uuid", "url", "sim",
                "sms", "rcs", "mms", "psd2", "dlr", "cnam",
                "dtmf", "asr", "tts", "ncco", "rtc"
        };
        var result = (title.substring(0, 1).toUpperCase() + title.substring(1))
                .replace("NCCO", "ncco")    // To avoid adding spaces in the next regex
                .replaceAll("(?<!^)([A-Z])(?![A-Z])", " $1")
                .replace("Vcard", "vCard")
                .replace("Numberinsight", "Number Insight")
                .replace("Verify\n", "Verify (Legacy)\n")
                .replace("Verify2", "Verify v2")
                .replace("Whatsapp", "WhatsApp")
                .replace("Simswap", "SIM Swap")
                .replace("Callncco", "Call NCCO");
        for (var ac : acronyms) {
            result = result.replaceAll("\\b(?i:"+ac+")\\b", ac.toUpperCase());
        }
        if ("Insight".equals(result)) {
            result = "Number Insight";
        }
        return result;
    }
}
