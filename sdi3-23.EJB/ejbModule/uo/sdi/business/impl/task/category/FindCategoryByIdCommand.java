package uo.sdi.business.impl.task.category;

import uo.sdi.business.exception.BusinessException;
import uo.sdi.business.impl.Command;
import uo.sdi.dto.CategoryDTO;
import uo.sdi.dto.DTOadapter;
import uo.sdi.persistence.CategoryFinder;

public class FindCategoryByIdCommand implements Command<CategoryDTO> {

    private Long id;

    public FindCategoryByIdCommand(Long id) {
	this.id = id;
    }

    @Override
    public CategoryDTO execute() throws BusinessException {
	return DTOadapter.categoryToDTO(CategoryFinder.findById(id));
    }

}