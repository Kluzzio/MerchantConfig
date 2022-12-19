package kluzzio.merchantconfig.config;

import java.util.Map;

public class Int2ObjectMapConfigObject {

    //Map<Integer, Map<String, Map<String, String>>>;
    //Map<Level, Map<Factory, Map<ParamName, ParamValue>>>

    public Map<Integer, FactoryMapConfigObject> LeveledTradesMap;

    public Int2ObjectMapConfigObject(Map<Integer, FactoryMapConfigObject> leveledTradesMap) {
        this.LeveledTradesMap = leveledTradesMap;
    }

    public Map<Integer, FactoryMapConfigObject> getLeveledTradesMap() {
        return LeveledTradesMap;
    }
}
