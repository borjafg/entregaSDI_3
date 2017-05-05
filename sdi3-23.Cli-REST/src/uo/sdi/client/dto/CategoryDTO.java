package uo.sdi.client.dto;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "category")
public class CategoryDTO implements Serializable {

    private static final long serialVersionUID = -411924970180L;

    private Long id;
    private String name;
    private Date created;

    private Long userId;

    public CategoryDTO() {

    }

    /**
     * Se usa para asociar una tarea con su categoría, cuando solo se conoce el
     * id de dicha categoría.
     */
    CategoryDTO(Long id) {
	this.id = id;
    }

    /**
     * Se usa para convertir una categoría en un DTO.
     */
    public CategoryDTO(Long id, Date created, Long userId) {
	this.id = id;
	this.created = created;
	this.userId = userId;
    }

    /**
     * Se usa para crear una nueva categoría.
     */
    public CategoryDTO(String name) {
	this.name = name;
    }

    // ===================================
    // Getters y Setters
    // ===================================

    @XmlElement(name = "id")
    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    @XmlElement(name = "name")
    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    @XmlElement(name = "created")
    public Date getCreated() {
	return created;
    }

    @XmlElement(name = "user_id")
    public Long getUserId() {
	return userId;
    }

    // ====================================
    // HashCode, equals y toString
    // ====================================

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;

	result = prime * result + ((id == null) ? 0 : id.hashCode());

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

	CategoryDTO other = (CategoryDTO) obj;

	if (id == null) {
	    if (other.id != null)
		return false;
	} else if (!id.equals(other.id))
	    return false;

	return true;
    }

    @Override
    public String toString() {
	return "CategoryDTO [id=" + id + ", name=" + name + ", created="
		+ created + ", userId=" + userId + "]";
    }

}