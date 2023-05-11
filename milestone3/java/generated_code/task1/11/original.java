boolean hasActivityAndNotEndOfStream(final long nowNs)
    {
        boolean isActive = true;

        if (((lastPacketTimestampNs + imageLivenessTimeoutNs) - nowNs < 0) ||
            (isEndOfStream && rebuildPosition.getVolatile() >= hwmPosition.get()))
        {
            isActive = false;
        }

        return isActive;
    }