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