@Override
public Feed toObject() throws JAXBException {
    Feed feed;
    try {
        this.upgradeToLatestVersion();
        feed = (Feed) ImmobiliareItUtils.createUnmarshaller().unmarshal(this.getDocument());
    } catch (JAXBException e) {
        // handle JAXBException
        throw e;
    } catch (Exception e) {
        // handle any other exceptions
        throw new RuntimeException(e.getMessage(), e);
    }
    return feed;
}