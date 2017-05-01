package uo.sdi.rest.responses;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import uo.sdi.dto.CategoryDTO;

@XmlRootElement(name = "categories_list")
public class ListCategoriesResponse {

    private List<CategoryDTO> categories;

    public ListCategoriesResponse() {

    }

    @XmlElement(name = "categories")
    public List<CategoryDTO> getCategories() {
	return categories;
    }

    public void setCategories(List<CategoryDTO> categories) {
	this.categories = categories;
    }

    // ===========================
    // hashCode, equals, toString
    // ===========================

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;

	result = prime * result
		+ ((categories == null) ? 0 : categories.hashCode());

	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;

	if (obj == null)
	    return false;

	if (getClass() != obj.getClass())
	    return false;

	ListCategoriesResponse other = (ListCategoriesResponse) obj;

	if (categories == null) {
	    if (other.categories != null)
		return false;
	} else if (!categories.equals(other.categories))
	    return false;

	return true;
    }

    @Override
    public String toString() {
	return "ListCategoriesResponse [categories=" + categories + "]";
    }

}