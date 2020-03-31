package paul.sydney.model;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.Clob;

public class HongXunFile implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2516745270873057482L;
	
	private Integer idc;
	private Integer parentId;
	private String name;	
	private Clob description;	
	//private String personIntro;
	private Blob myfile;
	public Integer getIdc() {
		return idc;
	}
	public void setIdc(Integer idc) {
		this.idc = idc;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public Clob getDescription() {
		return description;
	}
	public void setDescription(Clob description) {
		this.description = description;
	}
	/*	public String getPersonIntro() {
		return personIntro;
	}
	public void setPersonIntro(String personIntro) {
		this.personIntro = personIntro;
	}*/
	
	public Blob getMyfile() {
		return myfile;
	}


	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public void setMyfile(Blob myfile) {
		this.myfile = myfile;
	}

	
}
