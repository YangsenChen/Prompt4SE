@Override
    public Feed toObject() throws JAXBException {
        this.upgradeToLatestVersion();
        return (Feed) ImmobiliareItUtils.createUnmarshaller().unmarshal(this.getDocument());
    }