public class ProcessorFactory {
    public DocumentProcessor createProcessor(String fileName) {
        if (fileName == null || !fileName.contains(".")) {
            throw new IllegalArgumentException("Invalid file name. Missing extension.");
        }

        // Extract the file extension (e.g., "report.pdf" -> "pdf")
        String extension = fileName.substring(fileName.lastIndexOf(".")).toLowerCase();

        switch (extension) {
            case ".docx":
                return new DocxProcessor();
            case ".pdf":
                return new PdfProcessor();
            case ".txt":
                return new TxtProcessor();
            default:
                throw new IllegalArgumentException("Unsupported file format: " + extension);
        }
    }
}
