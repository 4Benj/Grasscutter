package emu.grasscutter.server.game.version;

import emu.grasscutter.Grasscutter;
import java.util.HashMap;

public class VersionHandler {
    private static HashMap<String, GameVersion> GameVersionsMap = new HashMap<>();

    private static GameVersion GetGameVersion(String versionId) {
        return GameVersionsMap.get(versionId);
    }

    private static void AddGameVersion(GameVersion version) {
        for (String versionId : version.getGameVersions()) {
            if (!GameVersionsMap.containsKey(versionId)) {
                Grasscutter.getLogger().error("CONFLICTING GAME VERSION DEFINITIONS %s", versionId);
                continue;
            }
            GameVersionsMap.put(versionId, version);
        }
    }
}
