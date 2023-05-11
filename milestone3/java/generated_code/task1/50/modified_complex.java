@Override
public Immoxml toObject(boolean skipValidation) throws JAXBException {
    Document document = this.getDocument();
    
    // Validate document if flag is not set
    if (!skipValidation) {
        validateDocument(document);
    }
    
    // Create unmarshaller
    Unmarshaller unmarshaller = ImmoXmlUtils.createUnmarshaller();
    
    // Set custom validation handler on unmarshaller
    ValidationHandler validationHandler = new ValidationHandler();
    unmarshaller.setEventHandler(validationHandler);
    
    // Unmarshal document using unmarshaller
    Immoxml immoxml = (Immoxml) unmarshaller.unmarshal(document);
    
    // Check if validation succeeded
    if (!validationHandler.isValid()) {
        throw new JAXBException("XML Validation failed - see logs for details");
    }

    // Upgrade object to latest version
    immoxml = upgradeToLatestVersion(immoxml);

    // Return upgraded object
    return immoxml;
}

// Validate XML document
private void validateDocument(Document document) throws JAXBException {
    // Validation logic here
    // ...
}

// Upgrade object to latest version
private Immoxml upgradeToLatestVersion(Immoxml immoxml) throws JAXBException {
    // Upgrade logic here
    // ...
    
    // Return upgraded object
    return upgradedImmoxml;
}