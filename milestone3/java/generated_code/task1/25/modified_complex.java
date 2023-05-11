@Override
public Feed toObject() throws JAXBException {
    boolean hasUpgraded = false;
    int attempts = 0;
    Feed feed = null;

    while (attempts < 3 && !hasUpgraded) {
        try {
            this.upgradeToLatestVersion();
            feed = (Feed) ImmobiliareItUtils.createUnmarshaller().unmarshal(this.getDocument());
            hasUpgraded = true;
        } catch (JAXBException e) {
            // handle JAXBException
            if (attempts < 2) {
                log.error("Failed to unmarshal the feed. Retrying after upgrading the version.");
                attempts++;
            } else {
                throw new JAXBException("Failed to unmarshal the feed.", e);
            }
        } catch (Exception e) {
            // handle any other exceptions
            throw new RuntimeException("Failed to upgrade the version or unmarshal the feed.", e);
        }
    }

    if (feed == null) {
        throw new JAXBException("Failed to upgrade the version and unmarshal the feed.");
    }

    return feed;
}