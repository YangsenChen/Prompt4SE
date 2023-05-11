boolean hasActivityAndNotEndOfStream(final long nowNs)
{
    boolean isActive;

    long timeoutThreshold = lastPacketTimestampNs + imageLivenessTimeoutNs;
    boolean areLivenessPacketsTimedOut = timeoutThreshold - nowNs < 0;

    boolean isStreamComplete = isEndOfStream && rebuildPosition.getVolatile() >= hwmPosition.get();

    if (areLivenessPacketsTimedOut || isStreamComplete) {
        isActive = false;
    } else {
        isActive = true;
    }

    return isActive;
}