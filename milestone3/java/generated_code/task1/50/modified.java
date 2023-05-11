@Override
public Immoxml toObject() throws JAXBException {
    // Create unmarshaller
    Unmarshaller unmarshaller = ImmoXmlUtils.createUnmarshaller();
    
    // Unmarshal document using unmarshaller
    Immoxml immoxml = (Immoxml) unmarshaller.unmarshal(this.getDocument());

    // Upgrade object to latest version
    this.upgradeToLatestVersion(immoxml);

    // Return upgraded object
    return immoxml;
}

// Upgrade object to latest version
private void upgradeToLatestVersion(Immoxml immoxml) {
    // Upgrade logic here
    // ...
}