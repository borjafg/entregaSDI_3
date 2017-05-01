package uo.sdi.rest;

import java.util.HashSet;
import java.util.Set;

<<<<<<< HEAD
import uo.sdi.filters.RestFilter;
=======
>>>>>>> 622bef857d0c655af153cebdba985cc0a47b63c4
import uo.sdi.rest.impl.TaskServiceRestImpl;
import uo.sdi.rest.impl.UserServiceRestImpl;

public class Application extends javax.ws.rs.core.Application {

    @Override
    public Set<Class<?>> getClasses() {
	Set<Class<?>> res = new HashSet<Class<?>>();

	res.add(UserServiceRestImpl.class);
	res.add(TaskServiceRestImpl.class);

<<<<<<< HEAD
	res.add(RestFilter.class);

=======
>>>>>>> 622bef857d0c655af153cebdba985cc0a47b63c4
	return res;
    }

}