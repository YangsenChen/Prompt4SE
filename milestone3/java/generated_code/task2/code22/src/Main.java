

public class Main {
    public static void main(String[] args) {
        // Example usage of the get method in the main method
        MicrodataDocument document = get("https://example.com");
        System.out.println(document);
    }

    public MicrodataDocument get(String url)
    {
        newUrl(url);

        try
        {
            Response response = Jsoup.connect(url)
                    .method(Method.GET)
                    .ignoreHttpErrors(true)
                    .execute();

            return new JsoupMicrodataDocument(Collections.<String, String>emptyMap(), response);
        }
        catch (IOException exception)
        {
            throw new MicrobrowserException("Error fetching page: " + url, exception);
        }
    }

}