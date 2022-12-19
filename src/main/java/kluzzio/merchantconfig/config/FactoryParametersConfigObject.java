package kluzzio.merchantconfig.config;

import java.util.Map;

public class FactoryParametersConfigObject {

    //Map<ParamName, ParamValue>

    public Map<String, String> FactoryParameters;

    public FactoryParametersConfigObject(Map<String, String> factoryParameters) {
        this.FactoryParameters = factoryParameters;
    }

    public Map<String, String> getFactoryParameters() {
        return FactoryParameters;
    }
}
