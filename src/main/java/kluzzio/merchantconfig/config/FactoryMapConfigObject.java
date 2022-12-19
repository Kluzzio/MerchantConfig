package kluzzio.merchantconfig.config;

import java.util.Map;

public class FactoryMapConfigObject {

    //Map<String, Map<String, String>[]>;
    //Map<Factory, Map<ParamName, ParamValue>[]>

    public Map<String, FactoryParametersConfigObject[]> FactoryMap;

    public FactoryMapConfigObject(Map<String, FactoryParametersConfigObject[]> factoryMap) {
        this.FactoryMap = factoryMap;
    }

    public Map<String, FactoryParametersConfigObject[]> getFactoryMap() {
        return FactoryMap;
    }
}
