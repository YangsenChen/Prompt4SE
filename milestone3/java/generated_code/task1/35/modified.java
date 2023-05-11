public static void authorizeAccount(final BraintreeFragment fragment, final boolean vault, final String profileId)
        throws AppSwitchNotAvailableException {
    
    Configuration configuration = fragment.getConfiguration();

    // Check if Venmo is enabled and installed
    String exceptionMessage = "";
    if (!configuration.getPayWithVenmo().isAccessTokenValid()) {
        exceptionMessage = "Venmo is not enabled";
    } else if (!Venmo.isVenmoInstalled(fragment.getApplicationContext())) {
        exceptionMessage = "Venmo is not installed";
    }

    // Throw an exception if there is an error or continue with the authorization
    if (!TextUtils.isEmpty(exceptionMessage)) {
        throw new AppSwitchNotAvailableException(exceptionMessage);
    } else {
        fragment.sendAnalyticsEvent("pay-with-venmo.selected");

        // Use merchant ID from configuration if profile ID is empty
        String venmoProfileId = profileId;
        if (TextUtils.isEmpty(venmoProfileId)) {
            venmoProfileId = configuration.getPayWithVenmo().getMerchantId();
        }

        // Persist vault option for client token authorization
        if (vault && fragment.getAuthorization() instanceof ClientToken) {
            persistVenmoVaultOption(true, fragment.getApplicationContext());
        }

        // Start the Venmo app switch activity
        fragment.startActivityForResult(getLaunchIntent(configuration.getPayWithVenmo(), venmoProfileId, fragment),
                BraintreeRequestCodes.VENMO);
        fragment.sendAnalyticsEvent("pay-with-venmo.app-switch.started");
    }
}