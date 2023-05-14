public static void authorizeAccount(final BraintreeFragment fragment, final boolean vault, final String profileId,
                                     final PaymentMethodNonceCallback callback) {
    Configuration configuration = fragment.getConfiguration();

    // Check if Venmo is enabled and installed
    if (!configuration.getPayWithVenmo().isAccessTokenValid()) {
        callback.onResult(null, new AppSwitchNotAvailableException("Venmo is not enabled"));
        fragment.sendAnalyticsEvent("pay-with-venmo.app-switch.failed");
        return;
    } else if (!Venmo.isVenmoInstalled(fragment.getApplicationContext())) {
        callback.onResult(null, new AppSwitchNotAvailableException("Venmo is not installed"));
        fragment.sendAnalyticsEvent("pay-with-venmo.app-switch.failed");
        return;
    }

    fragment.waitForConfiguration(new ConfigurationListener() {
        @Override
        public void onConfigurationFetched(Configuration configuration) {
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

            // Listen for the result of the Venmo app switch
            fragment.addListener(new BraintreeListener() {
                @Override
                public void onPaymentMethodNonceCreated(PaymentMethodNonce paymentMethodNonce) {
                    if (paymentMethodNonce instanceof VenmoAccountNonce) {
                        callback.onResult(paymentMethodNonce, null);
                    } else {
                        callback.onResult(null, new AppSwitchNotAvailableException("Invalid nonce returned"));
                    }
                    fragment.sendAnalyticsEvent("pay-with-venmo.app-switch.succeeded");
                }

                @Override
                public void onCancel(int requestCode) {
                    callback.onResult(null, new AppSwitchNotAvailableException("App switch canceled"));
                    fragment.sendAnalyticsEvent("pay-with-venmo.app-switch.canceled");
                }

                @Override
                public void onError(Exception error) {
                    callback.onResult(null, error);
                    fragment.sendAnalyticsEvent("pay-with-venmo.app-switch.failed");
                }
            });
        }
    });
}