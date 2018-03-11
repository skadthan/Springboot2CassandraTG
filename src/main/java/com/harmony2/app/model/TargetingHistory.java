package com.harmony2.app.model;

import java.io.Serializable;

import org.springframework.data.cassandra.core.mapping.Table;

@Table("customer_targetting_history")
public class TargetingHistory implements Serializable {
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	 private String ssoid;
	 private String offername;
	 public String getSsoid() {
		return ssoid;
	}
	public void setSsoid(String ssoid) {
		this.ssoid = ssoid;
	}
	public String getOffername() {
		return offername;
	}
	public void setOffername(String offername) {
		this.offername = offername;
	}
	
	private int noofdayssincelastcommunicationviewed;
	 private int noofdayssincelastcommunicationaccepted;
	 private int noofdayssincelastcommunicationclicked;
	 private int noofdayssincelastcommunicationdeclined;
	 private int noofdayssincelastcommunicationliked;
	 private int noofdayssincelastcommunicationdisliked;
	 private int noofdayssincelastcommunicationpostponed;
	 private int noofdayssincelastcommunicationconverted;
	public int getNoofdayssincelastcommunicationviewed() {
		return noofdayssincelastcommunicationviewed;
	}
	public void setNoofdayssincelastcommunicationviewed(int noofdayssincelastcommunicationviewed) {
		this.noofdayssincelastcommunicationviewed = noofdayssincelastcommunicationviewed;
	}
	public int getNoofdayssincelastcommunicationaccepted() {
		return noofdayssincelastcommunicationaccepted;
	}
	public void setNoofdayssincelastcommunicationaccepted(int noofdayssincelastcommunicationaccepted) {
		this.noofdayssincelastcommunicationaccepted = noofdayssincelastcommunicationaccepted;
	}
	public int getNoofdayssincelastcommunicationclicked() {
		return noofdayssincelastcommunicationclicked;
	}
	public void setNoofdayssincelastcommunicationclicked(int noofdayssincelastcommunicationclicked) {
		this.noofdayssincelastcommunicationclicked = noofdayssincelastcommunicationclicked;
	}
	public int getNoofdayssincelastcommunicationdeclined() {
		return noofdayssincelastcommunicationdeclined;
	}
	public void setNoofdayssincelastcommunicationdeclined(int noofdayssincelastcommunicationdeclined) {
		this.noofdayssincelastcommunicationdeclined = noofdayssincelastcommunicationdeclined;
	}
	public int getNoofdayssincelastcommunicationliked() {
		return noofdayssincelastcommunicationliked;
	}
	public void setNoofdayssincelastcommunicationliked(int noofdayssincelastcommunicationliked) {
		this.noofdayssincelastcommunicationliked = noofdayssincelastcommunicationliked;
	}
	public int getNoofdayssincelastcommunicationdisliked() {
		return noofdayssincelastcommunicationdisliked;
	}
	public void setNoofdayssincelastcommunicationdisliked(int noofdayssincelastcommunicationdisliked) {
		this.noofdayssincelastcommunicationdisliked = noofdayssincelastcommunicationdisliked;
	}
	public int getNoofdayssincelastcommunicationpostponed() {
		return noofdayssincelastcommunicationpostponed;
	}
	public void setNoofdayssincelastcommunicationpostponed(int noofdayssincelastcommunicationpostponed) {
		this.noofdayssincelastcommunicationpostponed = noofdayssincelastcommunicationpostponed;
	}
	public int getNoofdayssincelastcommunicationconverted() {
		return noofdayssincelastcommunicationconverted;
	}
	public void setNoofdayssincelastcommunicationconverted(int noofdayssincelastcommunicationconverted) {
		this.noofdayssincelastcommunicationconverted = noofdayssincelastcommunicationconverted;
	}

	 @Override
	    public String toString() {
	        return "TargetingHistory [ssoid=" + ssoid + ", offername=" + offername + "noofdayssincelastcommunicationviewed=" + noofdayssincelastcommunicationviewed + ", noofdayssincelastcommunicationaccepted=" + noofdayssincelastcommunicationaccepted + ", noofdayssincelastcommunicationclicked=" + noofdayssincelastcommunicationclicked
	                + ", noofdayssincelastcommunicationdeclined=" + noofdayssincelastcommunicationdeclined + ", noofdayssincelastcommunicationliked=" + noofdayssincelastcommunicationliked +", noofdayssincelastcommunicationdisliked=" + noofdayssincelastcommunicationdisliked + ", noofdayssincelastcommunicationpostponed=" + noofdayssincelastcommunicationpostponed + ", noofdayssincelastcommunicationconverted=" + noofdayssincelastcommunicationconverted + "]";
	    }  

}
