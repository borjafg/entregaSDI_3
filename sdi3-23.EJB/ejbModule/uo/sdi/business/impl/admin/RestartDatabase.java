package uo.sdi.business.impl.admin;

import uo.sdi.business.exception.BusinessException;
import uo.sdi.business.impl.Command;
import uo.sdi.management.DatabaseManager;

public class RestartDatabase implements Command<Void> {

    @Override
    public Void execute() throws BusinessException {
	DatabaseManager.deleteAndInsertData();

	return null;
    }

}