package uo.sdi.infrastructure;


public class Services {

    public static ServicesFactory getServicesFactory() {
	return (ServicesFactory) FactoryHelper.createFactory(
		"factories.properties", "services_factory");
    }

}