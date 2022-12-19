package kluzzio.merchantconfig.config;

import java.util.Map;

public class SettingsConfigurationObject {

    public Map<String, ProfessionSettings> ProfessionMap;

    public SettingsConfigurationObject(Map<String, ProfessionSettings> professionMap) {
        this.ProfessionMap = professionMap;
    }

    public Map<String, ProfessionSettings> getProfessionMap() {
        return ProfessionMap;
    }

    public static class ProfessionSettings {

        public int maxLevel;
        public Map<Integer, Integer> numOfTradesGainedPerLevel;

        public ProfessionSettings(int maxLevel, Map<Integer, Integer> numOfTradesGainedPerLevel) {
            this.maxLevel = maxLevel;
            this.numOfTradesGainedPerLevel = numOfTradesGainedPerLevel;
        }

        public int getMaxLevel() {
            return maxLevel;
        }

        public Map<Integer, Integer> getNumOfTradesGainedPerLevel() {
            return numOfTradesGainedPerLevel;
        }
    }
}
