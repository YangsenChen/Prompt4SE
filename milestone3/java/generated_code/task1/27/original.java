public void externalize(DataOutputStream sink) throws IOException {
        arrayVar.externalize(sink);
        for (Enumeration e = mapVars.elements(); e.hasMoreElements();) {
            ClientIO bt = (ClientIO) e.nextElement();
            bt.externalize(sink);
        }
    }