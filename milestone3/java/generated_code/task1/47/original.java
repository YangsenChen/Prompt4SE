public static WebsockifySslContext getInstance(String keystore, String password, String keyPassword) {
    	WebsockifySslContext context = SingletonHolder.INSTANCE_MAP.get(keystore);
    	if ( context == null )
    	{
    		context = new WebsockifySslContext ( keystore, password, keyPassword );
    		SingletonHolder.INSTANCE_MAP.put(keystore, context);
    	}
    	return context;
    }