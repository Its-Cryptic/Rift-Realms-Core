package dev.riftrealmsteam.riftrealmscore.api.util;

public class ClientInfo {
    private ClientInfo() {
    }
    public static long serverTime;
    public static long levelTime;

    public static void setServerTime(long serverTime) {
        ClientInfo.serverTime = serverTime;
    }

    public static void setLevelTime(long levelTime) {
        ClientInfo.levelTime = levelTime;
    }
}
