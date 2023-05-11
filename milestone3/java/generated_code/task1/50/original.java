@Override
    public Immoxml toObject() throws JAXBException {
        this.upgradeToLatestVersion();
        return (Immoxml) ImmoXmlUtils.createUnmarshaller().unmarshal(this.getDocument());
    }