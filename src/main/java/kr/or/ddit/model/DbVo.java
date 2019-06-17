package kr.or.ddit.model;

public class DbVo {
	
	private String uri;
	private String classname;

	public DbVo(String uri, String classname) {
		this.uri = uri;
		this.classname = classname;
	}
	
	public DbVo(){
		
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getClassname() {
		return classname;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}
	
	

}
