package kluzzio.merchantconfig.config;

import java.util.Map;

public class TradesConfigObject {

    //Map<String, Map<Integer, Map<String, Map<String, String>[]>>>;
    //Map<Profession, Map<Level, Map<Factory, Map<ParamName, ParamValue>>>>

    public Map<String, Int2ObjectMapConfigObject> ProfessionMap;

    public TradesConfigObject(Map<String, Int2ObjectMapConfigObject> professionMap) {
        this.ProfessionMap = professionMap;
    }

    public Map<String, Int2ObjectMapConfigObject> getProfessionMap() {
        return ProfessionMap;
    }
}
