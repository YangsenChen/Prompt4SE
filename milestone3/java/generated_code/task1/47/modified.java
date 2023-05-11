public static WebsockifySslContext getInstance(String keystore, String password, String keyPassword) {
	WebsockifySslContext context;
	synchronized (SingletonHolder.INSTANCE_MAP) {
		context = SingletonHolder.INSTANCE_MAP.get(keystore);
		if (context == null) {
			SingletonHolder.INSTANCE_MAP.put(keystore, new WebsockifySslContext(keystore, password, keyPassword));
			context = SingletonHolder.INSTANCE_MAP.get(keystore);
		}
	}
	return context;
}