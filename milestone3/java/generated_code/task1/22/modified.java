public MicrodataDocument get(String url) throws MicrobrowserException
	{
		newUrl(url); // Data flow: calling the newUrl method with input url
		
		Response response = null;
		try
		{
			// Control flow: making a network call using Jsoup.connect to fetch a response
			response = Jsoup.connect(url)
				.method(Method.GET)
				.ignoreHttpErrors(true)
				.execute();
		}
		catch (IOException exception)
		{
			throw new MicrobrowserException("Error fetching page: " + url, exception); // Control flow: throw an exception if network call fails
		}
		
		// Data flow: creating a new MicrodataDocument object by passing an empty Map and response as parameters
		return new JsoupMicrodataDocument(Collections.<String, String>emptyMap(), response);
	}