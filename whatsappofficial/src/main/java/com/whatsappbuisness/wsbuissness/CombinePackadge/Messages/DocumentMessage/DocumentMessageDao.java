package com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.DocumentMessage;
/*      
 Author=Supreet Singh
 Date= 04/02/21 5:15 PM
 */


import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DocumentMessageDao implements Serializable {

	private String caption;
	private String id;
	private String filename;
	@JsonProperty("link")
	private String link;
	private String file;
	@JsonProperty("mime_type")
	private String mime_type;
	private String sha256;

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public String getMime_type() {
		return mime_type;
	}

	public void setMime_type(String mime_type) {
		this.mime_type = mime_type;
	}

	public String getSha256() {
		return sha256;
	}

	public void setSha256(String sha256) {
		this.sha256 = sha256;
	}
	
	@Override
	public String toString() {
		return "DocumentMessageDao{" +
				"caption='" + caption + '\'' +
				", id='" + id + '\'' +
				", filename='" + filename + '\'' +
				", link='" + link + '\'' +
				", file='" + file + '\'' +
				", mime_type='" + mime_type + '\'' +
				", sha256='" + sha256 + '\'' +
				'}';
	}



}
