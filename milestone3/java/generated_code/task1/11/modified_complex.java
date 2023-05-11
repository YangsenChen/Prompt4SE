boolean hasActivityAndNotEndOfStream(final long nowNs, final List<Packet> packets, final int maxConsecutiveEmptyPackets, final boolean streamPaused, final boolean streamStopped) {
    int numConsecutiveEmptyPackets = 0;
    boolean hasActivity = false;

    // check if stream is paused or stopped
    if (streamPaused || streamStopped) {
        return false;
    }

    // iterate through packets and check if any packets have been received
    for (final Packet packet : packets) {
        if (packet != null) {
            numConsecutiveEmptyPackets = 0;
            if (packet.isLivenessPacket()) {
                lastPacketTimestampNs = nowNs;
            } else {
                hasActivity = true;
            }
        } else {
            numConsecutiveEmptyPackets++;
        }

        // check if too many consecutive empty packets have been received
        if (numConsecutiveEmptyPackets > maxConsecutiveEmptyPackets) {
            hasActivity = false;
            break;
        }
    }

    // check if end of stream has been reached
    final boolean noMorePacketsExpected = packets.size() == 0 || packets.get(packets.size() - 1).isEndOfStreamPacket();
    final boolean endOfStreamPacketReceived = noMorePacketsExpected && packets.size() > 0 && packets.get(packets.size() - 1).receiptTimestampNs() > 0;
    final boolean isEndOfStream = noMorePacketsExpected && endOfStreamPacketReceived;
    if (isEndOfStream) {
        rebuildPosition.setVolatile(hwmPosition.get());
    }

    boolean isActive = hasActivity && !isEndOfStream;
    return isActive;
}